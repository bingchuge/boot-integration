package org.example.sa.rbac.demo.service.impl;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.example.sa.rbac.demo.SaRbacDemoApplication;
import org.example.sa.rbac.demo.entity.SysRole;
import org.example.sa.rbac.demo.entity.dto.RolePageDto;
import org.example.sa.rbac.demo.service.SysRoleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = SaRbacDemoApplication.class)
class SysRoleServiceImplTest {

    @Autowired
    private SysRoleService sysRoleService;

    @Test
    public void listTest() {
        RolePageDto rolePageDto = new RolePageDto();
        Page<SysRole> page = sysRoleService.getPage(rolePageDto);
        System.out.println(JSONUtil.toJsonStr(page));
    }
}
