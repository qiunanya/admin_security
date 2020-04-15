package com.example.security.handle;

import com.example.security.enums.ResultEnum;
import com.example.security.utils.ResponseUtil;
import com.example.security.utils.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/**
  * <p>
  *  权限认证异常处理器
  * </p>
  * @author NanYa
  * @since 2020-04-03
  */
public class AuthEntryPoint implements AuthenticationEntryPoint {

    private final static Logger logger = LoggerFactory.getLogger(AuthEntryPoint.class);

    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        logger.error(e.getMessage());
        ResponseUtil.write(response, ResultUtil.error(ResultEnum.TOKEN_IS_NOT_VALID));
    }
}
