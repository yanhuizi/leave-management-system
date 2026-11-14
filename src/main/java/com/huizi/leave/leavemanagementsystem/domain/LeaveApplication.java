package com.huizi.leave.leavemanagementsystem.domain;

import java.text.ParseException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;


import com.huizi.leave.leavemanagementsystem.util.DateUtil;

public class LeaveApplication {
	public static final int STATUS_DAI_SHEN_PI = 1;//待审批
	public static final int STATUS_BANZHREN_PASS = 2;//班主任审批通过
	public static final int STATUS_FUDAOYUAN_PASS = 3;//辅导员审批通过
	public static final int STATUS_PASS = 4;//审批通过
	public static final int STATUS_OVER = 5;//结束
	
	public static final int TYPE_IN_ONE = 1;//一天以内
	public static final int TYPE_BETWEEM_ONE_THREE = 2;//一天以外三天以内
	public static final int TYPE_GREATER_THREE = 3;//三天以外
	public static final int TYPE_OUTER_ENSHI = 4;//出恩施州
	
	public static final int APROVE_TYPE_PASS = 1;//审批人通过审批
	public static final int APPOVE_TYPE_NO_PASS = 2;//审批人未通过审批
	
	
	
	private int id;
	private int studentId;
	private int type;
	private String path;
	private String reason;
	private Date begintime;
	private String h5Begintime;
	private Date endtime;
	private String h5Endtime;
	private int status;
	
	public void parse(HttpServletRequest request) throws ParseException{
		String strId = request.getParameter("id");
		String strStudentId = request.getParameter("studentId");
		String strType = request.getParameter("type");
		path = request.getParameter("path");
		reason = request.getParameter("reason");
		String strBegintime = request.getParameter("begintime");
		String strEndtime = request.getParameter("endtime");
		String strStatus  = request.getParameter("status");
		if (null!=strId) {
			id = Integer.parseInt(strId);
		}
		if (null!=strStudentId) {
			studentId = Integer.parseInt(strStudentId);
		}
		if (null!=strType) {
			type = Integer.parseInt(strType);
		}
		
		if (null!=strBegintime) {
			begintime = DateUtil.parse(strBegintime);
		}
		if (null!=strEndtime) {
			endtime = DateUtil.parse(strEndtime);
		}
		if (null!=strStatus) {
			status = Integer.parseInt(strStatus);
		}
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getStudentId() {
		return studentId;
	}
	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public Date getBegintime() {
		return begintime;
	}
	public void setBegintime(Date begintime) {
		this.begintime = begintime;
	}
	public Date getEndtime() {
		return endtime;
	}
	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getH5Begintime() {
		return h5Begintime;
	}
	public void setH5Begintime(String h5Begintime) {
		this.h5Begintime = h5Begintime;
	}
	public String getH5Endtime() {
		return h5Endtime;
	}
	public void setH5Endtime(String h5Endtime) {
		this.h5Endtime = h5Endtime;
	}
	
	
}
