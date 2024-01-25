package org.example.sa.rbac.demo.service;

import org.example.sa.rbac.demo.entity.SysMenuRole;
import com.baomidou.mybatisplus.extension.service.IService;
import org.example.sa.rbac.demo.entity.dto.SaveRoleMenuDto;

import java.util.List;
import java.util.Set;

/**
 * <p>
 * 菜单权限表 服务类
 * </p>
 *
 * @author bingchu
 * @since 2024-01-09
 */
public interface SysMenuRoleService extends IService<SysMenuRole> {

    List<String> getMenuIdsByRoleIds(Set<Long> roleIds);

    void save(SaveRoleMenuDto roleMenuDto);

    void removeByRoleId(Long roleId);
}
