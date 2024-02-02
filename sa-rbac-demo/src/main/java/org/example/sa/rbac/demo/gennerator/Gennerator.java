package org.example.sa.rbac.demo.gennerator;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.baomidou.mybatisplus.generator.fill.Column;
import org.apache.ibatis.annotations.Mapper;

import java.sql.Types;
import java.util.Collections;

/**
 * 表-类生成器
 */
public class Gennerator {

    public static void main(String[] args) {
        String tableNames = "sys_user_dept";

        // 数据库链接
        String url = "jdbc:mysql://127.0.0.1:3306/sarbac?useUnicode=true&characterEncoding=utf8&serverTimezone=GMT%2B8";
        String username = "root";
        String password = "123456";

        FastAutoGenerator.create(url, username, password)
                .globalConfig(builder -> {
                    builder.author("bingchu") // 设置作者
//                            .enableSwagger() // 开启 swagger 模式
//                            .fileOverride() // 覆盖已生成文件
                            .disableOpenDir() // 禁用打开输出目录
                            .outputDir(System.getProperty("user.dir") + "/sa-rbac-demo/src/main/java"); // 指定输出目录
                })
                .dataSourceConfig(builder -> builder.typeConvertHandler((globalConfig, typeRegistry, metaInfo) -> {
                    int typeCode = metaInfo.getJdbcType().TYPE_CODE;
                    if (typeCode == Types.SMALLINT) {
                        // 自定义类型转换
                        return DbColumnType.INTEGER;
                    }
                    return typeRegistry.getColumnType(metaInfo);

                }))
                .packageConfig(builder -> {
                    builder.parent("org.example.sa.rbac.demo") // 设置父包名
//                            .moduleName("system") // 设置父包模块名
                            .entity("entity")
                            .service("service")
                            .serviceImpl("service.impl")
                            .mapper("mappers")
                            .xml("mappers")
                            .controller("controller")
                            .pathInfo(Collections.singletonMap(OutputFile.xml, System.getProperty("user.dir") + "/sa-rbac-demo/src/main/resources/mappers")); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.addInclude(tableNames) // 设置需要生成的表名
                            .serviceBuilder()
                            .formatServiceFileName("%sService")
                            .controllerBuilder()
                            .enableRestStyle()
                            .entityBuilder()
                            .disableSerialVersionUID()
                            .enableTableFieldAnnotation()
                            .addTableFills(
                                    new Column("create_time", FieldFill.INSERT),
                                    new Column("update_time", FieldFill.UPDATE)
                            )
                            .enableLombok() // 开启lombok
//                            .addTablePrefix("t_", "c_"); // 设置过滤表前缀
                            .mapperBuilder()
                            .mapperAnnotation(Mapper.class)
                    ;
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }

}
