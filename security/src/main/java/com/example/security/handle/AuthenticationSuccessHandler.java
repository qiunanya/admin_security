package com.example.security.handle;

import com.example.security.config.LoginUserDetails;
import com.example.security.framework.RedisCacheProject;
import com.example.security.framework.TokenProperties;
import com.example.security.utils.JwtTokenUtil;
import com.example.security.utils.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
  * <p>
  *  登录成功的类
  *  采坑提醒，注意：request header中数据不能过大，否则会引起客户端出现 code = 400 的错误，
  * </p>
  *
  * @author qiu
  * @since 2020-04-94
  */
@Component
public class AuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
    private final Logger logger = LoggerFactory.getLogger(AuthenticationFailHandler.class);

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private TokenProperties tokenProperties;

    @Autowired
    private RedisCacheProject redisCacheProject;

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 重写父类请求成功方法
     * @param request
     * @param response
     * @param authentication
     * @throws ServletException
     * @throws IOException
     */

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
        LoginUserDetails userDetails = (LoginUserDetails) authentication.getPrincipal();
        // 创建token返回前端
        String token = jwtTokenUtil.createToken(userDetails.getUsername(),userDetails.getUserId());
        logger.info("登录成功 : onAuthenticationSuccess");
        ResponseUtil.out(response, ResponseUtil.resultMap(true,200,"登录成功",token,userDetails));

    }
}
