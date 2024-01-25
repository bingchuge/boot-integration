package org.example.sa.rbac.demo.service;

import cn.hutool.json.JSONUtil;
import org.example.sa.rbac.demo.SaRbacDemoApplication;
import org.example.sa.rbac.demo.entity.SysMenu;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest(classes = SaRbacDemoApplication.class)
class SysMenuServiceTest {

    @Autowired
    SysMenuService sysMenuService;

    @Test
    public void list() {
        List<SysMenu> sysMenus = sysMenuService.listByUserId(100);
        System.out.println(JSONUtil.toJsonStr(sysMenus));
    }

}
