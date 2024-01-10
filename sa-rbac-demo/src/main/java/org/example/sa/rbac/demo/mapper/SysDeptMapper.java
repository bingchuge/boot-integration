package org.example.sa.rbac.demo.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.example.sa.rbac.demo.entity.SysDept;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 部门表 Mapper 接口
 * </p>
 *
 * @author bingchu
 * @since 2024-01-09
 */
@Mapper
public interface SysDeptMapper extends BaseMapper<SysDept> {

}
