package org.example.sa.rbac.demo.service;

import org.example.sa.rbac.demo.entity.SysUserDept;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author bingchu
 * @since 2024-01-31
 */
public interface SysUserDeptService extends IService<SysUserDept> {


    List<Long> getUserIdsByDeptIds(List<Long> deptIds);

    void updateUserDept(String deptId, Long userId);

    Long getDeptIdByUserId(Long userId);
}
