package org.example.sa.rbac.demo.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.example.sa.rbac.demo.entity.SysDept;
import org.example.sa.rbac.demo.entity.bo.TreeNode;
import org.example.sa.rbac.demo.mappers.SysDeptMapper;
import org.example.sa.rbac.demo.service.SysDeptRoleService;
import org.example.sa.rbac.demo.service.SysDeptService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.sa.rbac.demo.service.SysUserDeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 * 部门表 服务实现类
 * </p>
 *
 * @author bingchu
 * @since 2024-01-09
 */
@Service
public class SysDeptServiceImpl extends ServiceImpl<SysDeptMapper, SysDept> implements SysDeptService {

    @Autowired
    private SysDeptMapper sysDeptMapper;

    @Autowired
    private SysUserDeptService sysUserDeptService;

    @Autowired
    private SysDeptRoleService sysDeptRoleService;

    @Override
    public List<SysDept> getDeptList() {
        return this.list();
    }

    @Override
    public List<SysDept> excludeChild(Long deptId) {
        List<SysDept> depts = this.list();
        depts = depts.stream().filter(dept -> {
            if (dept.getAncestors() == null || dept.getAncestors().isEmpty()) {
                return true;
            }
            if (dept.getDeptId().equals(deptId)) {
                return false;
            }
            List<String> ancestors = Arrays.asList(dept.getAncestors().split(","));
            return !ancestors.contains(deptId.toString());
        }).collect(Collectors.toList());
        return depts;
    }

    @Override
    public void saveDept(SysDept sysDept) {
        if (sysDept.getDeptId() != null) {
            sysDeptMapper.updateById(sysDept);
            return;
        }
        String ancestors = sysDept.getAncestors();
        if (ancestors != null && sysDept.getParentId() != null) {
            ancestors += ",";
        } else if (sysDept.getParentId() != null){
            ancestors = "";
        }
        if (ancestors != null && sysDept.getParentId() != null) {
            ancestors += sysDept.getParentId().toString();
            sysDept.setAncestors(ancestors);
        }
        this.save(sysDept);
    }

    @Override
    @Transactional
    public void removeByIdAndChildren(Long deptId) {
        this.removeById(deptId);
        sysDeptMapper.deleteChildren(deptId);
    }

    @Override
    public List<TreeNode> getDeptTree() {
        List<SysDept> list = this.list();
        List<TreeNode> treeNodes = buildTree(list);
        return treeNodes;
    }

    @Override
    public List<Long> getUserIdsByDeptId(Integer deptId) {
        // 查找当前部门id的所有子部门id集合
        List<SysDept> sysDepts = this.getAllDept(deptId);
        List<Long> deptIds = sysDepts.stream().map(SysDept::getDeptId).collect(Collectors.toList());
        List<Long> userIds = sysUserDeptService.getUserIdsByDeptIds(deptIds);
        return userIds;
    }

    @Override
    public List<SysDept> getAllDept(Integer deptId) {
        return sysDeptMapper.selectAllDept(deptId);
    }

    @Override
    public void updateUserDept(String deptId, Long userId) {
        sysUserDeptService.updateUserDept(deptId, userId);
    }

    @Override
    public SysDept getDeptByUserId(Long userId) {
        Long deptId = sysUserDeptService.getDeptIdByUserId(userId);
        if (deptId != null) {
            return this.getById(deptId);
        }
        return null;
    }

    @Override
    public List<SysDept> listByRoleId(Long roleId) {
        List<Long> depts = sysDeptRoleService.getDepsIdsByRoleId(roleId);
        if (CollUtil.isEmpty(depts)) {
            return new ArrayList<>();
        }
        List<SysDept> sysDepts = this.listByDeptIds(depts);
        return sysDepts;
    }

    @Override
    public List<SysDept> listByDeptIds(List<Long> depts) {
        LambdaQueryWrapper<SysDept> wrapper = Wrappers.lambdaQuery();
        wrapper.in(SysDept::getDeptId, depts);
        return this.list(wrapper);
    }

    @Override
    public void listWithAuthority() {
        Long userId = 100L;
    }

    public static List<TreeNode> buildTree(List<SysDept> depts) {
        List<TreeNode> nodes = depts.stream()
                .map(dept -> new TreeNode(dept.getDeptId(), dept.getDeptName(), dept.getOrderNum()))
                .collect(Collectors.toList());

        Map<Long, TreeNode> nodeMap = new HashMap<>();
        nodes.forEach(node -> nodeMap.put(node.getId(), node));

        List<TreeNode> tree = new ArrayList<>();
        for (TreeNode node : nodes) {
            SysDept dept = findDeptById(depts, node.getId());
            if (dept.getParentId() == 0) {
                tree.add(node);
            } else {
                TreeNode parentNode = nodeMap.get(dept.getParentId());
                if (parentNode != null) {
                    parentNode.getChildren().add(node);
                }
            }
        }

        sortTree(tree);
        return tree;
    }

    private static void sortTree(List<TreeNode> tree) {
        tree.sort(Comparator.comparing(TreeNode::getOrderNum));
        tree.forEach(node -> sortTree(node.getChildren()));
    }

    private static SysDept findDeptById(List<SysDept> depts, Long id) {
        for (SysDept dept : depts) {
            if (dept.getDeptId().equals(id)) {
                return dept;
            }
        }
        return null;
    }
}
