package org.example.sa.rbac.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.example.sa.rbac.demo.entity.SysUserDept;
import org.example.sa.rbac.demo.mapper.SysDeptMapper;
import org.example.sa.rbac.demo.mapper.SysUserDeptMapper;
import org.example.sa.rbac.demo.service.SysUserDeptService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author bingchu
 * @since 2024-01-31
 */
@Service
public class SysUserDeptServiceImpl extends ServiceImpl<SysUserDeptMapper, SysUserDept> implements SysUserDeptService {

    @Autowired
    private SysDeptMapper sysDeptMapper;

    @Override
    public List<Long> getUserIdsByDeptIds(List<Long> deptIds) {
        LambdaQueryWrapper<SysUserDept> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.in(SysUserDept::getDeptId, deptIds);
        List<SysUserDept> list = this.list(queryWrapper);
        return list.stream().map(SysUserDept::getUserId).collect(Collectors.toList());
    }

    @Override
    public void updateUserDept(String deptId, Long userId) {
        LambdaQueryWrapper<SysUserDept> wrapperRemove = Wrappers.lambdaQuery();
        wrapperRemove.eq(SysUserDept::getUserId, userId)
                .eq(SysUserDept::getDeptId, deptId);
        this.remove(wrapperRemove);
        SysUserDept sysUserDept = new SysUserDept();
        sysUserDept.setUserId(userId);
        sysUserDept.setDeptId(Long.parseLong(deptId));
        this.save(sysUserDept);
    }

    @Override
    public Long getDeptIdByUserId(Long userId) {
        LambdaQueryWrapper<SysUserDept> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(SysUserDept::getUserId, userId);
        SysUserDept sysUserDept = this.getOne(queryWrapper);
        return sysUserDept == null ? null : sysUserDept.getDeptId();
    }
}
