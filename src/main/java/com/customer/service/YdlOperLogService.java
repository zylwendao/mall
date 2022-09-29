package com.customer.service;

import com.customer.entity.YdlOperLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * 操作日志(YdlOperLog)表服务接口
 *
 * @author makejava
 * @since 2022-09-26 13:51:48
 */
public interface YdlOperLogService {

    /**
     * 通过ID查询单条数据
     *
     * @param operId 主键
     * @return 实例对象
     */
    YdlOperLog queryById(Integer operId);

    /**
     * 分页查询
     *
     * @param ydlOperLog 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    Page<YdlOperLog> queryByPage(YdlOperLog ydlOperLog, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param ydlOperLog 实例对象
     * @return 实例对象
     */
    YdlOperLog insert(YdlOperLog ydlOperLog);

    /**
     * 修改数据
     *
     * @param ydlOperLog 实例对象
     * @return 实例对象
     */
    YdlOperLog update(YdlOperLog ydlOperLog);

    /**
     * 通过主键删除数据
     *
     * @param operId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer operId);

}
