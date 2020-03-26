package com.example.security;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SecurityApplicationTests {

    @Test
    public void contextLoads() {
        String pwd = "123456";
        String encode = new BCryptPasswordEncoder().encode(pwd);
        System.out.println("加密密码："+encode);

    }

}
