package com.example.security.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.io.PrintWriter;

/**
 * @ClassName WebAppSecurityConfig
 * @Author QiuKing
 * @EnableWebSecurity 开启security环境注解
 * @Date 2020/3/1916:15
 */
@Configuration
@EnableWebSecurity
public class WebAppSecurityConfig extends WebSecurityConfigurerAdapter {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    protected void configure(AuthenticationManagerBuilder builder) throws Exception {
        // 内存登录
        builder.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder())
                .withUser("qiuny")
                .password(new BCryptPasswordEncoder().encode("123456"))
                .roles("ADMIN")
                .and()
                .withUser("yangy")
                .password(new BCryptPasswordEncoder().encode("123456"))
                .roles("BOSS")
                .authorities("ADD","DELETE","UPDATE","SELECT")
                ;

        // super.configure(builder);
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
        * .formLogin(); // 开启默认登录
        * .loginPage("login.html") //设置登录页，提交表单action：login.html method="post"
        * .loginProcessingUrl() //登录地址：一旦开启就会覆盖 loginPage默认提交地址
        * .defaultSuccessUrl() // 默认登录成功要跳转的页面路径
        * .usernameParameter("userName").passwordParameter("passWord") // 修改默认登录表单属性字段名
        * .disable() 禁用csrf跨越请求功能，如果安全性高就可以用它
         * login_url:  http://localhost:9999/qiu/do/login?loginName=qiuny&loginPassword=123456
         * out_url:  http://localhost:9999/qiu/do/logout
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
                .formLogin()
                .loginProcessingUrl("/do/login")
                .usernameParameter("loginName")
                .passwordParameter("loginPassword")
                .defaultSuccessUrl("/user/loginSuccess")
                .and()
                .csrf()
                .disable()
                .logout()
                .logoutUrl("/do/logout")
                .logoutSuccessUrl("/user/logoutSuccess").permitAll();
//               .logoutSuccessHandler(((request, response, authentication) -> {
//                   PrintWriter writer = response.getWriter();
//                   writer.write("true");
//                   writer.close();
//               })).permitAll();

       //super.configure(security);
    }
}
