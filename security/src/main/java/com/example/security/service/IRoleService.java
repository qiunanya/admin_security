package com.example.security.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.security.entitys.Role;

import java.util.List;

/**
   * <p>
   * 角色表 服务实现类
   * </p>
   *
   * @author qiu
   * @since 2020-03-87
   */

public interface IRoleService extends IService<Role> {

     /**
       * <p>
       *  根据用户id获取角色集合
       * </p>
       * @param userId
       * @author qiu
       * @since 2020-03-87
       * @return List<Role>
       */
     List<Role> getRoleListByUserId(String userId);
}
