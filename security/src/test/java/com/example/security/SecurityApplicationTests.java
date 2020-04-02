package com.example.security;

import com.example.security.entitys.Permission;
import com.example.security.entitys.Role;
import com.example.security.service.IPermissionService;
import com.example.security.service.IRoleService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SecurityApplicationTests {
    @Value("${jwt.secret}")
    private String secret= null;

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
    // 生成token
    @Test
    public void createJwtTest(){
        long now = System.currentTimeMillis();
        long time = 7*24*3600*1000; // 7天过期
        long dead = now + time; // 60秒过期
        JwtBuilder builder = Jwts.builder();
        builder.setId("0902")
                .setSubject("邱")
                .setIssuedAt(new Date())
                .setExpiration(new Date(dead))
                .claim("admin","person") // 自定义claims
                .signWith(SignatureAlgorithm.HS256,secret);

        System.out.println("创建token："+builder.compact()+"  密钥："+secret);

    }

    // 解析token
    @Test
    public void resoleToken(){
        // 生成后解析
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIwOTAyIiwic3ViIjoi6YKxIiwiaWF0IjoxNTg1ODEwOTkxLCJleHAiOjE1ODY0MTU3OTEsImFkbWluIjoicGVyc29uIn0.VyMsg_nZ63N7OISIPzMVX4xMMzZs3tu3CljvDKw_EUM";
        Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        System.out.println("解析token："+claims + "密钥："+secret);

    }


}
