package org.example.sa.rbac.demo.entity.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.example.sa.rbac.demo.common.obj.PageParam;

import javax.validation.constraints.NotNull;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class UserPageUnauthorizedDto extends PageParam {

    private Long roleId;
    private String userName;
    private String name;
    private List<Long> deptIds;

}
