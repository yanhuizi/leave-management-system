package com.huizi.leave.leavemanagementsystem.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import com.huizi.leave.leavemanagementsystem.util.JdbcUtil;

public class PermissionDao {
	public static final int STUDENT_ROLE = 1;
	private static PermissionDao instance;
	private  PermissionDao() {}
	static{
		instance = new PermissionDao();
	}
	public static PermissionDao getInstance() {
		return instance;
	}
	
	/**
	 * 根据教师id查找用户权限
	 * @param id
	 * @return
	 * @throws SQLException 
	 */
	public Set<String> getPermissionByTeacherId(int teacherId) throws SQLException{
		Set<String> set = new HashSet<>();
		Connection conn = JdbcUtil.getConnect();
		String sql = "SELECT menu.permission FROM menu "
                     + "WHERE id in (SELECT role_menu.menuId FROM role_menu "
                     + "WHERE roleId in (SELECT roleid from user_role WHERE userid = ?))";
		PreparedStatement psm = JdbcUtil.getPreparedStatement(conn, sql);
		psm.setInt(1, teacherId);
		ResultSet result = psm.executeQuery();
		String permission;
		while(result.next()){
			permission = new String(result.getString(1));
			set.add(permission);
		}
		JdbcUtil.closeConnectionStateMent(conn, psm);
		return set;
	}
	
	public Set<String> getPermissionByStudentRoleId() throws SQLException{
		return getPermissionByRoleId(STUDENT_ROLE);
	}
	public Set<String> getPermissionByRoleId(int id) throws SQLException{
		Set<String> set = new HashSet<>();
		Connection conn = JdbcUtil.getConnect();
		String sql = "SELECT menu.permission FROM menu "
                     + "WHERE id in (SELECT role_menu.menuId FROM role_menu "
                     + "WHERE roleId = ?)";
		PreparedStatement psm = JdbcUtil.getPreparedStatement(conn, sql);
		psm.setInt(1, id);
		ResultSet result = psm.executeQuery();
		String permission;
		while(result.next()){
			permission = new String(result.getString(1));
			set.add(permission);
		}
		JdbcUtil.closeConnectionStateMent(conn, psm);
		return set;
	}
}
