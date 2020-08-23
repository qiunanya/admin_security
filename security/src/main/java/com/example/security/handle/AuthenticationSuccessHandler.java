package com.example.security.handle;

import com.example.security.config.SecurityUserDetailsImpl;
import com.example.security.framework.RedisCacheProject;
import com.example.security.framework.TokenProperties;
import com.example.security.utils.JwtTokenUtil;
import com.example.security.utils.ResponseUtil;
import com.example.security.utils.UUIDUtil;
import io.jsonwebtoken.Clock;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.DefaultClock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

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

    private Clock clock = DefaultClock.INSTANCE;

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
        //super.onAuthenticationSuccess(request, response, authentication);
        String username = ((UserDetails)authentication.getPrincipal()).getUsername();
        SecurityUserDetailsImpl userDetails = (SecurityUserDetailsImpl) authentication.getPrincipal();
        // 权限缓存到redis
//        List<GrantedAuthority> authorities = ( List<GrantedAuthority> )((UserDetails)authentication.getPrincipal()).getAuthorities();
//
//        List<String> list = new ArrayList<>();
//        authorities.forEach(e->list.add(e.getAuthority()));
//
//        AuthorityEntity authorityEntity = AuthorityEntity.builder().username(username).list(list).build();
//        // 权限放入缓存
//        redisCacheProject.setCacheAuthority(StaticConstant.AUTHORITIES + username, authorityEntity);

        Date createTime = clock.now();

        String token = Jwts.builder().setId(UUIDUtil.createId())
                .setSubject(username)
                .setIssuedAt(createTime)
                .setExpiration(new Date(System.currentTimeMillis() + tokenProperties.getExpiration() * 60 * 1000))
                .signWith(SignatureAlgorithm.HS512, tokenProperties.getSecret())
                .compact();
        logger.info("登录成功 : onAuthenticationSuccess");
        ResponseUtil.out(response,ResponseUtil.resultMap(true,200,"登录成功",token,userDetails));


    }
}
