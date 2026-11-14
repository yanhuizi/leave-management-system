package com.huizi.leave.leavemanagementsystem.domain;

import javax.servlet.http.HttpServletRequest;

public class Teacher {
	private int id;
	private String name;
	private String num;
	private String password;
	private int collegeId;
	private College college;
	private String  phone;
	private String collegeName;
	
	public void  parse(HttpServletRequest request) {
		String strId = request.getParameter("id");
		if (null != strId) {
			id = Integer.parseInt(strId);
		}
		name = request.getParameter("name");
		num = request.getParameter("num");
		password = request.getParameter("password");
		String strCollegeId = request.getParameter("collegeId");
		if (null != strCollegeId) {
			collegeId = Integer.parseInt(strCollegeId);
		}
		phone = request.getParameter("phone");
		
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getCollegeId() {
		return collegeId;
	}
	public void setCollegeId(int collegeId) {
		this.collegeId = collegeId;
	}
	public College getCollege() {
		return college;
	}
	public void setCollege(College college) {
		this.college = college;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getCollegeName() {
		return collegeName;
	}
	public void setCollegeName(String collegeName) {
		this.collegeName = collegeName;
	}
	
}
