package com.example.security.entitys;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @ClassName UserEntity
 * @Author QiuKing
 * @Date 2020/3/1911:08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("user")
public class UserEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户编号
     */
    @TableId
    private String userId;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 用户手机号码
     */
    private String userPhone;

    /**
     * 用户密码
     */
    private String userPassword;

    /**
     * 用户最近一次登录时间
     */
    private String userLastLoginTime;

    /**
     * 用户注册时间
     */
    private String userCreateTime;

    /**
     * 用户状态，0正常，-1删除
     */
    private String userStatus;

}