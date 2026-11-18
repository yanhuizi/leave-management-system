package com.huizi.leave.leavemanagementsystem.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import com.huizi.leave.leavemanagementsystem.domain.Role;
import com.huizi.leave.leavemanagementsystem.util.JdbcUtil;

public class RoleDao {
	private static RoleDao instance;
	private RoleDao(){};
	static{
		instance = new RoleDao();
	}
	public static RoleDao getInstance() {
		return instance;
	}
	
	public List<Role> getRoleListByUserID(int userid) throws SQLException {
		List<Role> roles = new ArrayList<Role>();
		Connection conn = JdbcUtil.getConnect();
		
		String sql = "SELECT * FROM role "
				+"WHERE role.id in (SELECT roleid from user_role where userid = ?)";
		PreparedStatement psm = JdbcUtil.getPreparedStatement(conn, sql);
		psm.setInt(1, userid);
		ResultSet set = psm.executeQuery();
		Role role;
		while(set.next()){
			role = new Role();
			role.setId(set.getInt(1));
			role.setName(set.getString(2));
			role.setRemark(set.getString(3));
			role.setEareType(set.getInt(4));
			roles.add(role);
		}
		JdbcUtil.closeConnectionStateMent(conn, psm);
		return roles;
	}
	
	public List<Role> getAll() throws SQLException {
		List<Role> roles = new ArrayList<Role>();
		Connection conn = JdbcUtil.getConnect();
		
		String sql = "SELECT * FROM role";
		PreparedStatement psm = JdbcUtil.getPreparedStatement(conn, sql);
		ResultSet set = psm.executeQuery();
		Role role;
		while(set.next()){
			role = new Role();
			role.setId(set.getInt(1));
			role.setName(set.getString(2));
			role.setRemark(set.getString(3));
			role.setEareType(set.getInt(4));
			roles.add(role);
		}
		JdbcUtil.closeConnectionStateMent(conn, psm);
		return roles;
	}
	
	public boolean add(Role role) throws SQLException {
		Connection conn = JdbcUtil.getConnect();
		String sql = "INSERT INTO role(name,remark,eareType) VALUES (?,?,?)";
		PreparedStatement psm = JdbcUtil.getPreparedStatement(conn, sql);
		psm.setString(1, role.getName());
		psm.setString(2, role.getRemark());
		psm.setInt(3, role.getEareType());
		int count = psm.executeUpdate();
		JdbcUtil.closeConnectionStateMent(conn, psm);
		return count==1;
	}
	
	public boolean update(Role role) throws SQLException {
		Connection conn = JdbcUtil.getConnect();
		String sql = "UPDATE role SET name = ?,remark = ?,eareType = ? WHERE id = ?";
		PreparedStatement psm = JdbcUtil.getPreparedStatement(conn, sql);
		psm.setString(1, role.getName());
		psm.setString(2, role.getRemark());
		psm.setInt(3, role.getEareType());
		psm.setInt(4, role.getId());
		int count = psm.executeUpdate();
		JdbcUtil.closeConnectionStateMent(conn, psm);
		return count==1;
	}

	public Role getById(int id) throws SQLException {
		Role role = new Role();
		Connection conn = JdbcUtil.getConnect();
		
		String sql = "SELECT * FROM role WHERE id = ?";
		PreparedStatement psm = JdbcUtil.getPreparedStatement(conn, sql);
		psm.setInt(1, id);
		ResultSet set = psm.executeQuery();
		
		if(set.next()){
			role.setId(set.getInt(1));
			role.setName(set.getString(2));
			role.setRemark(set.getString(3));
			role.setEareType(set.getInt(4));
			role.setMenus(PermissionDao.getInstance().getPermissionByRoleId(role.getId()));
		}else{
			role = null;
		}
		JdbcUtil.closeConnectionStateMent(conn, psm);
		return role;
	}

	public boolean deleteById(int id) throws SQLException {
		Connection conn = JdbcUtil.getConnect();
		
		String sql = "DELETE  FROM role WHERE id = ?";
		PreparedStatement psm = JdbcUtil.getPreparedStatement(conn, sql);
		psm.setInt(1, id);
		int reuslt = psm.executeUpdate();
		JdbcUtil.closeConnectionStateMent(conn, psm);
		return reuslt == 1;
	}
	
	public boolean updateRolePermissionById(int roleId,String[] permissiones) throws SQLException {
		Connection conn = JdbcUtil.getConnect();
		PreparedStatement psm;
		//开启mysql的事务
		conn.setAutoCommit(false);
		try {
			//1、删除原有的权限
			String sql = "delete FROM role_menu WHERE roleId = ?";
			psm = JdbcUtil.getPreparedStatement(conn, sql);
			psm.setInt(1, roleId);
			psm.execute();
			//2、添加新的权限
			if (permissiones != null) {
				for (String permission : permissiones) {
					sql = "INSERT INTO role_menu(roleId,menuId) VALUES(?,?) ";
					psm = JdbcUtil.getPreparedStatement(conn, sql);
					psm.setInt(1, roleId);
					psm.setString(2, permission);
					psm.execute();
				}
			}
			conn.commit();
		} catch (Exception e) {
			conn.rollback();
			return false;
		}
		JdbcUtil.closeConnectionStateMent(conn, psm);
		return true;
	}
}
