package org.example.sa.rbac.demo.controller;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.util.SaResult;
import org.example.sa.rbac.demo.entity.dto.LoginDto;
import org.example.sa.rbac.demo.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("login")
    public SaResult login(@RequestBody LoginDto loginDto) {
        SaTokenInfo loginInfo = loginService.login(loginDto);
        return SaResult.data(loginInfo);
    }

}
