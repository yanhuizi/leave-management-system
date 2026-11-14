package com.huizi.leave.leavemanagementsystem.service;
import java.sql.SQLException;
import java.util.List;

import com.huizi.leave.leavemanagementsystem.dao.StudentDao;
import com.huizi.leave.leavemanagementsystem.domain.Student;
import com.huizi.leave.leavemanagementsystem.domain.StudentQueryCondition;
import com.huizi.leave.leavemanagementsystem.page.Page;
import com.huizi.leave.leavemanagementsystem.session.QingjiaSesssion;

public class StudentService {
	private static StudentService instance;
	private  StudentService() {}
	static{
		instance = new StudentService();
	}
	public static StudentService getInstance() {
		return instance;
	}
	
	public Student getByNum(String num) throws SQLException{
		return StudentDao.getInstance().getByNum(num);
	}

	public int getTotalRecord(QingjiaSesssion qSesssion, StudentQueryCondition condition) throws SQLException {
		// TODO Auto-generated method stub
		return StudentDao.getInstance().getTotalRecord(qSesssion, condition);
	}

	public List<Student> queryPage(QingjiaSesssion qSesssion, StudentQueryCondition condition, Page page) throws SQLException {
		// TODO Auto-generated method stub
		return StudentDao.getInstance().queryPage(qSesssion, condition, page);
	}

	public boolean add(Student student) throws SQLException {
		// TODO Auto-generated method stub
		return StudentDao.getInstance().add(student);
	}

	public Student getByid(int id) throws SQLException {
		// TODO Auto-generated method stub
		return StudentDao.getInstance().getByid(id);
	}

	public boolean update(Student student) throws SQLException {
		// TODO Auto-generated method stub
		return StudentDao.getInstance().update(student);
	}

	public boolean resetPassword(int id) throws SQLException {
		// TODO Auto-generated method stub
		return StudentDao.getInstance().resetPassword(id);
	}

	public boolean updatePassword(int id,String newPassword) throws SQLException {
		// TODO Auto-generated method stub
		return StudentDao.getInstance().updatePassword(id,newPassword);
	}
}
