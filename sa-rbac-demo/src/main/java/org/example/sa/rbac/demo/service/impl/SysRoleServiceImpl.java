package org.example.sa.rbac.demo.service.impl;

import cn.hutool.core.bean.BeanUtil;
import org.example.sa.rbac.demo.entity.SysRole;
import org.example.sa.rbac.demo.entity.dto.SaveRoleMenuDto;
import org.example.sa.rbac.demo.mapper.SysRoleMapper;
import org.example.sa.rbac.demo.service.SysMenuRoleService;
import org.example.sa.rbac.demo.service.SysRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.sa.rbac.demo.service.SysUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
