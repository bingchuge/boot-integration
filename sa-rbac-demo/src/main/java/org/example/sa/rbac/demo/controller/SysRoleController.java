package org.example.sa.rbac.demo.controller;

import cn.dev33.satoken.util.SaResult;
import org.example.sa.rbac.demo.entity.SysRole;
import org.example.sa.rbac.demo.entity.dto.RolePageDto;
import org.example.sa.rbac.demo.entity.dto.RoleSaveDto;
import org.example.sa.rbac.demo.entity.dto.SaveRoleMenuDto;
import org.example.sa.rbac.demo.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * <p>
 * 角色信息表 前端控制器
 * </p>
 *
 * @author bingchu
 * @since 2024-01-09
 */
@RestController
@RequestMapping("/sysRole")
public class SysRoleController {

    @Autowired
    private SysRoleService sysRoleService;

    /**
     * 角色列表
     *
     * @return
     */
    @GetMapping("list")
    public SaResult list() {
        List<SysRole> list = sysRoleService.list();
        return SaResult.data(list);
    }

    @GetMapping("page")
    public SaResult page(RolePageDto rolePageDto) {
        return SaResult.data(sysRoleService.getPage(rolePageDto));
    }


    /**
     * @Description: 根据角色id获取角色信息
     * @param roleId
     * @return
     */
    @GetMapping("get")
    public SaResult get(@RequestParam("roleId") Long roleId) {
        SysRole sysRole = sysRoleService.getById(roleId);
        return SaResult.data(sysRole);
    }

    /**
     * @Description: 保存角色
     */
    @PostMapping("saveRoleMenu")
    public SaResult saveRoleMenu(@RequestBody @Valid SaveRoleMenuDto roleMenuDto) {
        sysRoleService.saveRoleMenu(roleMenuDto);
        return SaResult.ok();
    }

    /**
     * 根据角色ID删除角色信息
     *
     * @param roleId 角色ID
     * @return 返回操作结果
     */
    @PostMapping("{roleId}")
    public SaResult delete(@PathVariable("roleId") Long roleId) {
        sysRoleService.removeById(roleId);
        return SaResult.ok();
    }

}
