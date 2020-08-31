package com.example.security.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.security.entitys.SysPermission;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
   * <p>
   * 权限服务实习
   * </p>
   *
   * @author qiu
   * @since 2020-03-87
   */
@Mapper
public interface IPermissionService extends IService<SysPermission> {
  /**
    * <p>
    *  根据用户id获取权限集合
    * </p>
    * @param userId 用户id
    * @author qiu
    * @since 2020-03-87
    * @return List<SysPermission>
    */
 List<SysPermission> permissionListByUserId (String userId);

}
