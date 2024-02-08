package org.example.sa.rbac.demo.entity.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.example.sa.rbac.demo.common.obj.PageParam;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@EqualsAndHashCode(callSuper = true)
@Data
public class UserPageDto extends PageParam {

    private String userName;
    private String name;
    private Integer status;
    private Long deptId;

}
