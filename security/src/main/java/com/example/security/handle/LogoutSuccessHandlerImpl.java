package com.example.security.handle;

import com.example.security.utils.StaticConstant;
import com.example.security.utils.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/**
  * <p>
  *  自定义退出处理类 返回成功
  * </p>
  *
  * @author qiu
  * @since 2020-04-95
  */
@Component
public class LogoutSuccessHandlerImpl implements LogoutSuccessHandler {

    private static Logger logger = LoggerFactory.getLogger(LogoutSuccessHandlerImpl.class);

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
           // 退出系统业务逻辑
        Authentication authentication1 = SecurityContextHolder.getContext().getAuthentication();
        if (!StringUtils.isEmpty(authentication)){
            // 清除SecurityContextLogoutHandler全局主体信息，包括用户信息和权限
            new SecurityContextLogoutHandler().logout(request,response,authentication1);
        }

        logger.info("退出成功---》"+authentication1);
        ResponseUtil.out(response,ResponseUtil.resultMap(true,200, StaticConstant.LOGOUT_SUCCESS));

    }
}
