package com.jacky.utils;

public class ReturnResult<T> {

	private String code;
	private String msg;
	private Object extra;
	private T data;

	public ReturnResult(String code, String msg, T data) {

		this.code = code;
		this.msg = msg;
		this.data = data;
	}

	public ReturnResult(String code, String msg, T data, Object extra) {

		this.code = code;
		this.msg = msg;
		this.data = data;
		this.extra = extra;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public Object getExtra() {
		return extra;
	}

	public void setExtra(Object extra) {
		this.extra = extra;
	}
}
