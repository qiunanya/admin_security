package com.example.security.handle;

import com.example.security.utils.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
  * <p>
  *  用户认证失败
  * </p>
  *
  * @author qiu
  * @since 2020-04-95
  */
@Component
public class AuthenticationFailHandler extends SimpleUrlAuthenticationFailureHandler {

    private final Logger logger = LoggerFactory.getLogger(AuthenticationFailHandler.class);

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {

        // super.onAuthenticationFailure(request, response, exception);
        //request.getParameter()

        logger.info("登录失败：AuthenticationFailHandler"+exception.getMessage());
        if (exception instanceof UsernameNotFoundException ){
            ResponseUtil.out(response,ResponseUtil.resultMap(false,500,"用户不存在"));
        }else if (exception instanceof BadCredentialsException){
            ResponseUtil.out(response,ResponseUtil.resultMap(false,500,"账号或密码错误"));
        }else if (exception instanceof LockedException){
            ResponseUtil.out(response,ResponseUtil.resultMap(false,500,"账号已锁定"));
        }else if (exception instanceof DisabledException){
            ResponseUtil.out(response,ResponseUtil.resultMap(false,500,"账号已被禁用，请联系管理员"));
        }else if (exception instanceof AccountExpiredException){
            ResponseUtil.out(response,ResponseUtil.resultMap(false,500,"账号已过期"));
        }else if (exception instanceof CredentialsExpiredException){
            ResponseUtil.out(response,ResponseUtil.resultMap(false,500,"账号密码已过期"));
        }else {
            ResponseUtil.out(response,ResponseUtil.resultMap(false,500, "登录失败，其他内部错误"));
        }
    }
}
