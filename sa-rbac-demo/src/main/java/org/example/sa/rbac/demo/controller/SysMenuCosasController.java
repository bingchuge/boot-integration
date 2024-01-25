package org.example.sa.rbac.demo.controller;

import cn.dev33.satoken.util.SaResult;
import org.example.sa.rbac.demo.entity.SysMenuCosas;
import org.example.sa.rbac.demo.service.SysMenuCosasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 系统菜单 前端控制器
 * </p>
 *
 * @author bingchu
 * @since 2024-01-18
 */
@RestController
public class SysMenuCosasController {

    @Autowired
    private SysMenuCosasService sysMenuCosasService;

    @GetMapping("system/listmenu")
    public SaResult list() {
        List<SysMenuCosas> sysMenuCosas = sysMenuCosasService.listMenu();
        return SaResult.data(sysMenuCosas);
    }

}
