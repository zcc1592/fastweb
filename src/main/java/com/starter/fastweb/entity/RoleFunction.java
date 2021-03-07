package com.starter.fastweb.entity;

/**
 * role与function的关联关系
 * @Author: Jack
 * @Time: 2017-02-21 15:59
 */
public class RoleFunction {

    private String id;

    private String roleCode;

    private String functionCode;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public String getFunctionCode() {
        return functionCode;
    }

    public void setFunctionCode(String functionCode) {
        this.functionCode = functionCode;
    }
}
