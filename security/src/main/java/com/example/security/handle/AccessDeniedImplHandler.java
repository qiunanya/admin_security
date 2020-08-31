package com.example.security.handle;

import com.example.security.utils.ResponseUtil;
import com.example.security.utils.StaticConstant;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/**
  * <p>
  *  用户访问权限不足处理类
  * </p>
  *
  * @author qiu
  * @since 2020-04-97
  */
@Slf4j
@Component
public class AccessDeniedImplHandler implements AccessDeniedHandler {

    private final static Logger logger = LoggerFactory.getLogger(AccessDeniedImplHandler.class);

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException e) throws IOException, ServletException {
        logger.error("权限不足："+e.getMessage());
       if (isAjaxRequest(request)){
           ResponseUtil.out(response, ResponseUtil.resultMap(false, 403, StaticConstant.NO_AUTHORIZED));
       }else{
           ResponseUtil.out(response, ResponseUtil.resultMap(false, 403, StaticConstant.NO_AUTHORIZED));
       }
    }

    /**
     * 判断是否为 AJAX 请求
     *
     * @param request HttpServletRequest
     * @return boolean
     */
    private static boolean isAjaxRequest(HttpServletRequest request) {
        return (request.getHeader("X-Requested-With") != null && "XMLHttpRequest".equals(request.getHeader("X-Requested-With")));
    }
}
