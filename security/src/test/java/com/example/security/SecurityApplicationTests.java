package com.example.security;

import com.example.security.entitys.Permission;
import com.example.security.entitys.Role;
import com.example.security.service.IPermissionService;
import com.example.security.service.IRoleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SecurityApplicationTests {
    @Autowired
    private IPermissionService iPermissionService;

    @Autowired
    private IRoleService iRoleService;

    @Test
    public void contextLoads() {
        String pwd = "123456";
        String encode = new BCryptPasswordEncoder().encode(pwd);
        System.out.println("加密密码："+encode);

    }

    @Test
    public void getPermissionList(){
        String userId = "1601e658096048f48225c50f7e879a02";
        List<Permission> list = iPermissionService.permissionListByUserId(userId);
        System.out.println("用户的权限集合："+list);
    }

    @Test
    public void getRoleList(){
        String userId = "1601e658096048f48225c50f7e879a02";
        List<Role> list = iRoleService.getRoleListByUserId(userId);
        System.out.println("用户的角色集合："+list);
    }

}
