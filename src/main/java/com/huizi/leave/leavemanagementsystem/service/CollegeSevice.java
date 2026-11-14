package com.huizi.leave.leavemanagementsystem.service;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.huizi.leave.leavemanagementsystem.dao.CollegeDao;
import com.huizi.leave.leavemanagementsystem.domain.College;

public class CollegeSevice {
	private static CollegeSevice instance;
	private  CollegeSevice() {}
	static{
		instance = new CollegeSevice();
	}
	public static CollegeSevice getInstance() {
		return instance;
	}
	public List<College> getAll() throws SQLException {
		return CollegeDao.getInstance().getAll();
	}
	public boolean add(College college) throws SQLException {
		return CollegeDao.getInstance().add(college);
	}
	public College getById(String id) throws SQLException {
		return CollegeDao.getInstance().getById(id);
	}
	public boolean updateById(College college) throws SQLException {
		return CollegeDao.getInstance().updateById(college);
	}
	public List<College> getByRequest(HttpServletRequest request) throws SQLException {
		return CollegeDao.getInstance().getByRequest(request);
	}
}
