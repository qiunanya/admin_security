package com.example.security.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.security.dto.user.FindUserDTO;
import com.example.security.entitys.SysUser;
import com.example.security.mapper.sysUser.UserMapper;
import com.example.security.service.IUserService;
import com.example.security.utils.ResultUtil;
import com.example.security.utils.StaticConstant;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* 描述: <br>
* @Author QiuKing
* @Date 2020/3/19 11:10
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, SysUser> implements IUserService {

    @Override
    public ResultUtil getList() {
        QueryWrapper<SysUser> wrapper = new QueryWrapper<>();
        wrapper.eq("user_status", StaticConstant.ZERO);
        try {
            List<SysUser> list = this.list(wrapper);
           return ResultUtil.success(list);
        }catch (Exception e){
           return ResultUtil.error(e.toString());
        }
    }

    @Override
    public SysUser findUserByName(String username) {
        QueryWrapper<SysUser> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(SysUser::getName,username)
                .eq(SysUser::getUserStatus, StaticConstant.ONE);
        return this.getOne(wrapper);
    }

    @Override
    public IPage<SysUser> getPage(FindUserDTO findUserDTO) {
        QueryWrapper<SysUser> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(SysUser::getUserStatus,StaticConstant.ZERO);
        return this.page(new Page<SysUser>(findUserDTO.getPage(),findUserDTO.getSize()),wrapper);
    }
}
