package com.huizi.leave.leavemanagementsystem.domain;

/**
 * 统计概览：用于“数据统计”模块展示各数据表与请假状态的记录数。
 */
public class StatOverview {
	private int collegeCount;
	private int classCount;
	private int studentCount;
	private int teacherCount;
	private int roleCount;
	private int pendingCount;
	private int passCount;
	private int overCount;

	public int getCollegeCount() {
		return collegeCount;
	}
	public void setCollegeCount(int collegeCount) {
		this.collegeCount = collegeCount;
	}
	public int getClassCount() {
		return classCount;
	}
	public void setClassCount(int classCount) {
		this.classCount = classCount;
	}
	public int getStudentCount() {
		return studentCount;
	}
	public void setStudentCount(int studentCount) {
		this.studentCount = studentCount;
	}
	public int getTeacherCount() {
		return teacherCount;
	}
	public void setTeacherCount(int teacherCount) {
		this.teacherCount = teacherCount;
	}
	public int getRoleCount() {
		return roleCount;
	}
	public void setRoleCount(int roleCount) {
		this.roleCount = roleCount;
	}
	public int getPendingCount() {
		return pendingCount;
	}
	public void setPendingCount(int pendingCount) {
		this.pendingCount = pendingCount;
	}
	public int getPassCount() {
		return passCount;
	}
	public void setPassCount(int passCount) {
		this.passCount = passCount;
	}
	public int getOverCount() {
		return overCount;
	}
	public void setOverCount(int overCount) {
		this.overCount = overCount;
	}
}
