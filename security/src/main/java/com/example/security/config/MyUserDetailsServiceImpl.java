package com.example.security.config;

import com.example.security.entitys.UserEntity;
import com.example.security.service.IUserService;
import com.example.security.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 根据表单提交的账号查询user对象，并装配角色、权限信息
 * @ClassName MyUserDetailsServiceImpl
 * @Author QiuKing
 * @Date 2020/3/2615:49
 */
@Component
public class MyUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private IUserService iUserService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = iUserService.findUserByName(username);
        if (!StringUtils.isEmpty(user)){
            //1 、给用户设置角色权限信息
            List<GrantedAuthority> authorities = new ArrayList<>();

            authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
            authorities.add(new SimpleGrantedAuthority("UPDATE"));

            //2 、把admin对象和authorities 封装到 UserDetails中
            String password = user.getUserPassword();

           return new User(username,password,authorities);

        }else {
            return null;
        }


    }
}
