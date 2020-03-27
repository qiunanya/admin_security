package com.example.security.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.security.entitys.Permission;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
   * <p>
   * 角色表 服务实现类
   * </p>
   *
   * @author qiu
   * @since 2020-03-87
   */
@Repository
public interface PermissionMapper extends BaseMapper<Permission> {
      /**
        * <p>
        *  根据用户id获取权限集合
        * </p>
        * @param userId 用户id
        * @author qiu
        * @since 2020-03-87
        * @return  List<Permission>
        */
     List<Permission> permissionListByUserId(String userId);
 }
