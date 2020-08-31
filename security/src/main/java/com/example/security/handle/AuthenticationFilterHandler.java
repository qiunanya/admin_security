package com.example.security.handle;

import com.example.security.utils.ResponseUtil;
import com.example.security.utils.SecurityConstant;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.util.StringUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
  * <p>
  *  权限过滤认证
  * </p>
  *
  * @author qiu
  * @since 2020-04-07
  */
public class AuthenticationFilterHandler extends BasicAuthenticationFilter {

    private static final Logger logger = LoggerFactory.getLogger(AuthenticationFilterHandler.class);
    public AuthenticationFilterHandler(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String[] header = request.getHeader(SecurityConstant.HEADER).split(" ");
        // 如果请求头中没有Authorization 直接放行
        if (StringUtils.isEmpty(header)) {
            chain.doFilter(request, response);
            return;
        }
        if (!header[0].equals(SecurityConstant.BEARER)) {
            ResponseUtil.out(response, ResponseUtil.resultMap(false, 403, "token 错误！请检查"));
            return;
        }
        try {
            Claims claims = Jwts
                    .parser()
                    .setSigningKey(SecurityConstant.JWT_SIGN_KEY)
                    .parseClaimsJws(header[1])
                    .getBody();
        } catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException | SignatureException | IllegalArgumentException e) {
            logger.info("token 已经失效，请重新登录:");
            ResponseUtil.out(response, ResponseUtil.resultMap(false, 401, "token 已经失效，请重新登录"));
        } catch (Exception e) {
            logger.error("权限不足 ：getAuthentication");
            ResponseUtil.out(response, ResponseUtil.resultMap(false, 500, "解析token错误"));
        }
        chain.doFilter(request, response);
    }
}

