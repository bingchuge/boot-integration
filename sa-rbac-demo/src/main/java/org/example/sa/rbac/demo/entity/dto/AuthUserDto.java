package org.example.sa.rbac.demo.entity.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
public class AuthUserDto {

    private Long roleId;

    @NotEmpty(message = "选择用户不能为空")
    @Size(min = 1, message = "选择用户必须大于0")
    private List<Long> userIds;

}
