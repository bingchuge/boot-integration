package org.example.sa.rbac.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.example.sa.rbac.demo.entity.SysMenuCosas;
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
}
