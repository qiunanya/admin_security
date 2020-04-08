package com.example.security.framework;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
  * <p>
  *  token 属性类
  * </p>
  *
  * @author qiu
  * @since 2020-04-94
  */
@Configuration
@Component
@Data
public class TokenProperties {

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

}
