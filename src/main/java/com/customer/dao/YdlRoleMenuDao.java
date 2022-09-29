package com.customer.dao;

import com.customer.entity.YdlRoleMenu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * 角色和菜单关联表(YdlRoleMenu)表数据库访问层
 *
 * @author makejava
 * @since 2022-09-26 13:51:48
 */
@Mapper
public interface YdlRoleMenuDao {

    /**
     * 通过ID查询单条数据
     *
     * @param roleId 主键
     * @return 实例对象
     */
    YdlRoleMenu queryById(Long roleId);

    /**
     * 查询指定行数据
     *
     * @param ydlRoleMenu 查询条件
     * @param pageable         分页对象
     * @return 对象列表
     */
    List<YdlRoleMenu> queryAllByLimit(YdlRoleMenu ydlRoleMenu, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param ydlRoleMenu 查询条件
     * @return 总行数
     */
    long count(YdlRoleMenu ydlRoleMenu);

    /**
     * 新增数据
     *
     * @param ydlRoleMenu 实例对象
     * @return 影响行数
     */
    int insert(YdlRoleMenu ydlRoleMenu);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<YdlRoleMenu> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<YdlRoleMenu> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<YdlRoleMenu> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<YdlRoleMenu> entities);

    /**
     * 修改数据
     *
     * @param ydlRoleMenu 实例对象
     * @return 影响行数
     */
    int update(YdlRoleMenu ydlRoleMenu);

    /**
     * 通过主键删除数据
     *
     * @param roleId 主键
     * @return 影响行数
     */
    int deleteById(Long roleId);

}

