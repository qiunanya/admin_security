package com.example.security.handle;

import com.example.security.utils.StaticConstant;
import com.example.security.utils.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/**
  * <p>
  *  认证失败处理类 返回未授权
  * </p>
  *
  * @author qiu
  * @since 2020-04-97
  */
@Slf4j
@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {

    private final static Logger logger = LoggerFactory.getLogger(AuthenticationEntryPointImpl.class);

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {

        logger.error(e.getMessage());

        String msg = StaticConstant.UNAUTHORIZED+":"+request.getRequestURI();
        ResponseUtil.out(response,ResponseUtil.resultMap(false,201,msg));

    }
}
