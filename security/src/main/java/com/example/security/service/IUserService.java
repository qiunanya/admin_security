package com.example.security.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.security.dto.user.FindUserDTO;
import com.example.security.entitys.SysUser;
import com.example.security.utils.ResultUtil;

/**
* 描述:
* @Author QiuKing
* @Date 2020/3/19 10:15
*/

public interface IUserService extends IService<SysUser> {
    /**
      * <p>
      *  获取用户列表
      * </p>
      * @return ResultUtil
      * @author qiu
      * @since 2020-08-26
      */
    ResultUtil getList();
    /**
      * <p>
      *  根据用户名查找用户信息
      * </p>
      * @param username
      * @return SysUser
      * @author qiu
      * @since 2020-08-23
      */
    SysUser findUserByName(String username);
    /**
      * <p>
      *  分页获取用户数据
      * </p>
      * @return IPage<SysUser>
      * @author qiu
      * @since 2020-08-26
     * @param findUserDTO
      */
    IPage<SysUser> getPage(FindUserDTO findUserDTO);
}
