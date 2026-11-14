package com.huizi.leave.leavemanagementsystem.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import com.huizi.leave.leavemanagementsystem.domain.Clazz;
import com.huizi.leave.leavemanagementsystem.domain.Student;
import com.huizi.leave.leavemanagementsystem.domain.StudentQueryCondition;
import com.huizi.leave.leavemanagementsystem.page.Page;
import com.huizi.leave.leavemanagementsystem.session.QingjiaSesssion;
import com.huizi.leave.leavemanagementsystem.util.JdbcUtil;

public class StudentDao {
	private static StudentDao instance;
	private  StudentDao() {}
	static{
		instance = new StudentDao();
	}
	public static StudentDao getInstance() {
		return instance;
	}
	
	public Student getByNum(String num) throws SQLException{
		Student student = new Student();
		Connection conn = JdbcUtil.getConnect();
		String sql = "select * from student where num like ?";
		PreparedStatement psm = JdbcUtil.getPreparedStatement(conn, sql);
		psm.setString(1, num);
		ResultSet set = psm.executeQuery();
		if (set.next()) {
			student.setId(set.getInt(1));
			student.setName(set.getString(2));
			student.setNum(set.getString(3));
			student.setPassword(set.getString(4));
			student.setClassId(set.getInt(5));
			student.setSex(set.getInt(6));
		}else{
			student = null;
		}
		JdbcUtil.closeConnectionStateMent(conn, psm);
		return student;
	}

	public int getTotalRecord(QingjiaSesssion qSesssion, StudentQueryCondition condition) throws SQLException {
		Connection conn = JdbcUtil.getConnect();
		String strCondition = getCondition(condition);
		StringBuilder builder = new StringBuilder();
		StringBuilder sqlBuilder = new StringBuilder("select count(*) from student_view ");
		PreparedStatement psm = null;
		String areaClause = "";
		if (qSesssion.getDataArea()<=QingjiaSesssion.DATA_AREA_CLASS) {
			for (Clazz  clazz : qSesssion.getClazzes()) {
				builder.append(clazz.getId());
				builder.append(",");
			}
			if (builder.length()>0) {
				builder.replace(builder.length()-1,builder.length(), "");
				areaClause = builder.toString();
			}
			sqlBuilder.append(" WHERE classId IN (?) ");
			
			if (strCondition != null) {
				sqlBuilder.append(" AND ");
				sqlBuilder.append(strCondition);
			}
			psm = JdbcUtil.getPreparedStatement(conn, sqlBuilder.toString());
			psm.setString(1, areaClause);
		}else if(qSesssion.getDataArea() == QingjiaSesssion.DATA_AREA_COLLEGE){
			sqlBuilder.append(" WHERE belongToCollegeId = ?");
			if ( strCondition != null) {
				sqlBuilder.append(" AND ");
				sqlBuilder.append(strCondition);
				psm = JdbcUtil.getPreparedStatement(conn, sqlBuilder.toString());
				psm.setInt(1, qSesssion.getCollegeId());
			}
			
		}else {
			if ( strCondition != null) {
				sqlBuilder.append(" WHERE ");
				sqlBuilder.append(strCondition);
				psm = JdbcUtil.getPreparedStatement(conn, sqlBuilder.toString());				
			}else {
				psm = JdbcUtil.getPreparedStatement(conn, sqlBuilder.toString());				
			}
		}
		ResultSet set = psm.executeQuery();
		int count = 0;
		if (set.next()) {
			count = set.getInt(1);
		}
		JdbcUtil.closeConnectionStateMent(conn, psm);
		return count;
	}

	private String getCondition(StudentQueryCondition condition) {
		StringBuilder builder = new StringBuilder();
		if (condition.getCollegeId() !=0 ) {
			builder.append(" belongToCollegeId = " + condition.getCollegeId());
		}
		if (condition.getGradeId() !=0) {
			if (builder.length()>0) {
				builder.append(" AND gradeId = " + condition.getGradeId() );
			}else {
				builder.append(" gradeId = "+condition.getGradeId() );
			}
		}
		if (condition.getClassId()!=0) {
			if (builder.length()>0) {
				builder.append(" AND classId = " + condition.getClassId());
			}else {
				builder.append(" classId = " + condition.getClassId());
			}
		}
		if (condition.getName()!=null && condition.getName().trim().length()!=0) {
			if (builder.length()>0) {
				builder.append(" AND name like '%" + condition.getName() + "%'");
			}else {
				builder.append(" name like '%" + condition.getName() +"%'");
			}
		}
		if (condition.getNum() != null && condition.getNum().trim().length()!=0) {
			if (builder.length()>0) {
				builder.append(" AND num like '%" + condition.getNum() +"%'");
			}else {
				builder.append(" num like '%" + condition.getNum() +"%'");
			}
		}
		
		return builder.length()==0?null:builder.toString();
	}

