package org.example.sa.rbac.demo.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.ListUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.example.sa.rbac.demo.entity.SysDept;
import org.example.sa.rbac.demo.entity.SysMenuCosas;
import org.example.sa.rbac.demo.entity.SysRole;
import org.example.sa.rbac.demo.entity.dto.AuthDeptDto;
import org.example.sa.rbac.demo.entity.dto.AuthUserDto;
import org.example.sa.rbac.demo.entity.dto.RolePageDto;
import org.example.sa.rbac.demo.entity.dto.SaveRoleMenuDto;
import org.example.sa.rbac.demo.entity.vo.RoleMenuDetail;
import org.example.sa.rbac.demo.mappers.SysRoleMapper;
import org.example.sa.rbac.demo.service.*;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>
 * 角色信息表 服务实现类
 * </p>
 *
 * @author bingchu
 * @since 2024-01-09
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {

    @Autowired
    private SysUserRoleService sysUserRoleService;

    @Autowired
    private SysMenuRoleService sysMenuRoleService;

    @Autowired
    private SysMenuCosasService sysMenuCosasService;
    @Autowired
    private SysDeptRoleService sysDeptRoleService;
    @Autowired
    private SysDeptService sysDeptService;

    @Override
    public Set<Long> getRoleIdsByUserId(Long userId) {
        return sysUserRoleService.getRoleIdsByUserId(userId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveRoleMenu(SaveRoleMenuDto roleMenuDto) {
        SysRole sysRole = BeanUtil.toBean(roleMenuDto, SysRole.class);
        if (sysRole.getRoleId() != null) {
            this.updateById(sysRole);
        } else {
            this.save(sysRole);
        }
        roleMenuDto.setRoleId(sysRole.getRoleId());
        // 保存角色菜单关系
        sysMenuRoleService.save(roleMenuDto);
    }

    @Override
    public Page<SysRole> getPage(RolePageDto rolePageDto) {
        LambdaQueryWrapper<SysRole> wrapper = Wrappers.lambdaQuery();
        wrapper.like(rolePageDto.getRoleName() != null ,SysRole::getRoleName, rolePageDto.getRoleName())
                .eq(rolePageDto.getStatus() != null, SysRole::getStatus, rolePageDto.getStatus())
                .orderByAsc(SysRole::getRoleSort);
        Page<SysRole> pageParam = new Page<>(rolePageDto.getPageNum(), rolePageDto.getPageSize());
        Page<SysRole> page = this.page(pageParam, wrapper);
        return page;
    }

    @Override
    public RoleMenuDetail getDetail(Long roleId) {
        SysRole sysRole = this.getById(roleId);
        RoleMenuDetail roleMenuDetail = BeanUtil.toBean(sysRole, RoleMenuDetail.class);
        List<Long> menuIds = sysMenuRoleService.getMenuIdsByRoleId(roleId);
        List<SysMenuCosas> sysMenuCosas = ListUtil.empty();
        if (CollUtil.isNotEmpty(menuIds)) {
            sysMenuCosas = sysMenuCosasService.listByIds(menuIds);
        }
        roleMenuDetail.setMenuList(sysMenuCosas);
        return roleMenuDetail;
    }

    @Override
    public void authUsers(AuthUserDto authUserDto) {
        sysUserRoleService.authUsers(authUserDto);
    }

    @Override
    public void unAuthUsers(AuthUserDto authUserDto) {
        sysUserRoleService.unAuthUsers(authUserDto);
    }

    @Override
    public void authDepts(AuthDeptDto authDeptDto) {
        sysDeptRoleService.authDepts(authDeptDto);
    }

    @Override
    public Set<Long> getRoleIds(Long userId) {
        SysDept dept = sysDeptService.getDeptByUserId(userId);
        List<SysDept> allDept = sysDeptService.getAllDept(dept.getDeptId());
        List<Long> depts = allDept.stream().map(SysDept::getDeptId).collect(Collectors.toList());
        Set<Long> roleIdsByDept = sysDeptRoleService.getRoleIdsByDeptIds(depts);
        // 个人角色
        Set<Long> roleIdsByUser = sysUserRoleService.getRoleIdsByUserId(userId);
        Set<Long> roleIds = new HashSet<>(roleIdsByDept);
        roleIds.addAll(roleIdsByUser);
        return roleIds;
    }

}
