package com.example.security.controller;


import com.example.security.service.IUserService;
import com.example.security.utils.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

/**
* 描述: 用户接口
* @Author QiuKing
* @Date 2020/3/19 10:09
*/

@RestController
@RequestMapping(value = "/user")
public class UserController {
    @Value("${jwt.sorket}")
    private String sorket= null;
    /**
    * 描述: <br>获得日志
    * @Author QiuKing
    * @Date 2020/3/19 15:42
    */
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private IUserService userService;

    @GetMapping("/list")
    public ResultUtil list() {

        ResultUtil result = userService.getList();
        result.setTest_msg(sorket);
        return result;
    }

    @GetMapping("/test")
    public ResultUtil test(){
        return  ResultUtil.success("不需要登录也能访问我");
    }

    @RequestMapping("/loginSuccess")
    public ResultUtil login(){
         logger.info("登录成功");
        return ResultUtil.success("恭喜你,登录成功");
    }

    @RequestMapping("/logoutSuccess")
    public ResultUtil logout(){
        logger.info("退出成功");
        return ResultUtil.success("退出成功");
    }

}

