package org.example.sa.rbac.demo.entity.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class UserSaveDto {

    private Long userId;
    @NotBlank(message = "账号不能为空")
    @Size(min = 6, max = 30, message = "账号大小必须在6到30之间")
    private String userName;
    private String name;
    private String password;
    private Integer sex;
    private Integer status;
    // 为了方便，只支持一个部门
    private String deptId;

}
