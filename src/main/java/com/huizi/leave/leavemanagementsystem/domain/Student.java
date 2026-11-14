package com.huizi.leave.leavemanagementsystem.domain;

import javax.servlet.http.HttpServletRequest;

public class Student {
	private int id;
	private String name;
	private String num;
	private String password;
	private int classId;
	private int sex;
	private String classNum;
	private String sexName;
	private int belongToCollegeId;
	private int gradeId;
	public void parse(HttpServletRequest request) {
		String strId        = request.getParameter("id");
		String strclassId   = request.getParameter("classId");
		String strSexId     = request.getParameter("sex");
		if (strId != null) {
			id = Integer.parseInt(strId);
		}
		if (null!=strclassId) {
			classId = Integer.parseInt(strclassId);
		}
		if (null!=strSexId) {
			sex = Integer.parseInt(strSexId);
		}
		name = request.getParameter("name");
		num  = request.getParameter("num");
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
	public int getClassId() {
		return classId;
	}
	public void setClassId(int classId) {
		this.classId = classId;
	}
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	public String getClassNum() {
		return classNum;
	}
	public void setClassNum(String classNum) {
		this.classNum = classNum;
	}
	public String getSexName() {
		return sexName;
	}
	public void setSexName(String sexName) {
		this.sexName = sexName;
	}
	public int getBelongToCollegeId() {
		return belongToCollegeId;
	}
	public void setBelongToCollegeId(int belongToCollegeId) {
		this.belongToCollegeId = belongToCollegeId;
	}
	public int getGradeId() {
		return gradeId;
	}
	public void setGradeId(int gradeId) {
		this.gradeId = gradeId;
	}
	
}
