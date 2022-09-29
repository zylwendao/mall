package com.customer.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.io.Serializable;

/**
 * 用户信息表(YdlUser)实体类
 *
 * @author makejava
 * @since 2022-09-26 13:51:48
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class YdlUser implements Serializable {
    private static final long serialVersionUID = -23714846144621829L;
    /**
     * 用户ID
     */
    private Long userId;
    /**
     * 用户账号
     */
    @NotNull
    @Size(min = 5, max = 15, message = "用户名长度应该在 5 到 15 之间")
    private String userName;
    /**
     * 用户昵称
     */
    private String nickName;
    /**
     * 用户邮箱
     */
    @Email
    private String email;
    /**
     * 手机号码
     */
    private String phonenumber;
    /**
     * 用户性别（0男 1女 2未知）
     */
    private String sex;
    /**
     * 头像地址
     */
    private String avatar;
    /**
     * 密码
     */
    @NotNull
    @Size(min = 5, max = 15, message = "密码长度应该在 5 到 15 之间")
    private String password;
    /**
     * 帐号状态（0正常 1停用）
     */
    private String status;
    /**
     * 删除标志（0代表存在 2代表删除）
     */
    private String delFlag;
    /**
     * 最后登录IP
     */
    private String loginIp;
    /**
     * 最后登录时间
     */
    private Date loginDate;
    /**
     * 创建者
     */
    private String createBy;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新者
     */
    private String updateBy;
    /**
     * 更新时间
     */
    private Date updateTime;
}

