package com.starter.fastweb.entity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 节点信息实体类
 * <p style="display:none">modifyRecord</p>
 * <p style="display:none">version:V1.0,author:yantao,date:2017-2-21 上午10:15:53,content:TODO </p>
 * @author yantao
 * @date 2017-2-21 上午10:15:53
 * @since
 * @version
 *
 *	此代码由歌联软件开发小组开发完成, 仅限歌联科技内部使用 
 *  外部使用该代码将付相应的法律责任
 *  更多信息请查询
 * 
 *
 *  http://www.coglinktech.com
 */
public class NodeEntity {
    private BigDecimal id;

    private String nodeCode;

    private String nodeName;

    private String dbsCode;

    private String dbsDcode;

    private String createUser;

    private Date createDate;

    private String modifyUser;

    private Date modifyDate;

    private String active;

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getNodeCode() {
        return nodeCode;
    }

    public void setNodeCode(String nodeCode) {
        this.nodeCode = nodeCode;
    }

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    public String getDbsCode() {
        return dbsCode;
    }

    public void setDbsCode(String dbsCode) {
        this.dbsCode = dbsCode;
    }

    public String getDbsDcode() {
        return dbsDcode;
    }

    public void setDbsDcode(String dbsDcode) {
        this.dbsDcode = dbsDcode;
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

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }
}