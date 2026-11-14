package com.huizi.leave.leavemanagementsystem.service;

import java.sql.SQLException;
import java.util.List;

import com.huizi.leave.leavemanagementsystem.dao.InitParamDao;
import com.huizi.leave.leavemanagementsystem.domain.LeaveApplicationStatus;
import com.huizi.leave.leavemanagementsystem.domain.LeaveApplicationType;
import com.huizi.leave.leavemanagementsystem.domain.SexType;
import com.huizi.leave.leavemanagementsystem.domain.MenuType;
import com.huizi.leave.leavemanagementsystem.domain.RoleType;

public class InitParamService {
	private static InitParamService instance;
	private  InitParamService() {}
	static{
		instance = new InitParamService();
	}
	public static InitParamService getInstance() {
		return instance;
	}
	
	public List<LeaveApplicationStatus> getLeaveApplicationResultType() throws SQLException {
		return InitParamDao.getInstance().getLeaveApplicationResultType();
	}
	
	public List<SexType> getSexType() throws SQLException {
		return InitParamDao.getInstance().getSexType();
	}
	public List<MenuType> getMenuType() throws SQLException {
		return InitParamDao.getInstance().getMenuType();
	}
	public List<RoleType> getRoleType() throws SQLException {
		return InitParamDao.getInstance().getRoleType();
	}
	public List<LeaveApplicationType> getLeaveApplicationType() throws SQLException {
		return InitParamDao.getInstance().getLeaveApplicationType();
	}
}
