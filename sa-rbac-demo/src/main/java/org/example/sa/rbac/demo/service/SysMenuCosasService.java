package org.example.sa.rbac.demo.service;

import org.example.sa.rbac.demo.entity.SysMenu;
import org.example.sa.rbac.demo.entity.SysMenuCosas;
import com.baomidou.mybatisplus.extension.service.IService;
import org.example.sa.rbac.demo.entity.dto.MenuSaveDto;

import java.util.List;
import java.util.Set;

/**
 * <p>
 * 系统菜单 服务类
 * </p>
 *
 * @author bingchu
 * @since 2024-01-18
 */
public interface SysMenuCosasService extends IService<SysMenuCosas> {

    public List<SysMenuCosas> listMenu();

    void add(MenuSaveDto menuSaveDto);

    void update(MenuSaveDto menuSaveDto);

    List<SysMenuCosas> listWithAuth();

    List<SysMenuCosas> listByUserId(long userId);


    List<SysMenuCosas> listByRoleIds(Set<Long> roleIds);

}
