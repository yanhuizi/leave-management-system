package com.huizi.leave.leavemanagementsystem.domain;

public class AjaxResult {
	public static final int SUCCESS = 200;
	public static final int FALSE   = 500;
	private int code;
	private String message;
	private Object object;
	public AjaxResult() {
		// TODO Auto-generated constructor stub
	}
	
	public AjaxResult(int code, String message, Object object) {
		super();
		this.code = code;
		this.message = message;
		this.object = object;
	}
	public static AjaxResult success(String msg) {
		return new AjaxResult(SUCCESS,msg,null);
	}
	public static AjaxResult success(String msg,Object object) {
		return new AjaxResult(SUCCESS,msg,object);
	}
	public static AjaxResult failure(String msg) {
		return new AjaxResult(FALSE,msg,null);
	}
	public static AjaxResult failure(String msg,Object object) {
		return new AjaxResult(FALSE,msg,object);
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Object getObject() {
		return object;
	}
	public void setObject(Object object) {
		this.object = object;
	}
	
}
