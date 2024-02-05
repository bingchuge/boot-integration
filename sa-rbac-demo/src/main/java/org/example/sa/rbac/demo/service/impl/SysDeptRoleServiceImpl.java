package org.example.sa.rbac.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.example.sa.rbac.demo.entity.SysDeptRole;
import org.example.sa.rbac.demo.entity.dto.AuthDeptDto;
import org.example.sa.rbac.demo.mappers.SysDeptRoleMapper;
import org.example.sa.rbac.demo.service.SysDeptRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 部门权限表 服务实现类
 * </p>
 *
 * @author bingchu
 * @since 2024-02-05
 */
@Service
public class SysDeptRoleServiceImpl extends ServiceImpl<SysDeptRoleMapper, SysDeptRole> implements SysDeptRoleService {

    @Override
    @Transactional
    public void authDepts(AuthDeptDto authDeptDto) {
        List<Long> deptIds = authDeptDto.getDeptIds();
        List<SysDeptRole> list = new ArrayList<>();
        for (Long deptId : deptIds) {
            SysDeptRole sysDeptRole = new SysDeptRole();
            sysDeptRole.setRoleId(authDeptDto.getRoleId());
            sysDeptRole.setDeptId(deptId);
            list.add(sysDeptRole);
        }
        // 先把旧数据删除
        removeByRoleId(authDeptDto.getRoleId());
        if (!list.isEmpty()) {
            this.saveBatch(list);
        }
    }

    public void removeByRoleId(Long RoleId) {
        LambdaQueryWrapper<SysDeptRole> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(SysDeptRole::getRoleId, RoleId);
        this.remove(wrapper);
    }

    @Override
    public List<Long> getDepsIdsByRoleId(Long roleId) {
        List<SysDeptRole> sysDeptRoles = listByRoleId(roleId);
        return sysDeptRoles.stream().map(SysDeptRole::getDeptId).collect(Collectors.toList());
    }

    private List<SysDeptRole> listByRoleId(Long roleId) {
        LambdaQueryWrapper<SysDeptRole> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(SysDeptRole::getRoleId, roleId);
        return this.list(wrapper);
    }
}
