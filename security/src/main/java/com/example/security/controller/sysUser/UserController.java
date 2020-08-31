package com.example.security.controller.sysUser;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.security.dto.user.FindUserDTO;
import com.example.security.entitys.SysUser;
import com.example.security.service.IUserService;
import com.example.security.utils.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
* 描述: 用户接口
* @Author QiuKing
* @Date 2020/3/19 10:09
*/

@RestController
@RequestMapping(value = "/sys_user")
@Api(tags = "用户接口")
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


    @GetMapping("/list")
    @PreAuthorize("hasAuthority('role:list')")
    @ApiOperation("获取用户列表")
    public ResultUtil list() {
        return userService.getList();
    }

    @PostMapping("/getPage")
    @PreAuthorize("hasAuthority('sys:user:page')")
    @ApiOperation("获取用户列表")
    public IPage<SysUser> getPage(@RequestBody FindUserDTO findUserDTO) {
        return userService.getPage(findUserDTO);
    }

    @GetMapping("/test")
    public ResultUtil test(){
        return  ResultUtil.success("不用登录就能访问我");
    }

    @GetMapping("/unLogin")
    public ResultUtil unLogin(){
        logger.info("未登录，请登录后在操作");
        return ResultUtil.error("未登录，请登录后在操作");
    }

}

