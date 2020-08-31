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
   * <p>
   * 角色表 服务实现类
   * </p>
   *
   * @author qiu
   * @since 2020-03-87
   */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_permission")
@ApiModel(value = "系统权限")
public class SysPermission implements Serializable {

    private static final long serialVersionUID = 1L;

     @TableId
     @ApiModelProperty(value = "权限编号")
     private String permissionId;

     @ApiModelProperty(value = "权限标识")
     private String permissionName;

     @ApiModelProperty(value = "授权url")
     private String permissionUrl;

     @ApiModelProperty(value = "权限描述")
     private String permissionComment;

     @ApiModelProperty(value = "权限状态,0正常，-1删除")
     private String permissionStatus;

}
