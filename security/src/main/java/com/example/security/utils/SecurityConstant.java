package com.example.security.utils;

/**
  * <p>
  *  SecurityConstant security 常量
  * </p>
  *
  * @author qiu
  * @since 2020-04-07
  */
public class SecurityConstant {

    /**
     * token分割
     */
    public final static String TOKEN_PREFIX = "Bearer ";

    /**
     * JWT签名加密key
     */
    public final static String JWT_SIGN_KEY = "NanYaQiu";

    /**
     * token参数头
     */
    public final static String HEADER = "Authorization";

    /**
     * 令牌
     */
    public final static String TOKEN = "token";

    /**
     * 权限参数头
     */
    public final static String AUTHORITIES = "userAuthorities";

    /**
     * 登录用户 redis key
     */
    public final static String LOGIN_TOKEN_KEY = "login_tokens:";

}
