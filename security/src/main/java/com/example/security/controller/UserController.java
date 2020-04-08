package com.example.security.controller;


import com.example.security.service.IUserService;
import com.example.security.utils.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
* 描述: 用户接口
* @Author QiuKing
* @Date 2020/3/19 10:09
*/

@RestController
@RequestMapping(value = "/user")
public class UserController {
    @Value("${jwt.secret}")
    private String secret= null;
    /**
    * 描述: <br>获得日志
    * @Author QiuKing
    * @Date 2020/3/19 15:42
    */
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private IUserService userService;
    private Object principal;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;


    @RequestMapping("/level1")
    public ResultUtil level1(){
        logger.info("可以访问");
        return ResultUtil.success("恭喜你,你是学徒可以访问level1");
    }

    @RequestMapping("/level2")
    @PreAuthorize("hasAnyAuthority('ADMIN','BOSS')")
    public ResultUtil level2(){
        logger.info("内门弟子");
        return ResultUtil.success("恭喜你,你是‘内门弟子’可以访问level2");
    }

    @GetMapping("/list")
    @PreAuthorize("hasAuthority('role:list')")
    public ResultUtil list() {
        ResultUtil result = userService.getList();
        result.setTest_msg(secret+"只有管理员才能获取用户列表");
        return result;
    }

    @GetMapping("/test")
    public ResultUtil test(){
        stringRedisTemplate.opsForValue().append("qiu","hello world");
        return  ResultUtil.success("不需要登录也能访问我");
    }

    @RequestMapping("/loginSuccess")
    public ResultUtil login(@AuthenticationPrincipal UserDetails userDetails){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();

         logger.info("登录成功");
        return ResultUtil.loginSuccess(principal);
    }

    @RequestMapping("/logoutSuccess")
    public ResultUtil logout(HttpServletRequest request, HttpServletResponse response){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!StringUtils.isEmpty(auth)){
            new SecurityContextLogoutHandler().logout(request, response, auth);
            // 清除当前用户权限缓存

        }
        logger.info("退出成功");
        return ResultUtil.success("退出成功");
    }

    @RequestMapping("/unLogin")
    public ResultUtil unLogin(){
        logger.info("未登录，请登录后在操作");
        return ResultUtil.error("未登录，请登录后在操作");
    }

}

