package com.example.security.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.security.entitys.SysPermission;
import com.example.security.mapper.permission.PermissionMapper;
import com.example.security.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
   * <p>
   * 角色表 服务实现类
   * </p>
   *
   * @author qiu
   * @since 2020-03-87
   */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, SysPermission> implements IPermissionService {
  @Autowired
  private PermissionMapper permissionMapper;

 @Override
 public List<SysPermission> permissionListByUserId(String userId) {
  List<SysPermission> list = permissionMapper.permissionListByUserId(userId);
  return list;
 }
}
