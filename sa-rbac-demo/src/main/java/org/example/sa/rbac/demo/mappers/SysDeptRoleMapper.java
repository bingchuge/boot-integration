package org.example.sa.rbac.demo.mappers;

import org.example.sa.rbac.demo.entity.SysDeptRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 部门权限表 Mapper 接口
 * </p>
 *
 * @author bingchu
 * @since 2024-02-05
 */
@Mapper
public interface SysDeptRoleMapper extends BaseMapper<SysDeptRole> {

}
