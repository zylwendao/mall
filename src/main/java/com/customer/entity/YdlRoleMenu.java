package com.customer.entity;

import java.io.Serializable;

/**
 * 角色和菜单关联表(YdlRoleMenu)实体类
 *
 * @author makejava
 * @since 2022-09-26 13:51:48
 */
public class YdlRoleMenu implements Serializable {
    private static final long serialVersionUID = 129129858206055330L;
    /**
     * 角色ID
     */
    private Long roleId;
    /**
     * 菜单ID
     */
    private Long menuId;


    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

}

