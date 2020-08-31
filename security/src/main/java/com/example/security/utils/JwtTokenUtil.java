package com.example.security.utils;

import com.alibaba.fastjson.JSON;
import com.example.security.config.LoginUserDetails;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Clock;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.DefaultClock;
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

    private Clock clock = DefaultClock.INSTANCE;


    /**
     * 生成token
     *
     * @param userEntity
     * @return
     */
    public String createToken(String username,String id) {
        Date createTime = clock.now();
        Map<String, Object> map = new HashMap<>(1);
        map.put("user", username);
        map.put("id", id);
        return StaticConstant.BEARER+" "+Jwts.builder()
                .setClaims(map)
                .setId(id)
                .setSubject(username)
                .setIssuedAt(createTime)
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
    public LoginUserDetails getUserDTO(String token) {
        Claims claims = generateToken(token);
        Map<String, String> map = claims.get("user", Map.class);
        LoginUserDetails userDTO = JSON.parseObject(JSON.toJSONString(map), LoginUserDetails.class);
        return userDTO;
    }



}
