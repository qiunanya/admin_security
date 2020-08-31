package com.example.security.utils;

/**
 * @ClassName 常量
 * @Author QiuKing
 * @Date 2020/3/1910:19
 */
public class StaticConstant {

    public final static Integer ZERO = 0;

    public final static Integer ONE = 1;

    public final static String LOGIN_FAIL = "登录失败";

    public final static String LOGOUT_SUCCESS = "退出成功";

    public final static String UNAUTHORIZED = "认证失败，访问路径为";

    public final static String NO_AUTHORIZED = "抱歉，您没有访问权限";

    /**
     * 用户账号是否被锁定 0正常 -1锁定
     */
    public final static Integer USER_ACCOUNT_NORMAL = 0;

    /**
     * 用户账号是否被禁用 0启用 -1禁用
     */
    public final static Integer USER_FORBIDDEN_ACCOUNT= 0;

    public final static String AUTHORITIES = "authorities";

    public final static String LOGIN_MARK = "new_app";

    // 持证标识
    public final static String BEARER = "Bearer";

}
