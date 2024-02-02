package org.example.sa.rbac.demo.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Slf4j
@Component
public class CommonFieldHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("start insert fill ....");
        ZoneId chinaZoneId = ZoneId.of("Asia/Shanghai");
        // 获取当前的日期和时间
        LocalDateTime nowInChina = ZonedDateTime.now(chinaZoneId).toLocalDateTime();
        this.setFieldValByName("createTime", nowInChina, metaObject);
        this.setFieldValByName("updateTime", nowInChina, metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("start update fill ....");
        ZoneId chinaZoneId = ZoneId.of("Asia/Shanghai");
        // 获取当前的日期和时间
        LocalDateTime nowInChina = ZonedDateTime.now(chinaZoneId).toLocalDateTime();
        this.setFieldValByName("updateTime", nowInChina, metaObject);
    }

    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));//如果配置多个插件,切记分页最后添加
        //interceptor.addInnerInterceptor(new PaginationInnerInterceptor()); 如果有多数据源可以不配具体类型 否则都建议配上具体的DbType
        return interceptor;
    }
}
