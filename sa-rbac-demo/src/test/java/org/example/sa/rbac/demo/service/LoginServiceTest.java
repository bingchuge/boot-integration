package org.example.sa.rbac.demo.service;

import cn.dev33.satoken.stp.SaTokenInfo;
import org.example.sa.rbac.demo.SaRbacDemoApplication;
import org.example.sa.rbac.demo.entity.dto.LoginDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest(classes = SaRbacDemoApplication.class)
public class LoginServiceTest {

    @Autowired
    private LoginService loginService;
    @Test
    public void login() {
        LoginDto loginDto = new LoginDto();
        loginDto.setUsername("admin");
        loginDto.setPassword("123456");
        SaTokenInfo login = loginService.login(loginDto);
        System.out.println(login);
    }
}
