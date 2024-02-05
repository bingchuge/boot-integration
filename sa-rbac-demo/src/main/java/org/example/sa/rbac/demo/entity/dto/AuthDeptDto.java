package org.example.sa.rbac.demo.entity.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Data
public class AuthDeptDto {

    private Long roleId;
    private List<Long> deptIds;

}
