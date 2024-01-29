package org.example.sa.rbac.demo.service;

import org.example.sa.rbac.demo.entity.SysDept;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 部门表 服务类
 * </p>
 *
 * @author bingchu
 * @since 2024-01-09
 */
public interface SysDeptService extends IService<SysDept> {

    List<SysDept> page();

}
