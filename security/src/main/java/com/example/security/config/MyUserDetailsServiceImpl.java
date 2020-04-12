package com.example.security.config;

import com.example.security.entitys.Permission;
import com.example.security.entitys.Role;
import com.example.security.entitys.UserEntity;
import com.example.security.service.IPermissionService;
import com.example.security.service.IRoleService;
import com.example.security.service.IUserService;
import com.example.security.utils.JwtTokenUtil;
import com.example.security.utils.StaticConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private final static Logger logger = LoggerFactory.getLogger(MyUserDetailsServiceImpl.class);

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
            logger.debug("用户不存在");
            throw new UsernameNotFoundException("用户不存在");
        }else if(!user.getLockStatus().equals(StaticConstant.ZERO)){
            logger.info("用户账号已被锁定");
            throw new UsernameNotFoundException("用户账号已被锁定");
        }else if (!user.getUserStatus().equals(StaticConstant.ZERO)){
            logger.debug("用户账号已被删除");
            throw new UsernameNotFoundException("用户账号已被删除");
        }else {
            return createLoginUser(user);
        }
    }

    private SecurityUserDetailsImpl createLoginUser(UserEntity user){
        String userId = user.getUserId();
        // 获取角色集合
        List<Role> roleList = iRoleService.getRoleListByUserId(userId);
        // 获取权限集合
        List<Permission> permissionList = iPermissionService.permissionListByUserId(userId);

        //给用户设置角色权限信息
        List<GrantedAuthority> authorities = new ArrayList<>();

        // 遍历角色
        if (!StringUtils.isEmpty(roleList)&&roleList.size()>0){
            roleList.forEach(e->{
                SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(e.getRoleName());
                authorities.add(simpleGrantedAuthority);
            });
        }

        // 遍历权限
        if (!StringUtils.isEmpty(permissionList)&&permissionList.size()>0){
            permissionList.forEach(e->{
                SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(e.getPermissionName());
                authorities.add(simpleGrantedAuthority);
            });
        }
        // 创建token返回前端
        String token = jwtTokenUtil.createToken(user);
        SecurityUserDetailsImpl admin = new SecurityUserDetailsImpl(user, authorities,roleList,token);
        return admin;
    }

}
