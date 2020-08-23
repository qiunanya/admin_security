package com.example.security.handle;

import com.example.security.entitys.AuthorityEntity;
import com.example.security.framework.RedisCacheProject;
import com.example.security.framework.SecurityUtils;
import com.example.security.framework.TokenProperties;
import com.example.security.utils.ResponseUtil;
import com.example.security.utils.SecurityConstant;
import com.example.security.utils.StaticConstant;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.util.StringUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
  * <p>
  *  权限过滤认证
  * </p>
  *
  * @author qiu
  * @since 2020-04-07
  */
public class AuthenticationFilterHandler extends BasicAuthenticationFilter {

    private TokenProperties tokenProperties;

    private RedisCacheProject redisCacheProject;

    private SecurityUtils securityUtils;

    private static final Logger logger = LoggerFactory.getLogger(AuthenticationFilterHandler.class);

    public AuthenticationFilterHandler(AuthenticationManager authenticationManager,
                                       TokenProperties tokenProperties,
                                       RedisCacheProject redisCacheProject,
                                       SecurityUtils securityUtils){
        super(authenticationManager);
        this.tokenProperties = tokenProperties;
        this.redisCacheProject = redisCacheProject;
        this.securityUtils = securityUtils;
    }

    public AuthenticationFilterHandler(AuthenticationManager authenticationManager, AuthenticationEntryPoint authenticationEntryPoint){
        super(authenticationManager,authenticationEntryPoint);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

        String[] header = request.getHeader(SecurityConstant.HEADER).split(" ");

        if (StringUtils.isEmpty(header)){
            chain.doFilter(request,response);
            return;
        }
        if (!header[0].equals(SecurityConstant.BEARER)){
            ResponseUtil.out(response,ResponseUtil.resultMap(false,403,"token 错误！请检查"));
            return;
        }
        try {
            UsernamePasswordAuthenticationToken authentication = getAuthentication(header[1], response);
            if (!StringUtils.isEmpty(authentication)){
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            }
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }catch (Exception e){
             logger.info("认证异常");
             e.getStackTrace();
        }
        chain.doFilter(request,response);
        // super.doFilterInternal(request, response, chain);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(String header,HttpServletResponse response){
        String username = "";

        List<GrantedAuthority> authorities = new ArrayList<>();

        try {
            Claims claims = Jwts
                    .parser()
                    .setSigningKey(SecurityConstant.JWT_SIGN_KEY)
                    .parseClaimsJws(header)
                    .getBody();
            username = claims.getSubject();

            // 读取缓存权限，直接读取数据
            AuthorityEntity authority = redisCacheProject.getCacheAuthority(StaticConstant.AUTHORITIES+username);
            if (!StringUtils.isEmpty(authority.getList())){
                List<String> list = authority.getList();
                for (String auth : list){
                    SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(auth);
                    authorities.add(simpleGrantedAuthority);
                }
            }else {
                // 否则从数据库读取权限
                authorities = securityUtils.getCurrUserPerms(username);
            }
        }catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException | SignatureException | IllegalArgumentException e ){
            logger.info("token 已经失效，请重新登录:");
            redisCacheProject.clearCacheAuthority(StaticConstant.AUTHORITIES + username);
            ResponseUtil.out(response,ResponseUtil.resultMap(false,401,"token 已经失效，请重新登录"));
            return null;
        }catch (Exception e){
            logger.error("权限不足 ：getAuthentication");
            redisCacheProject.clearCacheAuthority(StaticConstant.AUTHORITIES + username);
            ResponseUtil.out(response, ResponseUtil.resultMap(false, 500, "解析token错误"));
            return null;
        }

        if (!StringUtils.isEmpty(username)) {
            // 踩坑提醒 此处password不能为null
            User principal = new User(username, "", authorities);
            return new UsernamePasswordAuthenticationToken(principal, null, authorities);
        }else {
            return null;
        }
    }

}
