/*
 * Copyright by coglink and the original author or authors.
 * 
 * This document only allow internal use ,Any of your behaviors using the file
 * not internal will pay legal responsibility.
 *
 * You may learn more information about Coglink from
 *
 *      http://www.coglink.com
 *
 */
package com.starter.fastweb.utils;

import java.util.HashMap;

/**
 * 负责转换JSON
 * <p style="display:none">modifyRecord</p>
 * <p style="display:none">version:V1.0,author:yantao,date:2015-4-6 下午3:49:07,content:TODO </p>
 * @author yantao
 * @date 2015-4-6 下午3:49:07
 * @since
 * @version
 *
 *	此代码由歌联软件开发小组开发完成, 仅限多得米内部使用 
 *  外部使用该代码将付相应的法律责任
 *  更多信息请查询
 * 
 *
 *  http://www.coglink.com
 */
@SuppressWarnings({ "serial", "rawtypes","unchecked" })
public class JsonBean extends HashMap {
	/** 
	 * 执行操作结果的代码编号,
	 * 如果成功,值为10000,
	 * 如果失败, 值为错误编号.
	 */
	private String code = "0";
	/** 
	 * 成功或错误消息. 
	 */
	private String message;
	/** 
	 * 查询结果的总记录数;如果结果不是List,则为0. 
	 */
	private int count;
	/** 
	 * 返回结果,以下接口中的Reponse内容均简写为Detail内容.
	 */
	private Object results;
	
	/**
	 * 列表分页的总页数
	 */
	private int totalPage;
	/**
	 * 
	 * <p>构造函数</p>
	 * @author yanguangkun
	 * @date 2015-01-13 下午3:01:11
	 */
	public JsonBean() {
		setMessage("success");
		setCode("0");
		setCount(0);
		setResults("");
	}
	/**
	 * @return  the totalPage
	 */
	public int getTotalPage() {
		return totalPage;
	}

	/**
	 * @param totalPage the totalPage to set
	 */
	public void setTotalPage(int totalPage) {
		put("totalPage", totalPage);
		this.totalPage = totalPage;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		put("message", message);
		this.message = message;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		put("code", code);
		this.code = code;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		put("count", count);
		this.count = count;
	}
	
	/**
	 * @return  the results
	 */
	public Object getResults() {
		return results;
	}
	/**
	 * @param results the results to set
	 */
	public void setResults(Object results) {
		put("results", results);
		this.results = results;
	}
	public void put(String key,Object value) {
		super.put(key, value);
	}
}
