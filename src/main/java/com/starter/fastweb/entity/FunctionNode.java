package com.starter.fastweb.entity;

import java.util.List;
import java.util.Set;

/**
 * 功能权限树节点
 */
public class FunctionNode {

    private String functionCode;

    private String functionType;

    private String text;

    private List<FunctionNode> nodes;

    private TreeState state;

    //所有被选中的子节点（包括节点本身）
    private Set<String> allCheckNodes;

    public FunctionNode(){}

    public FunctionNode(String functionCode, String text, String functionType){
        this.functionCode = functionCode;
        this.text = text;
        this.functionType = functionType;
    }

    public Set<String> getAllCheckNodes() {
        return allCheckNodes;
    }

    public void setAllCheckNodes(Set<String> allCheckNodes) {
        this.allCheckNodes = allCheckNodes;
    }

    public TreeState getState() {
        return state;
    }

    public void setState(TreeState state) {
        this.state = state;
    }

    public String getFunctionType() {
        return functionType;
    }

    public void setFunctionType(String functionType) {
        this.functionType = functionType;
    }

    public List<FunctionNode> getNodes() {
        return nodes;
    }

    public void setNodes(List<FunctionNode> nodes) {
        this.nodes = nodes;
    }

    public String getFunctionCode() {
        return functionCode;
    }

    public void setFunctionCode(String functionCode) {
        this.functionCode = functionCode;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}