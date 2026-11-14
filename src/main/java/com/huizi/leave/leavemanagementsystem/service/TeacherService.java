package com.huizi.leave.leavemanagementsystem.service;

import java.sql.SQLException;
import java.util.List;

import com.huizi.leave.leavemanagementsystem.dao.TeacherDao;
import com.huizi.leave.leavemanagementsystem.domain.Teacher;
import com.huizi.leave.leavemanagementsystem.page.Page;

public class TeacherService {
	private TeacherService(){};
	private static TeacherService instance;
	static{
		instance = new TeacherService();
	}
	public static TeacherService getInstance() {
		return instance;
	}
	public Teacher getByNum(String num) throws SQLException {
		return TeacherDao.getInstance().getByNum(num);
	}
	
	public List<Teacher> getByName(String name) throws SQLException {
		return TeacherDao.getInstance().getByName(name);
	}
	public int getTotalRecordes(Teacher teacher) throws SQLException {
		return TeacherDao.getInstance().getTotalRecordes(teacher);
	}
	public List<Teacher> getAll(Teacher teacher, Page page) throws SQLException {
		return TeacherDao.getInstance().getAll(teacher,page);
	}
	public boolean add(Teacher teacher) throws SQLException {
		return TeacherDao.getInstance().add(teacher);
	}
	public Teacher getById(int id) throws SQLException {
		return TeacherDao.getInstance().getById(id);
	}
	public boolean update(Teacher teacher) throws SQLException {
		return TeacherDao.getInstance().update(teacher);
	}
	public boolean updateRoles(int id, String[] permissiones) throws SQLException {
		return TeacherDao.getInstance().updateRoles(id,permissiones);
	}
	public boolean ResetPassword(int id) throws SQLException {
		return TeacherDao.getInstance().ResetPassword(id);
	}
}