	public List<Student> queryPage(QingjiaSesssion qSesssion, StudentQueryCondition condition, Page page) throws SQLException {
		List<Student> students = new ArrayList<Student>();
		Connection conn = JdbcUtil.getConnect();
		String strCondition = getCondition(condition);
		StringBuilder builder = new StringBuilder();
		StringBuilder sqlBuilder = new StringBuilder("select * from student_view ");
		PreparedStatement psm = null;
		String areaClause = "";
		if (qSesssion.getDataArea()<=QingjiaSesssion.DATA_AREA_CLASS) {
			for (Clazz  clazz : qSesssion.getClazzes()) {
				builder.append(clazz.getId());
				builder.append(",");
			}
			if (builder.length()>0) {
				builder.replace(builder.length()-1,builder.length(), "");
				areaClause = builder.toString();
			}
			sqlBuilder.append(" WHERE classId IN (?) ");
			
			if (strCondition != null) {
				sqlBuilder.append(" AND ");
				sqlBuilder.append(strCondition);
			}
			sqlBuilder.append(" ORDER BY id DESC ");
			sqlBuilder.append(" limit " + ((page.getPageNo()-1)*page.getPageSize()+ "," +page.getPageSize()));
			
			psm = JdbcUtil.getPreparedStatement(conn, sqlBuilder.toString());
			psm.setString(1, areaClause);
		}else if(qSesssion.getDataArea() == QingjiaSesssion.DATA_AREA_COLLEGE){
			sqlBuilder.append(" WHERE belongToCollegeId = ?");
			if ( strCondition != null) {
				sqlBuilder.append(" AND ");
				sqlBuilder.append(strCondition);
				psm = JdbcUtil.getPreparedStatement(conn, sqlBuilder.toString());
				psm.setInt(1, qSesssion.getCollegeId());
			}
			sqlBuilder.append(" ORDER BY id DESC ");
			sqlBuilder.append(" limit " + ((page.getPageNo()-1)*page.getPageSize()+ "," +page.getPageSize()));
			psm = JdbcUtil.getPreparedStatement(conn, sqlBuilder.toString());				
		}else {
			if ( strCondition != null) {
				sqlBuilder.append(" WHERE ");
				sqlBuilder.append(strCondition);
			}
			sqlBuilder.append(" ORDER BY id DESC ");
			sqlBuilder.append(" limit " + ((page.getPageNo()-1)*page.getPageSize()+ "," +page.getPageSize()));
			psm = JdbcUtil.getPreparedStatement(conn, sqlBuilder.toString());				
		}
		
		ResultSet set = psm.executeQuery();
		Student student;
		while(set.next()) {
			student = new Student();
			student.setId(set.getInt(1));
			student.setName(set.getString(2));
			student.setNum(set.getString(3));
			student.setClassId(set.getInt(4));
			student.setSex(set.getInt(5));
			student.setClassNum(set.getString(6));
			student.setSexName(set.getString(7));
			students.add(student);
		}
		JdbcUtil.closeConnectionStateMent(conn, psm);
		return students;
	}

	public boolean add(Student student) throws SQLException {
		Connection conn = JdbcUtil.getConnect();
		String sql = "INSERT INTO student(name,num,password,classId,sex) VALUES (?,?,?,?,?)";
		PreparedStatement psm = JdbcUtil.getPreparedStatement(conn, sql);
		psm.setString(1, student.getName());
		psm.setString(2, student.getNum());
		psm.setString(3, student.getPassword());
		psm.setInt(4, student.getClassId());
		psm.setInt(5, student.getSex());
		
		int count = psm.executeUpdate();
		JdbcUtil.closeConnectionStateMent(conn, psm);
		return count==1;
	}

	public Student getByid(int id) throws SQLException {
		Student student = new Student();
		Connection conn = JdbcUtil.getConnect();
		String sql = "select * from student_view where id = ?";
		PreparedStatement psm = JdbcUtil.getPreparedStatement(conn, sql);
		psm.setInt(1, id);
		ResultSet set = psm.executeQuery();
		if (set.next()) {
			student.setId(set.getInt(1));
			student.setName(set.getString(2));
			student.setNum(set.getString(3));
			student.setClassId(set.getInt(4));
			student.setSex(set.getInt(5));
			student.setClassNum(set.getString(6));
			student.setSexName(set.getString(7));
			student.setBelongToCollegeId(set.getInt(8));
			student.setGradeId(set.getInt(9));
		}else{
			student = null;
		}
		JdbcUtil.closeConnectionStateMent(conn, psm);
		return student;
	}

	public boolean update(Student student) throws SQLException {
		Connection conn = JdbcUtil.getConnect();
		String sql = "UPDATE student SET name = ?,num = ?,classId = ?,sex = ? WHERE id = ?";
		PreparedStatement psm = JdbcUtil.getPreparedStatement(conn, sql);
		psm.setString(1, student.getName());
		psm.setString(2, student.getNum());
		psm.setInt(3, student.getClassId());
		psm.setInt(4, student.getSex());
		psm.setInt(5, student.getId());
		int count = psm.executeUpdate();
		JdbcUtil.closeConnectionStateMent(conn, psm);
		return count==1;
	}

	public boolean resetPassword(int id) throws SQLException {
		Connection conn = JdbcUtil.getConnect();
		String sql = "UPDATE student SET password = num  WHERE id = ?";
		PreparedStatement psm = JdbcUtil.getPreparedStatement(conn, sql);
		psm.setInt(1, id);
		int count = psm.executeUpdate();
		JdbcUtil.closeConnectionStateMent(conn, psm);
		return count==1;
	}

	public boolean updatePassword(int id, String newPassword) throws SQLException {
		Connection conn = JdbcUtil.getConnect();
		String sql = "UPDATE student SET password = ?  WHERE id = ?";
		PreparedStatement psm = JdbcUtil.getPreparedStatement(conn, sql);
		psm.setString(1, newPassword);
		psm.setInt(2, id);
		int count = psm.executeUpdate();
		JdbcUtil.closeConnectionStateMent(conn, psm);
		return count==1;
	}
}
