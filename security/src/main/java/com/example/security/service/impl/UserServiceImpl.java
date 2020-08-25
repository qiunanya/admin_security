package com.example.security.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.security.entitys.UserEntity;
import com.example.security.mapper.sysUser.UserMapper;
import com.example.security.service.IUserService;
import com.example.security.utils.StaticConstant;
import com.example.security.utils.ResultUtil;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* 描述: <br>
* @Author QiuKing
* @Date 2020/3/19 11:10
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper,UserEntity> implements IUserService {

    @Override
    public ResultUtil getList() {
        QueryWrapper<UserEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("user_status", StaticConstant.ZERO);
        try {
            List<UserEntity> list = this.list(wrapper);
           return ResultUtil.success(list);
        }catch (Exception e){
           return ResultUtil.error(e.toString());
        }
    }

    @Override
    public UserEntity findUserByName(String username) {
        QueryWrapper<UserEntity> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(UserEntity::getUserName,username)
                .eq(UserEntity::getUserStatus, StaticConstant.ZERO);
        UserEntity user = this.getOne(wrapper);
        return user;
    }
}
