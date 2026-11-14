package com.huizi.leave.leavemanagementsystem.service;

import java.sql.SQLException;
import java.util.List;


import com.huizi.leave.leavemanagementsystem.dao.ClassDao;
import com.huizi.leave.leavemanagementsystem.domain.Clazz;
import com.huizi.leave.leavemanagementsystem.domain.Grade;
import com.huizi.leave.leavemanagementsystem.page.Page;

public class ClassService {
	private static ClassService instance;
	private ClassService(){};
	static{
		instance = new ClassService();
	}
	public static ClassService getInstance() {
		return instance;
	}
	
	public List<Clazz> getByBanzhurenId(int id) throws SQLException {
		return ClassDao.getInstance().getByBanzhurenId(id);
	}

	public List<Clazz> getAll(Clazz clazz,String whereClause,Page page) throws SQLException {
		// TODO Auto-generated method stub
		return ClassDao.getInstance().getAll(clazz,whereClause,page);
	}

	public Clazz getById(int id) throws SQLException {
		// TODO Auto-generated method stub
		return ClassDao.getInstance().getById(id);
	}

	public boolean add(Clazz clazz) throws SQLException {
		// TODO Auto-generated method stub
		
		return ClassDao.getInstance().add(clazz);
	}

	public boolean update(Clazz clazz) throws SQLException {
		// TODO Auto-generated method stub
		return ClassDao.getInstance().update(clazz);
	}


	public List<Grade> getGrade() throws SQLException {
		// TODO Auto-generated method stub
		return ClassDao.getInstance().getGrade();
	}

	public Clazz getDetailById(int id) throws SQLException {
		// TODO Auto-generated method stub
		return ClassDao.getInstance().getDetailById(id);
	}

	public boolean setClassBanzhuren(int classid, int teacherid) throws SQLException {
		// TODO Auto-generated method stub
		return ClassDao.getInstance().setClassBanzhuren(classid,teacherid);
	}

	public int getTotalRecord(Clazz clazz, String whereClause) throws SQLException {
		// TODO Auto-generated method stub
		return ClassDao.getInstance().getTotalRecord(clazz, whereClause);
	}

	public List<Grade> getGradesByIds(String strGrades) throws SQLException {
		// TODO Auto-generated method stub
		return ClassDao.getInstance().getGradesByIds(strGrades);
	}

	public List<Clazz> getByCollegeAndGrade(int college, int grade) throws SQLException {
		// TODO Auto-generated method stub
		return ClassDao.getInstance().getByCollegeAndGrade(college,grade);
	}

}
