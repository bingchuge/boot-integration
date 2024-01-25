package org.example.sa.rbac.demo.entity.dto;

import lombok.Data;
import org.example.sa.rbac.demo.entity.SysRole;

@Data
public class SaveRoleMenuDto extends SysRole {

    private String menuIds;

}
