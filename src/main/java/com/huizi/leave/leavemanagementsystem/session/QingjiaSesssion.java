package com.huizi.leave.leavemanagementsystem.session;

import java.util.List;
import java.util.Set;

import com.huizi.leave.leavemanagementsystem.domain.Clazz;
import com.huizi.leave.leavemanagementsystem.domain.Grade;
import com.huizi.leave.leavemanagementsystem.domain.Role;
import com.huizi.leave.leavemanagementsystem.domain.Student;
import com.huizi.leave.leavemanagementsystem.domain.Teacher;

public class QingjiaSesssion {
	public static int USER_TYPE_TEACHER = 1;
	public static int USER_TYPE_STUDENT = 2;
	
	public static final int DATA_AREA_PERSON  = 1;
	public static final int DATA_AREA_CLASS   = 2;
	public static final int DATA_AREA_COLLEGE = 3;
	public static final int DATA_AREA_SYSTEM  = 4;
	public static final String QINGJIA_SESSION  = "qSession";
	/*
	 * 权限
	 */
	private Set<String> permissiones;
	/*
	 * 用户类型 1：教师 2：学生
	 */
	private int userType;
	/*
	 * 学生信息
	 */
	private Student student;
	
	/*
	 * 教师信息
	 */
	private Teacher teacher;
	
	/**
	 * 用户角色
	 */
	private List<Role> roles;
	
	/*
	 * 班主任管理的班级的ID
	 */
	private String manageClasses; 
	/**
	 * 班主任管理的班级或学生所在班级
	 */
	private List<Clazz>  clazzes;
	
	/** 
	 * 班主任多管理的班级的年级
	 */
	private List<Grade> grades;
	/*
	 * 学院id
	 */
	private int collegeId;
	/*
	 * 班级id
	 */
	private int classId;
	/**
	 * 学生所在班级
	 */
	private Clazz clazz;
	/**
	 * 是否是班主任
	 */
	private boolean isBanzhuren;
	/*
	 * 是否是辅导员
	 */
	private boolean isFudaoyuan;
	/*
	 * 是否是学院领导
	 */
	private boolean isXuyuanLingdao;
	
	/*
	 * 数据访问域
	 */
	private int dataArea = DATA_AREA_PERSON;
	
	public QingjiaSesssion() {
		// TODO Auto-generated constructor stub
	}
	
	public boolean hasPermission(String permission) {
		return permissiones.contains(permission);
	}

	public Set<String> getPermissiones() {
		return permissiones;
	}

	public void setPermissiones(Set<String> permissiones) {
		this.permissiones = permissiones;
	}

	public int getUserType() {
		return userType;
	}

	public void setUserType(int userType) {
		this.userType = userType;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public String getManageClasses() {
		return manageClasses;
	}

	public void setManageClasses(String manageClasses) {
		this.manageClasses = manageClasses;
	}

	public int getCollegeId() {
		return collegeId;
	}

	public void setCollegeId(int collegeId) {
		this.collegeId = collegeId;
	}

	public boolean isBanzhuren() {
		return isBanzhuren;
	}

	public void setBanzhuren(boolean isBanzhuren) {
		this.isBanzhuren = isBanzhuren;
	}

	public boolean isFudaoyuan() {
		return isFudaoyuan;
	}

	public void setFudaoyuan(boolean isFudaoyuan) {
		this.isFudaoyuan = isFudaoyuan;
	}

	public boolean isXuyuanLingdao() {
		return isXuyuanLingdao;
	}

	public void setXuyuanLingdao(boolean isXuyuanLingdao) {
		this.isXuyuanLingdao = isXuyuanLingdao;
	}

	public int getDataArea() {
		return dataArea;
	}

	public void setDataArea(int dataArea) {
		this.dataArea = dataArea;
	}

	public int getClassId() {
		return classId;
	}

	public void setClassId(int classId) {
		this.classId = classId;
	}

	public Clazz getClazz() {
		return clazz;
	}

	public void setClazz(Clazz clazz) {
		this.clazz = clazz;
	}

	public List<Clazz> getClazzes() {
		return clazzes;
	}

	public void setClazzes(List<Clazz> clazzes) {
		this.clazzes = clazzes;
	}

	public List<Grade> getGrades() {
		return grades;
	}

	public void setGrades(List<Grade> grades) {
		this.grades = grades;
	}
}
