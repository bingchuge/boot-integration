package org.example.sa.rbac.demo.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author bingchu
 * @since 2024-01-09
 */
@Getter
@Setter
@TableName("sys_user")
public class SysUser {

    /**
     * 用户ID
     */
    @TableId(value = "user_id", type = IdType.AUTO)
    private Long userId;

    /**
     * 用户昵称
     */
    @TableField("user_name")
    private String userName;

    /**
     * 用户类型（00系统用户 01注册用户）
     */
    @TableField("user_type")
    private String userType;

    /**
     * 姓名
     */
    @TableField("name")
    private String name;

    /**
     * 用户邮箱
     */
    @TableField("email")
    private String email;

    /**
     * 手机号码
     */
    @TableField("phone_number")
    private String phoneNumber;

    /**
     * 用户性别（1男 2女 3未知）
     */
    @TableField("sex")
    private Byte sex;

    /**
     * 头像路径
     */
    @TableField("avatar")
    private String avatar;

    /**
     * 密码
     */
    @TableField("password")
    private String password;

    /**
     * 盐加密
     */
    @TableField("salt")
    private String salt;

    /**
     * 帐号状态（1正常 0停用）
     */
    @TableField("status")
    private Byte status;

    /**
     * 最后登录时间
     */
    @TableField("login_date")
    private LocalDateTime loginDate;

    /**
     * 密码最后更新时间
     */
    @TableField("pwd_update_date")
    private LocalDateTime pwdUpdateDate;

    /**
     * 创建者
     */
    @TableField("create_by")
    private String createBy;

    /**
     * 创建时间
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 更新者
     */
    @TableField("update_by")
    private String updateBy;

    /**
     * 更新时间
     */
    @TableField(value = "update_time", fill = FieldFill.UPDATE)
    private LocalDateTime updateTime;

    /**
     * 备注
     */
    @TableField("remark")
    private String remark;

    /**
     * 删除标志（0代表存在 1代表删除）
     */
    @TableField("del_flag")
    private Byte delFlag;
}
