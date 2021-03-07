package com.starter.fastweb.utils;

import java.util.List;

/**
 * @Author: Jack
 * @Time: 2016-10-17 15:25
 */
public class AjaxTable {
    private int draw;
    //没有过滤的记录数（数据库里总共记录数）
    private int recordsTotal;
    //过滤后的记录数（如果有接收到前台的过滤条件，则返回的是过滤后的记录数，如果没有过滤条件，等于recordsTotal）
    private int recordsFiltered;
    //错误提示
    private String error;
    //表中需要显示的数据
    private List<?> data;

    public int getDraw() {
        return draw;
    }

    public void setDraw(int draw) {
        this.draw = draw;
    }

    public int getRecordsTotal() {
        return recordsTotal;
    }

    public void setRecordsTotal(int recordsTotal) {
        this.recordsTotal = recordsTotal;
    }

    public int getRecordsFiltered() {
        return recordsFiltered;
    }

    public void setRecordsFiltered(int recordsFiltered) {
        this.recordsFiltered = recordsFiltered;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public List<?> getData() {
        return data;
    }

    public void setData(List<?> data) {
        this.data = data;
    }
}
