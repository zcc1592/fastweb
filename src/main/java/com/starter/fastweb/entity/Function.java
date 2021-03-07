package com.starter.fastweb.entity;

import java.util.Date;

public class Function {
    private String id;

    private String functionCode;

    private String functionName;

    private String uri;

    private Integer functionLevel;

    private String parentCode;

    private Boolean active;

    private Integer displayOrder;

    private Boolean checkable;

    private String functionType;

    private Boolean leaf;

    private String iconCls;

    private String cls;

    private String functionDesc;

    private String functionSeq;

    private Integer systemType;

    private String createUser;

    private Date createDate;

    private String modifyUser;

    private Date modifyDate;

    //父功能
    private Function parentFunction;

    public Function getParentFunction() {
        return parentFunction;
    }

    public void setParentFunction(Function parentFunction) {
        this.parentFunction = parentFunction;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFunctionCode() {
        return functionCode;
    }

    public void setFunctionCode(String functionCode) {
        this.functionCode = functionCode;
    }

    public String getFunctionName() {
        return functionName;
    }

    public void setFunctionName(String functionName) {
        this.functionName = functionName;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }


    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }

    public Integer getFunctionLevel() {
        return functionLevel;
    }

    public void setFunctionLevel(Integer functionLevel) {
        this.functionLevel = functionLevel;
    }

    public Integer getDisplayOrder() {
        return displayOrder;
    }

    public void setDisplayOrder(Integer displayOrder) {
        this.displayOrder = displayOrder;
    }

    public String getFunctionType() {
        return functionType;
    }

    public void setFunctionType(String functionType) {
        this.functionType = functionType;
    }

    public String getIconCls() {
        return iconCls;
    }

    public void setIconCls(String iconCls) {
        this.iconCls = iconCls;
    }

    public String getCls() {
        return cls;
    }

    public void setCls(String cls) {
        this.cls = cls;
    }

    public String getFunctionDesc() {
        return functionDesc;
    }

    public void setFunctionDesc(String functionDesc) {
        this.functionDesc = functionDesc;
    }

    public String getFunctionSeq() {
        return functionSeq;
    }

    public void setFunctionSeq(String functionSeq) {
        this.functionSeq = functionSeq;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Boolean getCheckable() {
        return checkable;
    }

    public void setCheckable(Boolean checkable) {
        this.checkable = checkable;
    }

    public Boolean getLeaf() {
        return leaf;
    }

    public void setLeaf(Boolean leaf) {
        this.leaf = leaf;
    }

    public Integer getSystemType() {
        return systemType;
    }

    public void setSystemType(Integer systemType) {
        this.systemType = systemType;
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
        return "Function{" +
                "id='" + id + '\'' +
                ", functionCode='" + functionCode + '\'' +
                ", functionName='" + functionName + '\'' +
                ", uri='" + uri + '\'' +
                ", functionLevel='" + functionLevel + '\'' +
                ", parentCode='" + parentCode + '\'' +
                ", active='" + active + '\'' +
                ", displayOrder='" + displayOrder + '\'' +
                ", checkable='" + checkable + '\'' +
                ", functionType='" + functionType + '\'' +
                ", leaf='" + leaf + '\'' +
                ", iconCls='" + iconCls + '\'' +
                ", cls='" + cls + '\'' +
                ", functionDesc='" + functionDesc + '\'' +
                ", functionSeq='" + functionSeq + '\'' +
                ", systemType='" + systemType + '\'' +
                ", createUser='" + createUser + '\'' +
                ", createDate=" + createDate +
                ", modifyUser='" + modifyUser + '\'' +
                ", modifyDate=" + modifyDate +
                ", parentFunction=" + parentFunction +
                '}';
    }
}