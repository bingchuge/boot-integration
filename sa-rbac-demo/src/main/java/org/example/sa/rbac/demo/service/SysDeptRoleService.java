package org.example.sa.rbac.demo.service;

import org.example.sa.rbac.demo.entity.SysDeptRole;
import com.baomidou.mybatisplus.extension.service.IService;
import org.example.sa.rbac.demo.entity.dto.AuthDeptDto;

import java.util.List;

/**
 * <p>
 * 部门权限表 服务类
 * </p>
 *
 * @author bingchu
 * @since 2024-02-05
 */
public interface SysDeptRoleService extends IService<SysDeptRole> {

    void authDepts(AuthDeptDto authDeptDto);

    void removeByRoleId(Long RoleId);

    List<Long> getDepsIdsByRoleId(Long roleId);
}
