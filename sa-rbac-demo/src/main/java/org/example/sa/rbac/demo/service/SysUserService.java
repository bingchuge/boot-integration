package org.example.sa.rbac.demo.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.example.sa.rbac.demo.entity.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;
import org.example.sa.rbac.demo.entity.dto.UserPageByRoleDto;
import org.example.sa.rbac.demo.entity.dto.UserPageDto;
import org.example.sa.rbac.demo.entity.dto.UserPageUnauthorizedDto;
import org.example.sa.rbac.demo.entity.dto.UserSaveDto;
import org.example.sa.rbac.demo.entity.vo.UserInfoVo;
import org.example.sa.rbac.demo.entity.vo.UserListVo;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author bingchu
 * @since 2024-01-09
 */
public interface SysUserService extends IService<SysUser> {

    /**
     * 根据用户名获取用户
     * @param username 用户名
     * @return {SysUser}
     */

    SysUser getByUsername(String username);

    Page<UserListVo> getPage(UserPageDto pageParam);

    void saveUser(UserSaveDto userSaveDto);

    UserInfoVo getUserInfo(Long userId);

    Page<UserListVo> getPageByRoleId(UserPageByRoleDto userPageByRoleDto);

    Page<UserListVo> getUnauthorizedUserPage(UserPageUnauthorizedDto userPageUnauthorizedDto);
}
