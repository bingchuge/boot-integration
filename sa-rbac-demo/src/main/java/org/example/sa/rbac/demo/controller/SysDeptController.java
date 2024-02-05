package org.example.sa.rbac.demo.controller;

import cn.dev33.satoken.util.SaResult;
import org.example.sa.rbac.demo.entity.SysDept;
import org.example.sa.rbac.demo.entity.bo.TreeNode;
import org.example.sa.rbac.demo.service.SysDeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 部门表 前端控制器
 * </p>
 *
 * @author bingchu
 * @since 2024-01-09
 */
@RestController
@RequestMapping("/sysDept")
public class SysDeptController {

    @Autowired
    private SysDeptService sysDeptService;

    @GetMapping("/list")
    public SaResult list() {
        List<SysDept> list = sysDeptService.getDeptList();
        return SaResult.data(list);
    }

    @GetMapping("tree")
    public SaResult tree() {
        List<TreeNode> deptTree = sysDeptService.getDeptTree();
        return SaResult.data(deptTree);
    }

    @GetMapping("excludeChild/{deptId}")
    public SaResult excludeChild(@PathVariable Long deptId) {
        List<SysDept> depts = sysDeptService.excludeChild(deptId);
        return SaResult.data(depts);
    }

    @PostMapping("save")
    public SaResult save(@RequestBody SysDept sysDept) {
        sysDeptService.saveDept(sysDept);
        return SaResult.ok();
    }

    @PostMapping("update")
    public SaResult update(@RequestBody SysDept sysDept) {
        sysDeptService.updateById(sysDept);
        return SaResult.ok();
    }

    @PostMapping("delete")
    public SaResult delete(@RequestBody SysDept sysDept) {
        sysDeptService.removeByIdAndChildren(sysDept.getDeptId());
        return SaResult.ok();
    }

    @GetMapping("get")
    public SaResult get(@RequestBody SysDept sysDept) {
        SysDept dept = sysDeptService.getById(sysDept.getDeptId());
        return SaResult.data(dept);
    }

    @GetMapping(value = "/{deptId}")
    public SaResult getById(@PathVariable Long deptId) {
        SysDept dept = sysDeptService.getById(deptId);
        return SaResult.data(dept);
    }

    @GetMapping("listByRoleId")
    public SaResult listByRoleId(Long roleId) {
        List<SysDept> sysDepts = sysDeptService.listByRoleId(roleId);
        return SaResult.data(sysDepts);
    }

    @GetMapping("listWithAuthority")
    public SaResult listWithAuthority() {
        sysDeptService.listWithAuthority();
        return SaResult.ok();
    }

}
