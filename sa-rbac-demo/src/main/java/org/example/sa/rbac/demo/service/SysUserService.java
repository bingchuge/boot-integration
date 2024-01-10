package org.example.sa.rbac.demo.service;

import org.example.sa.rbac.demo.entity.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author bingchu
 * @since 2024-01-09
 */
public interface SysUserService extends IService<SysUser> {

    /**
     * 根据用户名获取用户
     * @param username 用户名
     * @return {SysUser}
     */

    SysUser getByUsername(String username);
}
