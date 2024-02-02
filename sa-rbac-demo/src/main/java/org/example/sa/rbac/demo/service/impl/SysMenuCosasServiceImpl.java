package org.example.sa.rbac.demo.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.example.sa.rbac.demo.entity.SysMenuCosas;
import org.example.sa.rbac.demo.entity.dto.MenuSaveDto;
import org.example.sa.rbac.demo.mapper.SysMenuCosasMapper;
import org.example.sa.rbac.demo.service.SysMenuCosasService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
