package com.example.security.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.sql.DataSource;

/**
 * @ClassName WebAppSecurityConfig
 * @Author QiuKing
 * @EnableWebSecurity 开启security环境注解
 * @EnableGlobalMethodSecurity(prePostEnabled =true) 作用：是这些注解生效 @PreAuthorize("hasRole('ADMIN')")
 *     @PostAuthorize()
 *     @PreFilter()
 *     @PostFilter()
 * @Date 2020/3/1916:15
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled =true,securedEnabled=true,jsr250Enabled=true)
public class WebAppSecurityConfig extends WebSecurityConfigurerAdapter {
    /**
    * 描述: 注入数据源
    * @Author QiuKing
    * @Date 2020/3/26 15:35
    */
    @Autowired
    private DataSource dataSource;

    @Autowired
    private MyUserDetailsServiceImpl myUserDetailsService;


    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    protected void configure(AuthenticationManagerBuilder builder) throws Exception {
        // 1、内存登录
//        builder.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder())
//                .withUser("qiuny")
//                .password(new BCryptPasswordEncoder().encode("123456"))
//                .roles("ADMIN","学徒")
//                .and()
//                .withUser("yangy")
//                .password(new BCryptPasswordEncoder().encode("123456"))
//                .roles("BOSS")
//                .authorities("ADD","DELETE","UPDATE","SELECT","内门弟子")
//                ;

        // 2、数据库登录
        builder.userDetailsService(myUserDetailsService).passwordEncoder(passwordEncoderBean());

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
         * .logout() 开启退出功能
         *  .logoutUrl("/do/logout") 指定退出提交地址
         *  .logoutSuccessUrl("/user/logoutSuccess").permitAll() 退出成功访问地址
         * login_url:  http://localhost:9999/qiu/do/login?loginName=qiuny&loginPassword=123456
         * out_url:  http://localhost:9999/qiu/do/logout
         * .exceptionHandling() 访问拒绝开启
         * .accessDeniedPage("/error/no/auth") 访问被拒绝访问地址
        * @Author QiuKing
        * @Date 2020/3/19 17:11
        */

        security
                .authorizeRequests()
                .antMatchers("/user/test")
                .permitAll()
                .antMatchers("/user/level1")
                .hasRole("学徒")
                .antMatchers("/user/level2")
                .hasAuthority("内门弟子")
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS, "/**").anonymous()
                .anyRequest()
                .authenticated()
                .and()
               // .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                // 定制我们自己的 session 策略：调整为让 Spring Security 不创建和使用 session
                .formLogin()
                .loginPage("/user/unLogin")
                .permitAll()
                .loginProcessingUrl("/do/login")
                .permitAll()
                .usernameParameter("loginName")
                .passwordParameter("loginPassword")
                .defaultSuccessUrl("/user/loginSuccess")
                .and()
                .csrf()
                .disable()
                .logout()
                .logoutUrl("/do/logout")
                .logoutSuccessUrl("/user/logoutSuccess").permitAll()
                .and()
                .exceptionHandling()
                .accessDeniedPage("/error/no/auth")
                ;
          // security.addFilterBefore(authenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);
       //super.configure(security);
    }

    @Bean
    public PasswordEncoder passwordEncoderBean() {
        return new BCryptPasswordEncoder();
    }


}
