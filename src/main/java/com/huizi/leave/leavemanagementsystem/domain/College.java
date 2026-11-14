package com.huizi.leave.leavemanagementsystem.domain;

import javax.servlet.http.HttpServletRequest;

public class College {
	private int id;
	private String name;
	private String num;
	private String tel;
	
	public void parse(HttpServletRequest request) {
		String strId = request.getParameter("id");
		if (null != strId) {
			id = Integer.parseInt(strId);
		}
		name = request.getParameter("name");
		num  = request.getParameter("num");
		tel  = request.getParameter("tel");
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	
}
