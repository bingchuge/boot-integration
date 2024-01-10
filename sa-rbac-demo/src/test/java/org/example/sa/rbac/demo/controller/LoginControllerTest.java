package org.example.sa.rbac.demo.controller;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.crypto.digest.BCrypt;
import org.example.sa.rbac.demo.SaRbacDemoApplication;
import org.example.sa.rbac.demo.entity.SysUser;
import org.example.sa.rbac.demo.service.SysUserService;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = SaRbacDemoApplication.class)
public class LoginControllerTest {

    @Autowired
    private SysUserService userService;

    /**
     * 测试登录
     */
    @Test
    public void login() {
        String username = "admin";
        String password = "123456";

        SysUser user = userService.getByUsername(username);
        // 如果用户不存在
        if (user == null) {
            System.out.println("用户不存在");
            return;
        }
        Assertions.assertNotNull(user.getPassword());
        // 一系列检查，省略代码
        // 比较密码, 如果不相等
        if (!user.getPassword().equals(BCrypt.hashpw(password, user.getSalt()))) {
            return;
        }
        // 登录成功
        StpUtil.login(user.getUserId());
        SaTokenInfo tokenInfo = StpUtil.getTokenInfo();
        System.out.println(tokenInfo);
    }

    @Test
    public void encrypt() {
        // 使用方法
        String plain_password = "123123123112312312311231231231123123123112312312311231231231";
        String salt = BCrypt.gensalt(10);
        String pw_hash = BCrypt.hashpw(plain_password, salt);
        String pw_hash2 = BCrypt.hashpw(plain_password, salt);
        System.out.println(pw_hash);
        // 使用checkpw方法检查被加密的字符串是否与原始字符串匹配：
        boolean checkpw = BCrypt.checkpw(salt, pw_hash);

        // gensalt方法提供了可选参数 (log_rounds) 来定义加盐多少，也决定了加密的复杂度:
    }

}
