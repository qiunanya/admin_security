package com.example.security.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.security.entitys.UserEntity;
import com.example.security.utils.ResultUtil;

/**
* 描述:
* @Author QiuKing
* @Date 2020/3/19 10:15
*/

public interface IUserService extends IService<UserEntity> {

    ResultUtil getList();
    /**
      * <p>
      *  根据用户名查找用户信息
      * </p>
      * @param username
      * @return UserEntity
      * @author qiu
      * @since 2020-08-23
      */
    UserEntity findUserByName(String username);

}
