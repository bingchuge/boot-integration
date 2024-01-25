package org.example.sa.rbac.demo.service;

import org.example.sa.rbac.demo.entity.SysMenuCosas;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

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

}
