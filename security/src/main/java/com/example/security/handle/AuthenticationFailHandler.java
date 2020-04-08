package com.example.security.handle;

import com.example.security.utils.StaticConstant;
import com.example.security.utils.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
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

        logger.info("登录失败：AuthenticationFailHandler"+exception);

        ResponseUtil.out(response,ResponseUtil.resultMap(false,201, StaticConstant.LOGIN_FAIL));

    }
}
