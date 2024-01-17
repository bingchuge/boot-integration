package org.example.sa.rbac.demo.service;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.crypto.digest.BCrypt;
import org.example.sa.rbac.demo.entity.SysUser;
import org.example.sa.rbac.demo.entity.dto.LoginDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    private SysUserService sysUserService;

    /**
     * 登录
     *
     * @param loginDto
     * @return
     */
    public SaTokenInfo login(LoginDto loginDto) {
        SysUser user = sysUserService.getByUsername(loginDto.getUsername());
        // 判断是否有该用户
        if (user == null) {
            throw new RuntimeException("用户不存在");
        }
        // 判断密码是否正确
        if (!user.getPassword().equals(BCrypt.hashpw(loginDto.getPassword(), user.getSalt()))) {
            throw new RuntimeException("密码错误");
        }
        // 账号是否被冻结
        if (user.getStatus() == 0) {
            throw new RuntimeException("账号被冻结");
        }
        StpUtil.login(user.getUserId());
        SaTokenInfo tokenInfo = StpUtil.getTokenInfo();
        return tokenInfo;
    }
}
