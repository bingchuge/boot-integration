package org.example.sa.rbac.demo.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 部门权限表
 * </p>
 *
 * @author bingchu
 * @since 2024-02-05
 */
@Getter
@Setter
@TableName("sys_dept_role")
public class SysDeptRole {

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 部门id
     */
    @TableField("dept_id")
    private Long deptId;

    /**
     * 角色id
     */
    @TableField("role_id")
    private Long roleId;

    /**
     * 创建者
     */
    @TableField("create_by")
    private String createBy;

    /**
     * 创建时间
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 更新者
     */
    @TableField("update_by")
    private String updateBy;

    /**
     * 更新时间
     */
    @TableField(value = "update_time", fill = FieldFill.UPDATE)
    private LocalDateTime updateTime;

    /**
     * 删除标志（0代表存在 1代表删除）
     */
    @TableField("del_flag")
    @TableLogic
    private Boolean delFlag;
}
