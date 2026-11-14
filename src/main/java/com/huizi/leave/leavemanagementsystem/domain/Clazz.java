package com.huizi.leave.leavemanagementsystem.domain;

import javax.servlet.http.HttpServletRequest;

/**
 * 班级
 * @author 86134
 *
 */
public class Clazz {
	private int id;
	private String num;
	private int gradeId;
	private String grade;
	private int belongToCollegeId;
	private String belongToCollege;
	private int banZhuRenId;
	private String banZhuRen;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	public int getGradeId() {
		return gradeId;
	}
	public String getBelongToCollege() {
		return belongToCollege;
	}
	public int getBanZhuRenId() {
		return banZhuRenId;
	}
	public void setBanZhuRenId(int banZhuRenId) {
		this.banZhuRenId = banZhuRenId;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public int getBelongToCollegeId() {
		return belongToCollegeId;
	}
	public void setBelongToCollegeId(int belongToCollegeId) {
		this.belongToCollegeId = belongToCollegeId;
	}
	public String getBanZhuRen() {
		return banZhuRen;
	}
	public void setBanZhuRen(String banZhuRen) {
		this.banZhuRen = banZhuRen;
	}
	public void setGradeId(int gradeId) {
		this.gradeId = gradeId;
	}
	public void setBelongToCollege(String belongToCollege) {
		this.belongToCollege = belongToCollege;
	}
	public void parse(HttpServletRequest request) {
		// TODO Auto-generated method stub
		String strId = request.getParameter("id");
		if (strId != null) {
			id = Integer.parseInt(strId);
		}
		num = request.getParameter("num");
		String strGradeId = request.getParameter("gradeId");
		if (strGradeId != null) {
			gradeId = Integer.parseInt(strGradeId);
		}
		String strbelongToCollegeId = request.getParameter("belongToCollegeId");
		if (null != strbelongToCollegeId) {
			belongToCollegeId = Integer.parseInt(strbelongToCollegeId);
		}
	}
	
}
