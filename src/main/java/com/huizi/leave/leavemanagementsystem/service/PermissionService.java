package com.huizi.leave.leavemanagementsystem.service;

import java.sql.SQLException;
import java.util.Set;


import com.huizi.leave.leavemanagementsystem.dao.PermissionDao;

public class PermissionService {
	private static PermissionService instance;
	private  PermissionService() {}
	static{
		instance = new PermissionService();
	}
	public static PermissionService getInstance() {
		return instance;
	}
	/**
	 * 根据教师id查找用户权限
	 * @param id
	 * @return
	 * @throws SQLException 
	 */
	public Set<String> getPermissionByTeacherId(int teacherId) throws SQLException{
		return PermissionDao.getInstance().getPermissionByTeacherId(teacherId);
	}
	
	public Set<String> getPermissionByStudentRoleId() throws SQLException{
		return PermissionDao.getInstance().getPermissionByStudentRoleId();
	}
}
