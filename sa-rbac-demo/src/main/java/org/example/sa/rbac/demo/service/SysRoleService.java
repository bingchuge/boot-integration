package org.example.sa.rbac.demo.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.example.sa.rbac.demo.entity.SysRole;
import com.baomidou.mybatisplus.extension.service.IService;
import org.example.sa.rbac.demo.entity.dto.RolePageDto;
import org.example.sa.rbac.demo.entity.dto.RoleSaveDto;
import org.example.sa.rbac.demo.entity.dto.SaveRoleMenuDto;

import java.util.Set;

/**
 * <p>
 * 角色信息表 服务类
 * </p>
 *
 * @author bingchu
 * @since 2024-01-09
 */
public interface SysRoleService extends IService<SysRole> {

    Set<Long> getRoleIdsByUserId(int userId);

    void saveRoleMenu(SaveRoleMenuDto roleMenuDto);

    Page<SysRole> getPage(RolePageDto rolePageDto);
}
