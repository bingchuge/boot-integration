package org.example.sa.rbac.demo.entity.dto;

import lombok.Data;

@Data
public class MenuSaveDto {

    private Long menu_id;
    private Long pid;
    private String title;
    private Integer menu_sort;
    private int type;
    private Boolean i_frame;
    private String path;
    private String component;


}
