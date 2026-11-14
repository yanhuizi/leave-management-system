package com.huizi.leave.leavemanagementsystem.service;

import java.sql.SQLException;
import java.util.List;


import com.huizi.leave.leavemanagementsystem.dao.RoleDao;
import com.huizi.leave.leavemanagementsystem.domain.Role;

public class RoleService {
	private static RoleService instance;
	private RoleService(){};
	static{
		instance = new RoleService();
	}
	public static RoleService getInstance() {
		return instance;
	}
	
	public List<Role> getRoleListByUserID(int userid) throws SQLException {
		return RoleDao.getInstance().getRoleListByUserID(userid);
	}
	
	public List<Role> getAll() throws SQLException {
		return RoleDao.getInstance().getAll();
	}
	public boolean add(Role role) throws SQLException {
		return RoleDao.getInstance().add(role);
	}
	
	public boolean update(Role role) throws SQLException {
		return RoleDao.getInstance().update(role);
	}

	public Role getById(int id) throws SQLException {
		return RoleDao.getInstance().getById(id);
	}

	public boolean deleteById(int id) throws SQLException {
		return RoleDao.getInstance().deleteById(id);
	}
	
	public boolean updateRolePermissionById(int roleId,String[] permissiones) throws SQLException {
		return RoleDao.getInstance().updateRolePermissionById(roleId, permissiones);
	}
}
