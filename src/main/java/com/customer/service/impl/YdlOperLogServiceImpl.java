package com.customer.service.impl;

import com.customer.entity.YdlOperLog;
import com.customer.dao.YdlOperLogDao;
import com.customer.service.YdlOperLogService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;

/**
 * 操作日志(YdlOperLog)表服务实现类
 *
 * @author makejava
 * @since 2022-09-26 13:51:48
 */
@Service("ydlOperLogService")
public class YdlOperLogServiceImpl implements YdlOperLogService {
    @Resource
    private YdlOperLogDao ydlOperLogDao;

    /**
     * 通过ID查询单条数据
     *
     * @param operId 主键
     * @return 实例对象
     */
    @Override
    public YdlOperLog queryById(Integer operId) {
        return this.ydlOperLogDao.queryById(operId);
    }

    /**
     * 分页查询
     *
     * @param ydlOperLog 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @Override
    public Page<YdlOperLog> queryByPage(YdlOperLog ydlOperLog, PageRequest pageRequest) {
        long total = this.ydlOperLogDao.count(ydlOperLog);
        return new PageImpl<>(this.ydlOperLogDao.queryAllByLimit(ydlOperLog, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param ydlOperLog 实例对象
     * @return 实例对象
     */
    @Override
    public YdlOperLog insert(YdlOperLog ydlOperLog) {
        this.ydlOperLogDao.insert(ydlOperLog);
        return ydlOperLog;
    }

    /**
     * 修改数据
     *
     * @param ydlOperLog 实例对象
     * @return 实例对象
     */
    @Override
    public YdlOperLog update(YdlOperLog ydlOperLog) {
        this.ydlOperLogDao.update(ydlOperLog);
        return this.queryById(ydlOperLog.getOperId());
    }

    /**
     * 通过主键删除数据
     *
     * @param operId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer operId) {
        return this.ydlOperLogDao.deleteById(operId) > 0;
    }
}
