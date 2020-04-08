package com.example.security.utils;

import com.alibaba.fastjson.JSON;
import com.example.security.config.SecurityUserDetailsImpl;
import com.example.security.entitys.UserEntity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName JwtTokenUtil
 * @Author QiuKing
 * @Date 2020/4/314:22
 */
@Configuration
@Component
@Data
public class JwtTokenUtil {

    /**
     * header名称
     */
    @Value("${jwt.tokenHeader}")
    private String tokenHeader;

    /**
     * token前缀
     */
    @Value("${jwt.tokenPrefix}")
    private String tokenPrefix;

    /**
     * 秘钥
     */
    @Value("${jwt.secret}")
    private String secret;

    /**
     * 过期时间
     */
    @Value("${jwt.expiration}")
    private Long expiration;

    /**
     * 选择记住后过期时间
     */
    @Value("${jwt.rememberExpiration}")
    private Long rememberExpiration;


    /**
     * 生成token
     *
     * @param userEntity
     * @return
     */
    public String createToken(UserEntity userEntity) {
        Map<String, Object> map = new HashMap<>(1);
        map.put("user", userEntity.getUserName());
        map.put("id", userEntity.getUserName());
        return Jwts.builder()
                .setClaims(map)
                .setSubject(userEntity.getUserName())
                .setExpiration(new Date(System.currentTimeMillis() + expiration *60 * 1000))
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }


    /**
     * 获取用户名
     *
     * @param token
     * @return
     */
    public String getUserName(String token) {
        return generateToken(token).getSubject();
    }

    /**
     * 解析token
     *
     * @param token
     * @return
     */
    public Claims generateToken(String token) {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * 获取userDTO
     *
     * @param token
     * @return
     */
    public SecurityUserDetailsImpl getUserDTO(String token) {
        Claims claims = generateToken(token);
        Map<String, String> map = claims.get("user", Map.class);
        SecurityUserDetailsImpl userDTO = JSON.parseObject(JSON.toJSONString(map), SecurityUserDetailsImpl.class);
        return userDTO;
    }



}
