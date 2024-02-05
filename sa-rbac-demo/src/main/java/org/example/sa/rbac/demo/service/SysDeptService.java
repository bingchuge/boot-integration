package org.example.sa.rbac.demo.service;

import org.example.sa.rbac.demo.entity.SysDept;
import com.baomidou.mybatisplus.extension.service.IService;
import org.example.sa.rbac.demo.entity.bo.TreeNode;

import java.util.List;

/**
 * <p>
 * 部门表 服务类
 * </p>
 *
 * @author bingchu
 * @since 2024-01-09
 */
public interface SysDeptService extends IService<SysDept> {

    List<SysDept> getDeptList();

    List<SysDept> excludeChild(Long deptId);

    void saveDept(SysDept sysDept);

    void removeByIdAndChildren(Long deptId);

    List<TreeNode> getDeptTree();

    List<Long> getUserIdsByDeptId(Integer deptId);

    List<SysDept> getAllDept(Integer deptId);

    void updateUserDept(String deptId, Long userId);

    SysDept getDeptByUserId(Long userId);

    List<SysDept> listByRoleId(Long roleId);

    List<SysDept> listByDeptIds(List<Long> menuIds);
}
