package com.example.security.controller;

import com.example.security.utils.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName ErrorController
 * @Author QiuKing
 * @Date 2020/3/2610:03
 */
@RestController
@RequestMapping(value = "/error")
public class ErrorController {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @RequestMapping("/no/auth")
    public ResultUtil test(){
        logger.info("对不起，您没有访问权限！");
        return  ResultUtil.error("对不起，您没有访问权限！");
    }

}
