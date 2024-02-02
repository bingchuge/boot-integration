package org.example.sa.rbac.demo.entity.dto;

import lombok.Data;
import org.example.sa.rbac.demo.common.obj.PageParam;

@Data
public class RolePageDto extends PageParam {

    private String roleName;
    private Integer status;

}
