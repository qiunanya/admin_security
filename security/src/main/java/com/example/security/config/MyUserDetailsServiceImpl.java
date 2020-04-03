package com.example.security.config;

import com.example.security.entitys.Permission;
import com.example.security.entitys.Role;
import com.example.security.entitys.UserEntity;
import com.example.security.service.IPermissionService;
import com.example.security.service.IRoleService;
import com.example.security.service.IUserService;
import com.example.security.utils.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
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
    @Autowired
    private IPermissionService iPermissionService;
    @Autowired
    private IRoleService iRoleService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = iUserService.findUserByName(username);
        if (StringUtils.isEmpty(user)){
            throw new UsernameNotFoundException("用户不存在");
        }else {
            String userId = user.getUserId();
            // 获取角色集合
            List<Role> roleList = iRoleService.getRoleListByUserId(userId);
            // 获取权限集合
            List<Permission> permissionList = iPermissionService.permissionListByUserId(userId);

            //给用户设置角色权限信息
            List<GrantedAuthority> authorities = new ArrayList<>();

            // 遍历角色
            for (Role role : roleList){
                String roleName = "ROLE_"+role.getRoleName();
                SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(roleName);
                authorities.add(simpleGrantedAuthority);

            }

            // 遍历权限
            for (Permission permission : permissionList){
                SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(permission.getPermissionName());
                authorities.add(simpleGrantedAuthority);
            }

//            authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
//            authorities.add(new SimpleGrantedAuthority("ROLE_SELECT"));
//            authorities.add(new SimpleGrantedAuthority("UPDATE"));

            // 把admin对象和authorities 封装到 UserDetails中
//            String password = user.getUserPassword();
//    return new User(username,password,authorities);
            String token = jwtTokenUtil.createToken(user);
            ExtendSecurityUser admin = new ExtendSecurityUser(user, authorities,roleList,token);


            return admin;
        }


    }
}
