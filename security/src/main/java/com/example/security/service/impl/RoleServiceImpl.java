package com.example.security.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.security.entitys.Role;
import com.example.security.mapper.RoleMapper;
import com.example.security.service.IRoleService;
import com.example.security.utils.Contant;
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
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<Role> getRoleListByUserId(String userId) {

        return roleMapper.getRoleListByUserId(userId);
    }
}
