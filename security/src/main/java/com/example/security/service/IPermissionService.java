package com.example.security.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.security.entitys.Permission;

import java.util.List;

/**
   * <p>
   * 权限服务实习
   * </p>
   *
   * @author qiu
   * @since 2020-03-87
   */
public interface IPermissionService extends IService<Permission> {
  /**
    * <p>
    *  根据用户id获取权限集合
    * </p>
    * @param userId 用户id
    * @author qiu
    * @since 2020-03-87
    * @return List<Permission>
    */
 List<Permission> permissionListByUserId (String userId);

}
