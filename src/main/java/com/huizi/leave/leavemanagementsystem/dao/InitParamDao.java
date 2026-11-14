package com.huizi.leave.leavemanagementsystem.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.huizi.leave.leavemanagementsystem.domain.LeaveApplicationStatus;
import com.huizi.leave.leavemanagementsystem.domain.LeaveApplicationType;
import com.huizi.leave.leavemanagementsystem.domain.MenuType;
import com.huizi.leave.leavemanagementsystem.domain.RoleType;
import com.huizi.leave.leavemanagementsystem.domain.SexType;
import com.huizi.leave.leavemanagementsystem.util.JdbcUtil;

public class InitParamDao {
	private static InitParamDao instance;
	private  InitParamDao() {}
	static{
		instance = new InitParamDao();
	}
	public static InitParamDao getInstance() {
		return instance;
	}
	
	public List<LeaveApplicationStatus> getLeaveApplicationResultType() throws SQLException {
		List<LeaveApplicationStatus> larts = new ArrayList<LeaveApplicationStatus>();
		Connection conn = JdbcUtil.getConnect();
		String sql = "SELECT * FROM application_status ";
		PreparedStatement psm = JdbcUtil.getPreparedStatement(conn, sql);
		ResultSet set = psm.executeQuery();
		LeaveApplicationStatus sexType;
		while(set.next()){
			sexType = new LeaveApplicationStatus();
			sexType.setId(set.getInt(1));
			sexType.setApplicationResult(set.getString(2));
			larts.add(sexType);
		}
		JdbcUtil.closeConnectionStateMent(conn, psm);
		return larts;
	}
	
	public List<SexType> getSexType() throws SQLException {
		List<SexType> sexTypes = new ArrayList<SexType>();
		Connection conn = JdbcUtil.getConnect();
		String sql = "SELECT * FROM sex_type ";
		PreparedStatement psm = JdbcUtil.getPreparedStatement(conn, sql);
		ResultSet set = psm.executeQuery();
		SexType sexType;
		while(set.next()){
			sexType = new SexType();
			sexType.setId(set.getInt(1));
			sexType.setName(set.getString(2));
			sexTypes.add(sexType);
		}
		JdbcUtil.closeConnectionStateMent(conn, psm);
		return sexTypes;
	}
	
	public List<MenuType> getMenuType() throws SQLException {
		List<MenuType> menuTypes = new ArrayList<MenuType>();
		Connection conn = JdbcUtil.getConnect();
		String sql = "SELECT * FROM menu_type ";
		PreparedStatement psm = JdbcUtil.getPreparedStatement(conn, sql);
		ResultSet set = psm.executeQuery();
		MenuType menuType;
		while(set.next()){
			menuType = new MenuType();
			menuType.setId(set.getInt(1));
			menuType.setName(set.getString(2));
			menuTypes.add(menuType);
		}
		JdbcUtil.closeConnectionStateMent(conn, psm);
		return menuTypes;
	}
	
	public List<RoleType> getRoleType() throws SQLException {
		List<RoleType> roleTypes = new ArrayList<RoleType>();
		Connection conn = JdbcUtil.getConnect();
		String sql = "SELECT * FROM role_type ";
		PreparedStatement psm = JdbcUtil.getPreparedStatement(conn, sql);
		ResultSet set = psm.executeQuery();
		RoleType roleType;
		while(set.next()){
			roleType = new RoleType();
			roleType.setId(set.getInt(1));
			roleType.setName(set.getString(2));
			roleTypes.add(roleType);
		}
		JdbcUtil.closeConnectionStateMent(conn, psm);
		return roleTypes;
	}
	
	public List<LeaveApplicationType> getLeaveApplicationType() throws SQLException {
		List<LeaveApplicationType> roleTypes = new ArrayList<LeaveApplicationType>();
		Connection conn = JdbcUtil.getConnect();
		String sql = "SELECT * FROM leave_application_type ";
		PreparedStatement psm = JdbcUtil.getPreparedStatement(conn, sql);
		ResultSet set = psm.executeQuery();
		LeaveApplicationType roleType;
		while(set.next()){
			roleType = new LeaveApplicationType();
			roleType.setId(set.getInt(1));
			roleType.setName(set.getString(2));
			roleTypes.add(roleType);
		}
		JdbcUtil.closeConnectionStateMent(conn, psm);
		return roleTypes;
	}
}
