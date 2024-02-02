package org.example.sa.rbac.demo.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.example.sa.rbac.demo.entity.SysRole;
import org.example.sa.rbac.demo.entity.dto.RolePageDto;
import org.example.sa.rbac.demo.entity.dto.RoleSaveDto;
import org.example.sa.rbac.demo.entity.dto.SaveRoleMenuDto;
import org.example.sa.rbac.demo.mapper.SysRoleMapper;
import org.example.sa.rbac.demo.service.SysMenuRoleService;
import org.example.sa.rbac.demo.service.SysRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.sa.rbac.demo.service.SysUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

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

    @Override
    public Set<Long> getRoleIdsByUserId(int userId) {
        return sysUserRoleService.getRoleIdsByUserId(userId);
    }

    @Override
    public void saveRoleMenu(SaveRoleMenuDto roleMenuDto) {
        SysRole sysRole = BeanUtil.toBean(roleMenuDto, SysRole.class);
        this.save(sysRole);
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
        Page<SysRole> page = this.page(pageParam);
        return page;
    }


}
