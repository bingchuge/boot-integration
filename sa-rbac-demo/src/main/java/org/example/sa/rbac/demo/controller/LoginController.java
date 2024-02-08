package org.example.sa.rbac.demo.controller;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import org.example.sa.rbac.demo.entity.dto.LoginDto;
import org.example.sa.rbac.demo.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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

    @PostMapping("logout")
    public SaResult logout() {
        StpUtil.logout();
        return SaResult.ok();
    }

    @GetMapping("isLogin")
    public SaResult isLogin() {
        boolean login = StpUtil.isLogin();
        if (login) {
            return SaResult.ok();
        }
        else {
            return SaResult.code(401);
        }
    }

}
