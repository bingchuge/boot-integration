package org.example.sa.rbac.demo.entity.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TreeNode {
    private Long id;
    private String label;
    private Integer orderNum;
    private List<TreeNode> children = new ArrayList<>();

    public TreeNode(Long id, String label, Integer orderNum) {
        this.id = id;
        this.label = label;
        this.orderNum = orderNum;
    }
}
