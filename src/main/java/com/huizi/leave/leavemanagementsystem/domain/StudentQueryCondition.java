package com.huizi.leave.leavemanagementsystem.domain;

import javax.servlet.http.HttpServletRequest;

public class StudentQueryCondition {
	private int collegeId;
	private int gradeId;
	private int classId;
	private String name;
	private String num;
	private int    sexId;    
	
	public void parse(HttpServletRequest request) {
		String strcollegeId = request.getParameter("collegeId");
		String strgradeId= request.getParameter("gradeId");
		String strclassId= request.getParameter("classId");
		String strSexId     = request.getParameter("sex");
		if (null!=strcollegeId) {
			collegeId = Integer.parseInt(strcollegeId);
		}
		if (null!=strgradeId) {
			gradeId = Integer.parseInt(strgradeId);
		}
		if (null!=strclassId) {
			classId = Integer.parseInt(strclassId);
		}
		if (null!=strSexId) {
			sexId = Integer.parseInt(strSexId);
		}
		name = request.getParameter("name");
		num  = request.getParameter("num");
	}
	public int getCollegeId() {
		return collegeId;
	}
	public void setCollegeId(int collegeId) {
		this.collegeId = collegeId;
	}
	public int getGradeId() {
		return gradeId;
	}
	public void setGradeId(int gradeId) {
		this.gradeId = gradeId;
	}
	public int getClassId() {
		return classId;
	}
	public void setClassId(int classId) {
		this.classId = classId;
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
	public int getSexId() {
		return sexId;
	}
	public void setSexId(int sexId) {
		this.sexId = sexId;
	}
	
}
