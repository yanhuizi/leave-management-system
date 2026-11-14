package com.huizi.leave.leavemanagementsystem.domain;

public class LeaveApplicationShenPiView {
	private int id;
	private int applicationId;
	private int teacherId;
	private int result;
	private String opinion;
	private String teacherName;
	private String aproveTypeName;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getApplicationId() {
		return applicationId;
	}
	public void setApplicationId(int applicationId) {
		this.applicationId = applicationId;
	}
	public int getTeacherId() {
		return teacherId;
	}
	public void setTeacherId(int teacherId) {
		this.teacherId = teacherId;
	}
	public int getResult() {
		return result;
	}
	public void setResult(int result) {
		this.result = result;
	}
	public String getOpinion() {
		return opinion;
	}
	public void setOpinion(String opinion) {
		this.opinion = opinion;
	}
	public String getTeacherName() {
		return teacherName;
	}
	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}
	public String getAproveTypeName() {
		return aproveTypeName;
	}
	public void setAproveTypeName(String aproveTypeName) {
		this.aproveTypeName = aproveTypeName;
	}
	
	
}
