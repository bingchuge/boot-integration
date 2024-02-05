package org.example.sa.rbac.demo.mappers;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.example.sa.rbac.demo.entity.SysDept;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

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

    @Update("update sys_dept set del_flag = 1 where find_in_set(#{deptId}, ancestors)")
    void deleteChildren(@Param("deptId") Long deptId);

//    @Select("select * from sys_dept where find_in_set(ancestors, #{deptId}) or dept_id = #{deptId}")
    List<SysDept> selectAllDept(@Param("deptId") Integer deptId);
}
