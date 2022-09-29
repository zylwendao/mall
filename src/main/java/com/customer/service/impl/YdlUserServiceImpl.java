package com.customer.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.customer.Constant.Constants;
import com.customer.dao.YdlUserDao;
import com.customer.entity.YdlLoginUser;
import com.customer.entity.YdlUser;
import com.customer.service.YdlUserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import eu.bitwalker.useragentutils.UserAgent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

/**
 * 用户信息表(YdlUser)表服务实现类
 *
 * @author makejava
 * @since 2022-09-26 13:51:48
 */
@Service("ydlUserService")
@Slf4j
public class YdlUserServiceImpl implements YdlUserService {
    @Resource
    private YdlUserDao ydlUserDao;

    @Resource
    private StringRedisTemplate stringRedisTemplate;
//    @Resource
//    private StringRedisTemplate redisTemplate;
    @Resource
    private RestTemplate restTemplate;
    /**
     * 通过ID查询单条数据
     *
     * @param userId 主键
     * @return 实例对象
     */
    @Override
    public YdlUser queryById(Long userId) {
        return this.ydlUserDao.queryById(userId);
    }

    /**
     * 分页查询
     *
     * @param ydlUser 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @Override
    public Page<YdlUser> queryByPage(YdlUser ydlUser, PageRequest pageRequest) {
        long total = this.ydlUserDao.count(ydlUser);
        return new PageImpl<>(this.ydlUserDao.queryAllByLimit(ydlUser, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param ydlUser 实例对象
     * @return 实例对象
     */
    @Override
    public YdlUser insert(YdlUser ydlUser) {
        this.ydlUserDao.insert(ydlUser);
        return ydlUser;
    }

    /**
     * 修改数据
     *
     * @param ydlUser 实例对象
     * @return 实例对象
     */
    @Override
    public YdlUser update(YdlUser ydlUser) {
        this.ydlUserDao.update(ydlUser);
        return this.queryById(ydlUser.getUserId());
    }

    /**
     * 通过主键删除数据
     *
     * @param userId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long userId) {
        return this.ydlUserDao.deleteById(userId) > 0;
    }
    /**
     * 登录
     */
    @Override
    public YdlLoginUser login(String userName, String password) throws JsonProcessingException {

        // 1、登陆，使用用户名查询用户，没有查询到，说明没有该账户
        YdlUser ydlUser = ydlUserDao.queryByUserName(userName);
        if(ydlUser == null) throw new RuntimeException("执行登陆操作：【"+ userName +"】该用户不存在");

        // 2、如果查到了，比较比较密码，密码如果不正确，登陆失败
        if(!password.equals(ydlUser.getPassword())){
            log.info("执行登陆操作：【"+ userName +"】该用户密码输入错误");
            throw new RuntimeException("执行登陆操作：【"+ userName +"】该用户密码输入错误");
        }

        // 3、如果验证成功了

        // (1) 生成token
        String token = UUID.randomUUID().toString();

        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        UserAgent userAgent = new UserAgent(request.getHeader("User-Agent"));

        // 通过ip获取其所属的地址
        ResponseEntity<String> result = restTemplate.getForEntity("https://whois.pconline.com.cn/ipJson.jsp?ip="+request.getRemoteHost()+"&json=true", String.class);
        String body = result.getBody();
//        String body = "{1:2}";
        JSONObject js = JSONObject.parseObject(body);
        String location = js.getString("addr")+js.getString("pro")+js.getString("city")+js.getString("region");

        // (2) 封装一个YdlLoginUser，保存在redis
        YdlLoginUser ydlLoginUser = YdlLoginUser.builder()
                .userId(ydlUser.getUserId())
                .token(token)
                .ipaddr(request.getRemoteAddr())
                .loginTime(new Date())
                .os(userAgent.getOperatingSystem().getName())
                .browser(userAgent.getBrowser().getName())
                .loginLocation(location)
                .ydlUser(ydlUser)
                .build();

        //key进行处理  token:username:uuid

        // 1、根据用户名生成一个key前缀token:username:
        String keyPrefix =  Constants.TOKEN_PREFIX + userName +":";
        // 2、查询token:username:前缀的数据
        Set<String> keys = stringRedisTemplate.keys(keyPrefix + "*");
        // 3、删除原来的数据
        keys.forEach(key -> stringRedisTemplate.delete(key));
        // 4、把新的数据加入redis
        stringRedisTemplate.opsForValue().set(keyPrefix + token,ydlLoginUser.toString(),1000L);

        return ydlLoginUser;
    }

    public static void main(String[] args) {
        String ip = "{\"ip\":\"122.3.3.4\",\"pro\":\"\",\"proCode\":\"999999\",\"city\":\"\",\"cityCode\":\"0\",\"region\":\"\",\"regionCode\":\"0\",\"addr\":\" 菲律宾\",\"regionNames\":\"\",\"err\":\"noprovince\"}";
        System.out.println(JSONObject.parseObject(ip).getString("ip"));
    }
}
