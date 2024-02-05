package org.example.sa.rbac.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.example.sa.rbac.demo.entity.SysUserRole;
import org.example.sa.rbac.demo.entity.dto.AuthUserDto;
import org.example.sa.rbac.demo.mappers.SysUserRoleMapper;
import org.example.sa.rbac.demo.service.SysMenuRoleService;
import org.example.sa.rbac.demo.service.SysUserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 * 用户角色表 服务实现类
 * </p>
 *
 * @author bingchu
 * @since 2024-01-18
 */
@Service
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleMapper, SysUserRole> implements SysUserRoleService {

    @Autowired
    private SysMenuRoleService sysMenuRoleService;

    @Override
    public Set<Long> getRoleIdsByUserId(int userId) {
        LambdaQueryWrapper<SysUserRole> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(SysUserRole::getUserId, userId);
        List<SysUserRole> list = this.list(wrapper);
        Set<Long> roleIds = list.stream().map(SysUserRole::getRoleId).collect(Collectors.toSet());
        return roleIds;
    }

    @Override
    public List<Long> getUserIdsByRoleId(Long roleId) {
        LambdaQueryWrapper<SysUserRole> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(SysUserRole::getRoleId, roleId);
        List<SysUserRole> list = this.list(wrapper);
        List<Long> userList = list.stream().map(SysUserRole::getUserId).collect(Collectors.toList());
        return userList;
    }

    @Override
    public void authUsers(AuthUserDto authUserDto) {
        List<Long> userIds = authUserDto.getUserIds();
        List<SysUserRole> list = new ArrayList<>();
        for (Long userId : userIds) {
            SysUserRole sysUserRole = new SysUserRole();
            sysUserRole.setRoleId(authUserDto.getRoleId());
            sysUserRole.setUserId(userId);
            list.add(sysUserRole);
        }
        this.saveBatch(list);
    }

    @Override
    public void unAuthUsers(AuthUserDto authUserDto) {
        LambdaUpdateWrapper<SysUserRole> wrapper = Wrappers.lambdaUpdate();
        wrapper.eq(SysUserRole::getRoleId, authUserDto.getRoleId())
                .in(SysUserRole::getUserId, authUserDto.getUserIds());
        this.remove(wrapper);
    }


}
