package com.example.security.handle;

import com.example.security.enums.ResultEnum;
import com.example.security.utils.ResponseUtil;
import com.example.security.utils.ResultUtil;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
  * <p>
  *  权限不足
  * </p>
  * @author NanYa
  * @since 2020-04-03
  */
public class RestAuthenticationAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse response, AccessDeniedException e) throws IOException, ServletException {
        ResponseUtil.write(response, ResultUtil.error(ResultEnum.ACCESS_NOT));
    }
}
