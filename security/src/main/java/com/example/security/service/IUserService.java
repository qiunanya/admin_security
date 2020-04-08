package com.example.security.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.security.entitys.UserEntity;
import com.example.security.utils.ResultUtil;

/**
* 描述: <br>
* @Author QiuKing
* @Date 2020/3/19 10:15
*/

public interface IUserService extends IService<UserEntity> {

    ResultUtil getList();

    UserEntity findUserByName(String username);

}
