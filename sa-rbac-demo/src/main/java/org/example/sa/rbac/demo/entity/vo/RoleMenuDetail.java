package org.example.sa.rbac.demo.entity.vo;

import lombok.Data;
import org.example.sa.rbac.demo.entity.SysMenuCosas;

import java.util.List;

@Data
public class RoleMenuDetail {

    private Long roleId;
    private String roleName;
    private Integer status;
    private Integer roleSort;

    private List<SysMenuCosas> menuList;

}
