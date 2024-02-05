package org.example.sa.rbac.demo.entity.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.example.sa.rbac.demo.common.obj.PageParam;

@EqualsAndHashCode(callSuper = true)
@Data
public class UserPageByRoleDto extends PageParam {

    private String userName;
    private String name;
    private Integer status;
    private Long roleId;

}
