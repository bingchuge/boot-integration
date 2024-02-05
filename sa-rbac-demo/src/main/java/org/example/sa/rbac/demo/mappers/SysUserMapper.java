package org.example.sa.rbac.demo.mappers;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.example.sa.rbac.demo.entity.SysUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.example.sa.rbac.demo.entity.dto.UserPageDto;
import org.example.sa.rbac.demo.entity.dto.UserPageUnauthorizedDto;
import org.example.sa.rbac.demo.entity.vo.UserListVo;

import java.util.List;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author bingchu
 * @since 2024-01-09
 */
@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {

    Page<UserListVo> selectUserPage(@Param("userPageDto") UserPageDto userPageDto, @Param("userIds") List<Long> userIds, Page<SysUser> sysUserPage);

    Page<UserListVo> selectUnauthorizedUserPage(@Param("userPageUnauthorizedDto") UserPageUnauthorizedDto userPageUnauthorizedDto, @Param("notInUserIds") List<Long> notInUserIds, @Param("sysUserPage") Page<SysUser> sysUserPage);
}
