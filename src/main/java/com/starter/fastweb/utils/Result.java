package com.starter.fastweb.utils;

import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 返回结果集
 * @param <T>
 */
public class Result<T> implements Serializable {
	private static final long serialVersionUID = 4760645896692860229L;
	
	private Integer code;
	private String msg;
	private boolean success;
	private T data;
	
	public Result() {
		super();
	}
	
	public Result(Integer code, String msg, boolean success) {
		super();
		this.code = code;
		this.msg = msg;
		this.success = success;
	}

	public static <T> Result<T> success(T data) {

		return null;
	}

}
