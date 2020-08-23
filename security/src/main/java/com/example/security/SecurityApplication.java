package com.example.security;

import com.github.xiaoymin.swaggerbootstrapui.annotations.EnableSwaggerBootstrapUI;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
* 描述: <br>
* @Author QiuKing
* @Date 2020/3/19 11:37
*/
@SpringBootApplication
@EnableSwagger2
@EnableSwaggerBootstrapUI
@MapperScan("com.example.security.mapper")
public class SecurityApplication {

    public static void main(String[] args) {
        SpringApplication.run(SecurityApplication.class, args);
    }

}
