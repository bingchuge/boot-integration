package org.example.sa.rbac.demo.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.json.JSONUtil;
import org.example.sa.rbac.demo.SaRbacDemoApplication;
import org.example.sa.rbac.demo.entity.SysMenuCosas;
import org.example.sa.rbac.demo.entity.dto.MenuSaveDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = SaRbacDemoApplication.class)
public class SysMenuCosasServiceImplTest {

    @Autowired
    private SysMenuCosasServiceImpl sysMenuCosasService;

    @Test
    public void test() {
        MenuSaveDto menuSaveDto = new MenuSaveDto();
        menuSaveDto.setPid(null);
        menuSaveDto.setTitle("hehe");
        menuSaveDto.setMenu_sort(1);
        menuSaveDto.setType(1);
        menuSaveDto.setI_frame(true);
        menuSaveDto.setPath("adf/asdf");
        menuSaveDto.setComponent("asdf");

        SysMenuCosas sysMenuCosas = meneSaveDto2SysMenuCosas(menuSaveDto);
        System.out.println(JSONUtil.toJsonStr(sysMenuCosas));
    }

    private SysMenuCosas meneSaveDto2SysMenuCosas(MenuSaveDto menuSaveDto) {
        if (menuSaveDto.getPid() == null || menuSaveDto.getPid() == 100) {
            menuSaveDto.setPid(0L);
        }
        SysMenuCosas sysMenuCosas = BeanUtil.toBean(menuSaveDto, SysMenuCosas.class);
        // 截取url最后一个/后面的字符串
        String engName = menuSaveDto.getPath();
        if (engName.contains("/")) {
            engName = engName.substring(engName.lastIndexOf("/") + 1);
        }
        sysMenuCosas.setName(engName);
        return sysMenuCosas;
    }

}
