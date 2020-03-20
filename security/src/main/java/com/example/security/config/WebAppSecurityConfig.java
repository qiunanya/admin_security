package com.example.security.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @ClassName WebAppSecurityConfig
 * @Author QiuKing
 * @Date 2020/3/1916:15
 */
@Configuration
@EnableWebSecurity//开启security环境注解
public class WebAppSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder builder) throws Exception {

        super.configure(builder);
    }

    @Override
    protected void configure(HttpSecurity security) throws Exception {
        /**
        * 描述: <br>重写父类方法 configure
        * authorizeRequests()//对请求进行授权
        * .antMatchers("/test")//无条件访问路径
        * permitAll()//无条件访问路径
        * .authorizeRequests()//对请求进行授权
        * .anyRequest() // 任意请求
        * .authenticated();// 需要登录以后才可以访问
        * formLogin(); // 开启默认登录
        * .loginPage("login.html") //设置登录页，提交表单action：login.html method="post"
        * .loginProcessingUrl() //登录地址：一旦开启就会覆盖 loginPage默认值
        * .defaultSuccessUrl() // 默认登录成功要跳转的页面路径
        * .usernameParameter("userName").passwordParameter("passWord") // 修改默认登录表单属性字段名
        * @Author QiuKing
        * @Date 2020/3/19 17:11
        */

        security
                .authorizeRequests()
                .antMatchers("/user/test")
                .permitAll()

                .and()
                .authorizeRequests()
                .anyRequest()
                .authenticated()

                .and()
                .formLogin();

       //super.configure(security);
    }
}
