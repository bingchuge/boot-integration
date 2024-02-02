package org.example.sa.rbac.demo.entity.vo;


import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserInfoVo {

    private Long deptId;
    private String deptName;

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

}
