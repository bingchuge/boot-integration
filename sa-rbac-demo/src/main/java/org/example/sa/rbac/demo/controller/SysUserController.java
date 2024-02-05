package org.example.sa.rbac.demo.controller;

import cn.dev33.satoken.util.SaResult;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.example.sa.rbac.demo.common.obj.PageParam;
import org.example.sa.rbac.demo.entity.SysUser;
import org.example.sa.rbac.demo.entity.dto.UserPageByRoleDto;
import org.example.sa.rbac.demo.entity.dto.UserPageDto;
import org.example.sa.rbac.demo.entity.dto.UserPageUnauthorizedDto;
import org.example.sa.rbac.demo.entity.dto.UserSaveDto;
import org.example.sa.rbac.demo.entity.vo.UserInfoVo;
import org.example.sa.rbac.demo.entity.vo.UserListVo;
import org.example.sa.rbac.demo.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author bingchu
 * @since 2024-01-09
 */
@RestController
@RequestMapping("/sysUser")
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;

    @GetMapping("page")
    public SaResult page(UserPageDto userPageDto) {
        Page<UserListVo> page = sysUserService.getPage(userPageDto);
        return SaResult.data(page);
    }

    /**
     * 保存
     * @param
     * @return
     */
    @PostMapping("save")
    public SaResult save(@RequestBody @Valid UserSaveDto userSaveDto) {
        sysUserService.saveUser(userSaveDto);
        return SaResult.ok();
    }

    @GetMapping("{userId}")
    public SaResult UserInfo(@PathVariable Long userId) {
        UserInfoVo userInfo = sysUserService.getUserInfo(userId);
        return SaResult.data(userInfo);
    }

    @DeleteMapping("{userId}")
    public SaResult delete(@PathVariable Long userId) {
        // TODO 没有验证相关权限
        sysUserService.removeById(userId);
        return SaResult.ok();
    }

    @GetMapping("/pageByRoleId")
    public SaResult pageByRoleId(UserPageByRoleDto userPageByRoleDto) {
        Page<UserListVo> sysUsers = sysUserService.getPageByRoleId(userPageByRoleDto);
        return SaResult.data(sysUsers);
    }

    @GetMapping("unauthorizedUserPage")
    public SaResult unauthorizedUserPage(@Valid UserPageUnauthorizedDto userPageUnauthorizedDto) {
        if (userPageUnauthorizedDto.getRoleId() == null || userPageUnauthorizedDto.getRoleId()== 0) {
            return SaResult.data(new Page<>());
        }
        Page<UserListVo> unauthorizedUserPage = sysUserService.getUnauthorizedUserPage(userPageUnauthorizedDto);
        return SaResult.data(unauthorizedUserPage);
    }

}
