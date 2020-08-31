package com.example.security.entitys;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @ClassName SysUser
 * @Author QiuKing
 * @Date 2020/3/1911:08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_user")
@ApiModel(value = "用户")
public class SysUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId
    @ApiModelProperty(value = "用户ID")
    private String userId;

    @ApiModelProperty(value = "用户名")
    private String name;

    @ApiModelProperty(value = "用户手机号码")
    private String userPhone;

    @ApiModelProperty(value = "用户密码")
    private String userPassword;

    @ApiModelProperty(value = "用户最近一次登录时间")
    private String userLastLoginTime;

    @ApiModelProperty(value = "用户注册时间")
    private String userCreateTime;

    @ApiModelProperty(value = "用户状态，0删除，1正常")
    private Boolean userStatus;

    @ApiModelProperty(value = "账号是否过期，0过期，1未过期")
    private Boolean accountStatus;

    @ApiModelProperty(value = "是否锁定 0正常，1锁定")
    private Boolean lockStatus;

    @ApiModelProperty(value = "禁用状态 0禁用 1启用")
    private Boolean forbiddenStatus;


}