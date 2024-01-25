package org.example.sa.rbac.demo;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = {"org.example.sa.rbac.demo.mapper"})
public class SaRbacDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SaRbacDemoApplication.class, args);
        System.out.println("SaRbacDemoApplication 启动成功");
    }

}
