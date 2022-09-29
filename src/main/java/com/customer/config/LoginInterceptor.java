package com.customer.config;

import com.customer.Constant.Constants;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Component
public class LoginInterceptor implements HandlerInterceptor {


    @Resource
    private StringRedisTemplate redisutil;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        // 判断有没有Authorization这个请求头，拿到首部信息的Authorization的值
        ResponseEntity<String> res = ResponseEntity.status(401).body("Bad Credentials!");
        String token = request.getHeader("Authorization");
        System.out.println("token============"+token);
        if (token == null) {
            response.setStatus(401);
            response.getWriter().write("1122");
            return false;
        }
        //        String tokenKey = Constants.TOKEN_PREFIX + request.getHeader("username")+":"+token;
        String redisQL = Constants.TOKEN_PREFIX + "*:" + token;
        Set<String> keys = redisutil.keys(redisQL);
        if (keys== null || keys.size() == 0){
            response.setStatus(401);
            response.getWriter().write("1122");
            return false;
        }
        String tokenKey = (String)keys.toArray()[0];
        // 3、使用token去redis中查看，有没有对应的loginUser
        String ydlLoginUser = redisutil.opsForValue().get(tokenKey);
        if (ydlLoginUser == null) {
            response.setStatus(401);
            response.getWriter().write("1122");
            return false;
        }
//        // 给token续命
        redisutil.expire(tokenKey,1000L, TimeUnit.SECONDS);

        return true;
    }
}