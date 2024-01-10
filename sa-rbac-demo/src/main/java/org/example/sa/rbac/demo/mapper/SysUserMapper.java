package org.example.sa.rbac.demo.mapper;

import org.example.sa.rbac.demo.entity.SysUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

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

}
