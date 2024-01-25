package org.example.sa.rbac.demo.controller;

import cn.dev33.satoken.stp.StpUtil;
import org.example.sa.rbac.demo.service.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 菜单表 前端控制器
 * </p>
 *
 * @author bingchu
 * @since 2024-01-09
 */
@RestController
@RequestMapping("/sysMenu")
public class SysMenuController {

    @Autowired
    private SysMenuService sysMenuService;

    /**
     * 获取菜单列表
     */
    @GetMapping("list")
    public void list() {
        sysMenuService.listByUserId(StpUtil.getLoginId(0));
    }


}
