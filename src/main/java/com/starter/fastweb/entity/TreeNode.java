package com.starter.fastweb.entity;

import java.util.List;

/**
 * 树结构的节点
 * @Author: Jack
 * @Time: 2017-02-23 11:18
 */
public class TreeNode {

    private String id;

    private String type;

    private String text;

    private List<TreeNode> nodes;

    private TreeState state;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<TreeNode> getNodes() {
        return nodes;
    }

    public void setNodes(List<TreeNode> nodes) {
        this.nodes = nodes;
    }

    public TreeState getState() {
        return state;
    }

    public void setState(TreeState state) {
        this.state = state;
    }
}
