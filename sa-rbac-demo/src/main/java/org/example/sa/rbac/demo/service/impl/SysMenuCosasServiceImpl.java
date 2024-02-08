package org.example.sa.rbac.demo.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.example.sa.rbac.demo.entity.SysMenu;
import org.example.sa.rbac.demo.entity.SysMenuCosas;
import org.example.sa.rbac.demo.entity.dto.MenuSaveDto;
import org.example.sa.rbac.demo.mappers.SysMenuCosasMapper;
import org.example.sa.rbac.demo.service.*;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <p>
 * 系统菜单 服务实现类
 * </p>
 *
 * @author bingchu
 * @since 2024-01-18
 */
@Service
public class SysMenuCosasServiceImpl extends ServiceImpl<SysMenuCosasMapper, SysMenuCosas> implements SysMenuCosasService {

    @Autowired
    private SysDeptService sysDeptService;
    @Autowired
    private SysRoleService sysRoleService;
    @Autowired
    private SysMenuService sysMenuService;
    @Autowired
    private SysMenuRoleService sysMenuRoleService;

    @Override
    public List<SysMenuCosas> listMenu() {
        LambdaQueryWrapper<SysMenuCosas> wrapper = new LambdaQueryWrapper<>();
        wrapper.orderByAsc(SysMenuCosas::getMenu_sort);
        return this.list(wrapper);
    }

    @Override
    public void add(MenuSaveDto menuSaveDto) {
        menuSaveDto.setMenu_id(null);
        SysMenuCosas sysMenuCosas = meneSaveDto2SysMenuCosas(menuSaveDto);
        this.save(sysMenuCosas);
    }

    @Override
    public void update(MenuSaveDto menuSaveDto) {
        SysMenuCosas sysMenuCosas = meneSaveDto2SysMenuCosas(menuSaveDto);
        this.updateById(sysMenuCosas);
    }

    @Override
    public List<SysMenuCosas> listWithAuth() {
        Long userId = StpUtil.getLoginId(0L);
        // 获取角色列表
        Set<Long> roleIds = sysRoleService.getRoleIds(userId);
        // 获取当前用户能访问的列表
        List<SysMenuCosas> sysMenus = listByRoleIds(roleIds);
        // TODO
        Set<Long> pids = new HashSet<>();
        for (SysMenuCosas sysMenu : sysMenus) {
            Long pid = sysMenu.getPid();
            pids.add(pid);
        }
        LambdaQueryWrapper<SysMenuCosas> wrapper = new LambdaQueryWrapper<>();
        wrapper.in(SysMenuCosas::getMenu_id, pids)
                .orderByAsc(SysMenuCosas::getMenu_sort);
        List<SysMenuCosas> list = this.list(wrapper);
        list.addAll(sysMenus);
        return list;
    }

    private SysMenuCosas meneSaveDto2SysMenuCosas(MenuSaveDto menuSaveDto) {
        if (menuSaveDto.getPid() == null || menuSaveDto.getPid() == 100) {
            menuSaveDto.setPid(0L);
        }
        SysMenuCosas sysMenuCosas = BeanUtil.toBean(menuSaveDto, SysMenuCosas.class);
        // 截取url最后一个/后面的字符串
        String engName = menuSaveDto.getPath();
        if (engName.contains("/")) {
            engName = engName.substring(engName.lastIndexOf("/") + 1);
        }
        sysMenuCosas.setName(engName);
        return sysMenuCosas;
    }

    @Override
    public List<SysMenuCosas> listByUserId(long userId) {
        Set<Long> roleIds = sysRoleService.getRoleIdsByUserId(userId);
        // 获取拥有菜单id
        List<Long> menuIds = sysMenuRoleService.getMenuIdsByRoleIds(roleIds);
        // 获取菜单集合
        List<SysMenuCosas> sysMenus = this.listByIds(menuIds);
        return sysMenus;
    }

    @Override
    public List<SysMenuCosas> listByRoleIds(Set<Long> roleIds) {
        List<Long> menuIds = sysMenuRoleService.getMenuIdsByRoleIds(roleIds);
        if (CollUtil.isEmpty(menuIds)) {
            return new ArrayList<>();
        }
        List<SysMenuCosas> sysMenus = this.listByIds(menuIds);
        return sysMenus;
    }
}
