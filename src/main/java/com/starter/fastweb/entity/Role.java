package com.starter.fastweb.entity;

import java.util.Arrays;
import java.util.Date;

/**
 * 角色
 */
public class Role {
    private String id;

    private String roleCode;

    private String roleName;

    private String roleDesc;

    private Boolean active;

    private String createUser;

    private Date createDate;

    private String modifyUser;

    private Date modifyDate;

    //功能编码集合
    private String[] functionCodes;

    public String[] getFunctionCodes() {
        return functionCodes;
    }

    public void setFunctionCodes(String[] functionCodes) {
        this.functionCodes = functionCodes;
    }

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

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleDesc() {
        return roleDesc;
    }

    public void setRoleDesc(String roleDesc) {
        this.roleDesc = roleDesc;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getModifyUser() {
        return modifyUser;
    }

    public void setModifyUser(String modifyUser) {
        this.modifyUser = modifyUser;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id='" + id + '\'' +
                ", roleCode='" + roleCode + '\'' +
                ", roleName='" + roleName + '\'' +
                ", roleDesc='" + roleDesc + '\'' +
                ", active='" + active + '\'' +
                ", createUser='" + createUser + '\'' +
                ", createDate=" + createDate +
                ", modifyUser='" + modifyUser + '\'' +
                ", modifyDate=" + modifyDate +
                ", functionCodes=" + Arrays.toString(functionCodes) +
                '}';
    }
}