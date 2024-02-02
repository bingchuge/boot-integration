package org.example.sa.rbac.demo.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.BCrypt;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.example.sa.rbac.demo.entity.SysDept;
import org.example.sa.rbac.demo.entity.SysUser;
import org.example.sa.rbac.demo.entity.dto.UserPageDto;
import org.example.sa.rbac.demo.entity.dto.UserSaveDto;
import org.example.sa.rbac.demo.entity.vo.UserInfoVo;
import org.example.sa.rbac.demo.entity.vo.UserListVo;
import org.example.sa.rbac.demo.mapper.SysUserMapper;
import org.example.sa.rbac.demo.service.SysDeptService;
import org.example.sa.rbac.demo.service.SysUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author bingchu
 * @since 2024-01-09
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    @Autowired
    private SysDeptService sysDeptService;
    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public SysUser getByUsername(String username) {
        LambdaQueryWrapper<SysUser> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(SysUser::getUserName, username);
        return this.getOne(wrapper);
    }

    @Override
    public Page<UserListVo> getPage(UserPageDto userPageDto) {
        LambdaQueryWrapper<SysUser> wrapper = Wrappers.lambdaQuery();
        List<Long> userIds = new ArrayList<>();
        if (userPageDto.getDeptId() != null) {
            // 查询部门有哪些用户
            userIds = sysDeptService.getUserIdsByDeptId(userPageDto.getDeptId());
            if (!userIds.isEmpty()) {
                wrapper.in(SysUser::getUserId, userIds);
            } else {
                return new Page<>();
            }
        }
        Page<UserListVo> userListVos = sysUserMapper.selectUserPage(userPageDto, userIds, new Page<SysUser>(userPageDto.getPageNum(), userPageDto.getPageSize()));
        return userListVos;
    }

    /**
     * TODO 权限没有控制,安全问题
     * @param userSaveDto
     */
    @Override
    @Transactional
    public void saveUser(UserSaveDto userSaveDto) {
        // 分开保存和新增
        if (userSaveDto.getUserId() == null) {
            Long userId = this.addUser(userSaveDto);
            userSaveDto.setUserId(userId);
        } else {
            // 修改
            this.updateUser(userSaveDto);
        }
        if (userSaveDto.getDeptId() != null) {
            // 部门修改
            sysDeptService.updateUserDept(userSaveDto.getDeptId(), userSaveDto.getUserId());
        }
    }

    @Override
    public UserInfoVo getUserInfo(Long userId) {
        SysUser sysUser = this.getById(userId);
        SysDept sysDept = sysDeptService.getDeptByUserId(userId);
        UserInfoVo userInfoVo = BeanUtil.toBean(sysUser, UserInfoVo.class);
        BeanUtil.copyProperties(sysDept, userInfoVo);
        return userInfoVo;
    }

    private void updateUser(UserSaveDto userSaveDto) {
        SysUser sysUser = BeanUtil.toBean(userSaveDto, SysUser.class);
        if (!StrUtil.isEmpty(userSaveDto.getPassword())) {
            sysUser = setPassword(userSaveDto);
        }
        this.updateById(sysUser);
    }

    private Long addUser(UserSaveDto userSaveDto) {
        SysUser byUsername = this.getByUsername(userSaveDto.getUserName());
        if (byUsername != null) {
            throw new RuntimeException("已有该账号！请换一个账号注册");
        }
        // 新增
        if (StrUtil.isEmpty(userSaveDto.getPassword())) {
            throw new RuntimeException("密码不能为空");
        }
        // 密码加密
        SysUser sysUser = setPassword(userSaveDto);
        this.save(sysUser);
        return sysUser.getUserId();
    }

    private static SysUser setPassword(UserSaveDto userSaveDto) {
        String salt = BCrypt.gensalt(10);
        String pw_hash = BCrypt.hashpw(userSaveDto.getPassword(), salt);
        SysUser sysUser = BeanUtil.toBean(userSaveDto, SysUser.class);
        sysUser.setPassword(pw_hash);
        sysUser.setSalt(salt);
        return sysUser;
    }
}
