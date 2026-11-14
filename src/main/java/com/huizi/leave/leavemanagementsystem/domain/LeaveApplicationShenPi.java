package com.huizi.leave.leavemanagementsystem.domain;

import javax.servlet.http.HttpServletRequest;

public class LeaveApplicationShenPi {
	public static final int RESULT_TYPE_AGREE = 1;
	public static final int RESULT_TYPE_DISAGREE = 2;
	private int id;
	private int applicationId;
	private int teacherId;
	private int result;
	private String opinion;
	
	public void parse(HttpServletRequest request) {
		String strApplicationId = request.getParameter("applicationId");
		String strResult = request.getParameter("result");
		opinion  = request.getParameter("opinion");
		if (strApplicationId != null) {
			applicationId = Integer.parseInt(strApplicationId);
		}
		if (strResult != null) {
			result = Integer.parseInt(strResult);
		}
	}
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
	
	
}
