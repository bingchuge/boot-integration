package org.example.sa.rbac.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.example.sa.rbac.demo.entity.SysMenuRole;
import org.example.sa.rbac.demo.entity.dto.SaveRoleMenuDto;
import org.example.sa.rbac.demo.mappers.SysMenuRoleMapper;
import org.example.sa.rbac.demo.service.SysMenuRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 * 菜单权限表 服务实现类
 * </p>
 *
 * @author bingchu
 * @since 2024-01-09
 */
@Service
public class SysMenuRoleServiceImpl extends ServiceImpl<SysMenuRoleMapper, SysMenuRole> implements SysMenuRoleService {

    @Autowired
    private SysMenuRoleMapper sysMenuRoleMapper;
    @Override
    public List<Long> getMenuIdsByRoleIds(Set<Long> roleIds) {
        LambdaQueryWrapper<SysMenuRole> wrapper = Wrappers.lambdaQuery();
        wrapper.in(SysMenuRole::getRoleId, roleIds);
        List<SysMenuRole> list = this.list(wrapper);
        List<Long> menuIds = list.stream().map(SysMenuRole::getMenuId).collect(Collectors.toList());
        return menuIds;
    }

    @Override
    @Transactional
    public void save(SaveRoleMenuDto roleMenuDto) {
        List<String> menuIds = splitTags(roleMenuDto.getMenuIds());
        List<SysMenuRole> records = new ArrayList<>();
        for (String menuId : menuIds) {
            // 转换成 sysmenurole实体类
            SysMenuRole sysMenuRole = new SysMenuRole();
            sysMenuRole.setRoleId(roleMenuDto.getRoleId());
            sysMenuRole.setMenuId(Long.valueOf(menuId));
            records.add(sysMenuRole);
        }
        // 先删除
        this.removeByRoleId(roleMenuDto.getRoleId());
        this.saveBatch(records);
    }

    @Override
    public void removeByRoleId(Long roleId) {
        LambdaQueryWrapper<SysMenuRole> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(SysMenuRole::getRoleId, roleId);
        this.remove(wrapper);
    }

    @Override
    public List<Long> getMenuIdsByRoleId(Long roleId) {
        LambdaQueryWrapper<SysMenuRole> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(SysMenuRole::getRoleId, roleId);
        List<SysMenuRole> list = this.list(wrapper);
        List<Long> menuIds = list.stream().map(SysMenuRole::getMenuId).collect(Collectors.toList());
        return menuIds;
    }

    private List<String> splitTags(String tagsStr) {
        if (tagsStr != null) {
            return Arrays.stream(tagsStr.split(","))
                    .map(String::trim)
                    .collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

}
