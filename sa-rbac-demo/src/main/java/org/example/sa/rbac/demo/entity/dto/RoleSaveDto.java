package org.example.sa.rbac.demo.entity.dto;

import lombok.Data;
import org.example.sa.rbac.demo.entity.SysRole;

import java.util.List;

@Data
public class RoleSaveDto extends SysRole {

    // 菜单id列表
    private List<Long> menuIds;
    // 权限id列表

}
