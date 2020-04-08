package com.example.security;

import com.example.security.entitys.Permission;
import com.example.security.entitys.Role;
import com.example.security.entitys.UserEntity;
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
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
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

    // 操作字符串
    @Autowired
    StringRedisTemplate stringRedisTemplate;
    // 操作字对象
    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    RedisTemplate<Object, UserEntity> userEntityRedisTemplate;

    @Test
    public void redisTest(){
        UserEntity entity = new UserEntity();
        entity.setUserId("123");
        entity.setUserName("邱南亚");
        entity.setUserCreateTime("2020-3-24");
        userEntityRedisTemplate.opsForValue().set("qiuny",entity);
    }

    @Test
    public void getValueTest(){
        String qiu = stringRedisTemplate.opsForValue().get("qiu");
        System.out.printf("获取值成功：qiu= "+qiu);
    }

    @Test
    public void deleteValueTest(){
        Boolean qiu = stringRedisTemplate.delete("msg");
        System.out.printf("删除成功：qiu= "+qiu);
    }

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
        String token = "eyJhbGciOiJIUzUxMiJ9.eyJqdGkiOiJmMjQ1NzAxOGRmMGI0MDJkOTY4NGVlZjYyZDM1NTc2ZiIsInN1YiI6InFpdW55IiwiaWF0IjoxNTg2MDA4Njg0LCJleHAiOjE1ODYyMjQ2ODR9.zAVV-SyXT2uuxGEDqR3KSIDhqmwra3yjcm7ewfU71hFh1_6-kxaN9hggmMDysavstyJxRBs9S8MXJimIn87yrw";
        Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        System.out.println("解析token："+claims + "密钥："+secret);

    }


}
