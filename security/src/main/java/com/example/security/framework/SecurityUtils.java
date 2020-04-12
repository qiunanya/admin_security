package com.example.security.framework;

import com.example.security.entitys.Permission;
import com.example.security.entitys.UserEntity;
import com.example.security.service.IPermissionService;
import com.example.security.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
  * <p>
  *  SecurityUtils 工具类，获取用户信息等
  * </p>
  *
  * @author qiu
  * @since 2020-04-98
  */
@Component
public class SecurityUtils {
    @Autowired
    private IUserService iUserService;

    @Autowired
    private IPermissionService iPermissionService;

    /**
     * 获取当前用户信息
     */
    public UserEntity currentUser(){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return iUserService.findUserByName(userDetails.getUsername());
    }

    /**
     * 获取当前用户ming
     */
    public static String getUserName(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }

    /**
     * 通过用户名获取用户拥有权限
     *
     * @param username
     */
    public List<GrantedAuthority> getCurrUserPerms(String username) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        List<Permission> permissions = iPermissionService.permissionListByUserId(iUserService.findUserByName(username).getUserId());
        if (!StringUtils.isEmpty(permissions)){
            for (Permission p : permissions) {
                authorities.add(new SimpleGrantedAuthority(p.getPermissionName()));
            }
            return authorities;
        }else {
            return authorities;
        }
    }

    /**
     * 获取当前用户详细信息
     */
    public static UserDetails getUserDetails(){
        UserDetails userDetails;
        try {
            userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        }catch (Exception e){
            throw new RuntimeException("登录状态过期");
        }
        return userDetails;
    }

}
