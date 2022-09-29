package com.customer.entity;

import java.io.Serializable;

/**
 * 用户和角色关联表(YdlUserRole)实体类
 *
 * @author makejava
 * @since 2022-09-26 13:51:48
 */
public class YdlUserRole implements Serializable {
    private static final long serialVersionUID = -16518972471595226L;
    /**
     * 用户ID
     */
    private Long userId;
    /**
     * 角色ID
     */
    private Long roleId;


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

}

