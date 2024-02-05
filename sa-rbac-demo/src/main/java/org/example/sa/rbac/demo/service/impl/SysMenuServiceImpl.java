package org.example.sa.rbac.demo.service.impl;

import org.example.sa.rbac.demo.entity.SysMenu;
import org.example.sa.rbac.demo.mappers.SysMenuMapper;
import org.example.sa.rbac.demo.service.SysMenuRoleService;
import org.example.sa.rbac.demo.service.SysMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.sa.rbac.demo.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * <p>
 * 菜单表 服务实现类
 * </p>
 *
 * @author bingchu
 * @since 2024-01-09
 */
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements SysMenuService {

    @Autowired
    private SysMenuRoleService sysMenuRoleService;

    @Autowired
    private SysRoleService sysRoleService;;


    @Override
    public List<SysMenu> listByUserId(int userId) {
        Set<Long> roleIds = sysRoleService.getRoleIdsByUserId(userId);
        // 获取拥有菜单id
        List<Long> menuIds = sysMenuRoleService.getMenuIdsByRoleIds(roleIds);
        // 获取菜单集合
        List<SysMenu> sysMenus = this.listByIds(menuIds);
        return sysMenus;
    }
}
