package org.example.sa.rbac.demo.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 系统菜单
 * </p>
 *
 * @author bingchu
 * @since 2024-01-18
 */
@Data
@TableName("sys_menu_cosas")
public class SysMenuCosas {

    /**
     * ID
     */
    @TableId(value = "menu_id", type = IdType.AUTO)
    private Long menu_id;

    /**
     * 上级菜单ID
     */
    private Long pid;

    /**
     * 子菜单数目
     */
    @TableField("sub_count")
    private Integer sub_count;

    /**
     * 菜单类型
     */
    private Integer type;

    /**
     * 菜单标题
     */
    private String title;

    /**
     * 组件名称
     */
    private String name;

    /**
     * 组件
     */
    private String component;

    /**
     * 排序
     */
    @TableField("menu_sort")
    private Integer menu_sort;

    /**
     * 图标
     */
    private String icon;

    /**
     * 链接地址
     */
    private String path;

    /**
     * 是否参与授权（0-参与 1-不参与）
     */
    @TableField("i_frame")
    private Boolean i_frame;

    /**
     * 缓存
     */
    private Boolean cache;

    /**
     * 隐藏
     */
    private Boolean hidden;

    /**
     * 权限
     */
    private String permission;

    /**
     * 创建者
     */
    @TableField("create_by")
    private String create_by;

    /**
     * 更新者
     */
    @TableField("update_by")
    private String update_by;

    /**
     * 创建日期
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Long create_time;

    /**
     * 更新时间
     */
    @TableField(value = "update_time", fill = FieldFill.UPDATE)
    private Long update_time;

}
