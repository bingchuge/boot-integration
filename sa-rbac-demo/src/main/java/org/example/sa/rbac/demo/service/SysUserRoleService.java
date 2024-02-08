package org.example.sa.rbac.demo.service;

import org.example.sa.rbac.demo.entity.SysUserRole;
import com.baomidou.mybatisplus.extension.service.IService;
import org.example.sa.rbac.demo.entity.dto.AuthUserDto;

import java.util.List;
import java.util.Set;

/**
 * <p>
 * 用户角色表 服务类
 * </p>
 *
 * @author bingchu
 * @since 2024-01-18
 */
public interface SysUserRoleService extends IService<SysUserRole> {

    Set<Long> getRoleIdsByUserId(Long userId);

    List<Long> getUserIdsByRoleId(Long roleId);

    void authUsers(AuthUserDto authUserDto);

    void unAuthUsers(AuthUserDto authUserDto);
}
