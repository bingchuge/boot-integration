package org.example.sa.rbac.demo.controller;

import cn.dev33.satoken.util.SaResult;
import org.example.sa.rbac.demo.entity.SysDept;
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

    @GetMapping("/page")
    public SaResult page() {
        List<SysDept> list = sysDeptService.page();
        return SaResult.data(list);
    }

    @PostMapping("save")
    public SaResult save(@RequestBody SysDept sysDept) {
        sysDeptService.save(sysDept);
        return SaResult.ok();
    }

    @PostMapping("update")
    public SaResult update(@RequestBody SysDept sysDept) {
        sysDeptService.updateById(sysDept);
        return SaResult.ok();
    }

    @PostMapping("delete")
    public SaResult delete(@RequestBody SysDept sysDept) {
        sysDeptService.removeById(sysDept.getDeptId());
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

}
