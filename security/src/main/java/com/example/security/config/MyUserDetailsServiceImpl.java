package com.example.security.config;

import cn.hutool.core.util.ObjectUtil;
import com.example.security.entitys.SysPermission;
import com.example.security.entitys.SysUser;
import com.example.security.framework.RedisCacheProject;
import com.example.security.service.IPermissionService;
import com.example.security.service.IRoleService;
import com.example.security.service.IUserService;
import com.example.security.utils.JwtTokenUtil;
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

    @Autowired
    private RedisCacheProject redisCacheProject;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser sysUser = iUserService.findUserByName(username);
        if (ObjectUtil.isEmpty(sysUser)){
            logger.debug("用户不存在");
            throw new UsernameNotFoundException("用户不存在");
        } else {
            String userId = sysUser.getUserId();
            //给用户设置角色权限信息
            List<GrantedAuthority> authorities = new ArrayList<>();
            // 获取权限集合
            System.out.println("用户id=>"+userId);
            List<SysPermission> permissionList= iPermissionService.permissionListByUserId(userId);
            // 遍历权限
            if (!StringUtils.isEmpty(permissionList)&&permissionList.size()>0){
                permissionList.forEach(e->{
                    authorities.add(new SimpleGrantedAuthority(e.getPermissionName()));
                });
            }
            return new LoginUserDetails(userId,sysUser.getName(),sysUser.getUserPassword(),
                       sysUser.getUserStatus(),sysUser.getAccountStatus(),
                       sysUser.getForbiddenStatus(),sysUser.getLockStatus(),authorities);
        }
    }

}
