package com.example.security.entitys;

import com.baomidou.mybatisplus.annotation.TableId;
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
public class Permission implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 权限编号
     */
    @TableId
    private String permissionId;

     /**
      * 权限标识
      */
     private String permissionName;

    /**
     * 授权url
     */
    private String permissionUrl;

    /**
     * 描述
     */
    private String permissionComment;

    /**
     * 权限状态,0正常，-1删除
     */
    private String permissionStatus;


}
