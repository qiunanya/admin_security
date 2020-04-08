package com.example.security.enums;

import lombok.Getter;
/**
  * <p>
  *  枚举类
  * </p>
  *
  * @author qiu
  * @since 2020-04-95
  */
@Getter
public enum ResultEnum {
    ACCESS_NOT(501, "权限不足"),

    TOKEN_IS_NOT_VALID(502, "token无效，请重新登录"),
    ;

    private Integer code;

    private String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
