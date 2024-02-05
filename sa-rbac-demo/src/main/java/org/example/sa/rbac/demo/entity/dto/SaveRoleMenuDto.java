package org.example.sa.rbac.demo.entity.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import org.example.sa.rbac.demo.entity.SysRole;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class SaveRoleMenuDto {

    private String menuIds;
    /**
     * 角色ID
     */
    private Long roleId;

    /**
     * 角色名称
     */
    @NotNull(message = "角色名称不能为空")
    @Size(min = 1, message = "角色名称长度必须大于0")
    private String roleName;

    /**
     * 显示顺序
     */
    private Byte roleSort;

    /**
     * 角色状态（1正常 0停用）
     */
    private Integer status;

}
