package org.example.sa.rbac.demo.controller;

import cn.dev33.satoken.util.SaResult;
import cn.hutool.core.bean.BeanUtil;
import org.example.sa.rbac.demo.entity.SysMenuCosas;
import org.example.sa.rbac.demo.entity.dto.MenuSaveDto;
import org.example.sa.rbac.demo.service.SysMenuCosasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("menu")
public class SysMenuCosasController {

    @Autowired
    private SysMenuCosasService sysMenuCosasService;

    @GetMapping("listmenu")
    public SaResult list() {
        List<SysMenuCosas> sysMenuCosas = sysMenuCosasService.listMenu();
        return SaResult.data(sysMenuCosas);
    }

    /**
     * 菜单添加
     *
     * @return
     */
    @PostMapping("add")
    public SaResult add(@RequestBody MenuSaveDto menuSaveDto) {
        sysMenuCosasService.add(menuSaveDto);
        return SaResult.ok();
    }

    @PostMapping("update")
    public SaResult update(@RequestBody MenuSaveDto menuSaveDto) {
        sysMenuCosasService.update(menuSaveDto);
        return SaResult.ok();
    }

    @DeleteMapping("{menu_id}")
    public SaResult delete(@PathVariable("menu_id") Long menuId) {
        sysMenuCosasService.removeById(menuId);
        return SaResult.ok();
    }


}
