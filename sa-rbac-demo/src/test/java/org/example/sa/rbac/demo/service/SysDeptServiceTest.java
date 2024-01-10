package org.example.sa.rbac.demo.service;

import org.example.sa.rbac.demo.SaRbacDemoApplication;
import org.example.sa.rbac.demo.entity.SysDept;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;


@SpringBootTest(classes = SaRbacDemoApplication.class)
class SysDeptServiceTest {

    @Autowired
    private SysDeptService service;

    /**
     * 测试查询所有部门
     */
    @Test
    public void test1() {
        List<SysDept> list = service.list();
        System.out.println(list);
    }

}
