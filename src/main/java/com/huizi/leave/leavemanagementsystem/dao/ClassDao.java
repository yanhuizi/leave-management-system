package com.huizi.leave.leavemanagementsystem.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import com.huizi.leave.leavemanagementsystem.domain.Clazz;
import com.huizi.leave.leavemanagementsystem.domain.Grade;
import com.huizi.leave.leavemanagementsystem.page.Page;
import com.huizi.leave.leavemanagementsystem.util.JdbcUtil;

public class ClassDao {
	private static ClassDao instance;
	private ClassDao(){};
	static{
		instance = new ClassDao();
	}
	public static ClassDao getInstance() {
		return instance;
	}
	
	public List<Clazz> getByBanzhurenId(int id) throws SQLException {
		List<Clazz> clazzs = new ArrayList<Clazz>();
		Connection conn = JdbcUtil.getConnect();
		String sql = "SELECT * FROM class WHERE banZhuRenId = ?";
		PreparedStatement psm = JdbcUtil.getPreparedStatement(conn, sql);
		psm.setInt(1, id);
		ResultSet set = psm.executeQuery();
		Clazz clazz;
		while(set.next()){
			clazz = new Clazz();
			clazz.setId(set.getInt(1));
			clazz.setNum(set.getString(2));
			clazz.setBelongToCollegeId(set.getInt(3));
			clazz.setBanZhuRenId(set.getInt(4));
			clazzs.add(clazz);
		}
		JdbcUtil.closeConnectionStateMent(conn, psm);
		return clazzs;
	}

	public List<Clazz> getAll(Clazz clazzCondition,String whereClause,Page page) throws SQLException {
		List<Clazz> clazzs = new ArrayList<Clazz>();
		Connection conn = JdbcUtil.getConnect();
		String sql;
		StringBuilder builder = new StringBuilder();
		if (null  == whereClause) {
			if (clazzCondition.getBelongToCollegeId() != 0 ||clazzCondition.getGradeId() != 0 ) {
				if (clazzCondition.getBelongToCollegeId() != 0 && clazzCondition.getGradeId() != 0) {
					builder.append("SELECT * FROM class_view WHERE gradeId = " 
				          + clazzCondition.getGradeId() + 
				          " AND belongToCollegeId = " +clazzCondition.getBelongToCollegeId());
				          
				}else if(clazzCondition.getBelongToCollegeId() != 0){
					builder.append("SELECT * FROM class_view WHERE " 
					          +" belongToCollegeId = " +clazzCondition.getBelongToCollegeId());
				}else{
					builder.append("SELECT * FROM class_view WHERE gradeId = " 
					          + clazzCondition.getGradeId());
				}
			}else{
				builder.append("SELECT * FROM class_view");
			}
		}else{
			builder.append("SELECT * FROM class_view WHERE " + whereClause);
			
			if (clazzCondition.getBelongToCollegeId() != 0) {
				builder.append(" AND belongToCollegeId = " +clazzCondition.getBelongToCollegeId());
			} 
			if (clazzCondition.getGradeId() != 0) {
				builder.append(" AND gradeId = " +clazzCondition.getGradeId());
			}
		}
		builder.append(" ORDER BY id DESC ");
		if (null != page) {
			builder.append(" limit " + ((page.getPageNo()-1)*page.getPageSize()+ "," +page.getPageSize()));
		}
		sql = builder.toString();
		PreparedStatement psm = JdbcUtil.getPreparedStatement(conn, sql);
		ResultSet set = psm.executeQuery();
		Clazz clazz;
		while(set.next()){
			clazz = new Clazz();
			setDetail(clazz, set);
			clazzs.add(clazz);
		}
		JdbcUtil.closeConnectionStateMent(conn, psm);
		return clazzs;
	}

	public List<Grade> getGrade() throws SQLException {
		List<Grade> grades = new ArrayList<Grade>();
		Connection conn = JdbcUtil.getConnect();
		String sql = "SELECT * FROM grade ORDER BY id DESC";
		PreparedStatement psm = JdbcUtil.getPreparedStatement(conn, sql);
		ResultSet set = psm.executeQuery();
		Grade grade;
		while(set.next()){
			grade = new Grade();
			grade.setId(set.getInt(1));
			grade.setGrade(set.getInt(2));
			grades.add(grade);
		}
		JdbcUtil.closeConnectionStateMent(conn, psm);
		return grades;
	}

	public boolean add(Clazz clazz) throws SQLException {
		Connection conn = JdbcUtil.getConnect();
		String sql = "INSERT INTO class(num,gradeId,belongToCollegeId) VALUES (?,?,?)";
		PreparedStatement psm = JdbcUtil.getPreparedStatement(conn, sql);
		psm.setString(1, clazz.getNum());
		psm.setInt(2, clazz.getGradeId());
		psm.setInt(3, clazz.getBelongToCollegeId());
		int count = psm.executeUpdate();
		JdbcUtil.closeConnectionStateMent(conn, psm);
		return count==1;
	}

	public Clazz getById(int id) throws SQLException {
		Clazz clazz = null;
		Connection conn = JdbcUtil.getConnect();
		
		String sql = "SELECT * FROM class WHERE id = ?";
		PreparedStatement psm = JdbcUtil.getPreparedStatement(conn, sql);
		psm.setInt(1, id);
		ResultSet set = psm.executeQuery();
		
		if(set.next()){
			clazz = new Clazz();
			clazz.setId(set.getInt(1));
			clazz.setNum(set.getString(2));
			clazz.setGradeId(set.getInt(3));
			clazz.setBelongToCollegeId(set.getInt(4));
		}
		JdbcUtil.closeConnectionStateMent(conn, psm);
		return clazz;
	}

	public boolean update(Clazz clazz) throws SQLException {
		Connection conn = JdbcUtil.getConnect();
		String sql = "UPDATE class SET num = ?,gradeId = ?,belongToCollegeId = ? WHERE id = ?";
		PreparedStatement psm = JdbcUtil.getPreparedStatement(conn, sql);
		psm.setString(1, clazz.getNum());
		psm.setInt(2, clazz.getGradeId());
		psm.setInt(3, clazz.getBelongToCollegeId());
		psm.setInt(4, clazz.getId());
		int count = psm.executeUpdate();
		JdbcUtil.closeConnectionStateMent(conn, psm);
		return count==1;
	}

	public Clazz getDetailById(int id) throws SQLException {
		Clazz clazz = null;
		Connection conn = JdbcUtil.getConnect();
		
		String sql = "SELECT * FROM class_view WHERE id = ?";
		PreparedStatement psm = JdbcUtil.getPreparedStatement(conn, sql);
		psm.setInt(1, id);
		ResultSet set = psm.executeQuery();
		
		if(set.next()){
			clazz = new Clazz();
			setDetail(clazz, set);
		}
		JdbcUtil.closeConnectionStateMent(conn, psm);
		return clazz;
	}

	private void setDetail(Clazz clazz, ResultSet set) throws SQLException {
		clazz.setId(set.getInt(1));
		clazz.setNum(set.getString(2));
		clazz.setGradeId(set.getInt(3));
		clazz.setBelongToCollegeId(set.getInt(4));
		clazz.setBanZhuRenId(set.getInt(5));
		clazz.setGrade(set.getString(6));
		clazz.setBelongToCollege(set.getString(7));
		clazz.setBanZhuRen(set.getString(8));
	}

	public boolean setClassBanzhuren(int classid, int teacherid) throws SQLException {
		Connection conn = JdbcUtil.getConnect();
		String sql = "UPDATE class SET banZhuRenId = ? WHERE id = ?";
		PreparedStatement psm = JdbcUtil.getPreparedStatement(conn, sql);
		psm.setInt(1, teacherid);
		psm.setInt(2, classid);
		int count = psm.executeUpdate();
		JdbcUtil.closeConnectionStateMent(conn, psm);
		return count==1;
	}

	public int getTotalRecord(Clazz clazzCondition, String whereClause) throws SQLException {
		
		Connection conn = JdbcUtil.getConnect();
		String sql;
		int totalRecord = 0;
		if (null  == whereClause) {
			if (clazzCondition.getBelongToCollegeId() != 0 ||clazzCondition.getGradeId() != 0 ) {
				if (clazzCondition.getBelongToCollegeId() != 0 && clazzCondition.getGradeId() != 0) {
					sql = "SELECT count(*) as count FROM class_view WHERE gradeId = " 
				          + clazzCondition.getGradeId() + 
				          " AND belongToCollegeId = " +clazzCondition.getBelongToCollegeId()
				          + " ORDER BY id DESC";
				}else if(clazzCondition.getBelongToCollegeId() != 0){
					sql = "SELECT count(*) as count FROM class_view WHERE " 
					          +" belongToCollegeId = " +clazzCondition.getBelongToCollegeId()
					          + " ORDER BY id DESC";
				}else{
					sql = "SELECT count(*) as count FROM class_view WHERE gradeId = " 
					          + clazzCondition.getGradeId()
					          + " ORDER BY id DESC";
				}
			}else{
				sql = "SELECT count(*) as count FROM class_view ORDER BY id DESC";
			}
		}else{
			StringBuilder builder = new StringBuilder();
			builder.append("SELECT count(*) as count FROM class_view WHERE " + whereClause);
			
			if (clazzCondition.getBelongToCollegeId() != 0) {
				builder.append("AND belongToCollegeId = " +clazzCondition.getBelongToCollegeId());
			} 
			if (clazzCondition.getGradeId() != 0) {
				builder.append(" AND gradeId = " +clazzCondition.getGradeId());
			}
			builder.append(" ORDER BY id DESC");
			sql = builder.toString();
		}
		
		PreparedStatement psm = JdbcUtil.getPreparedStatement(conn, sql);
		ResultSet set = psm.executeQuery();
		
		if(set.next()){
			totalRecord = set.getInt(1); 
		}
		JdbcUtil.closeConnectionStateMent(conn, psm);
		
		return totalRecord;
	}

	public List<Grade> getGradesByIds(String strGrades) throws SQLException {
		List<Grade> grades = new ArrayList<Grade>();
		Connection conn = JdbcUtil.getConnect();
		
		String sql = "SELECT * FROM grade WHERE id in (?)";
		PreparedStatement psm = JdbcUtil.getPreparedStatement(conn, sql);
		psm.setString(1, strGrades);
		ResultSet set = psm.executeQuery();
		Grade grade;
		if(set.next()){
			grade = new Grade();
			grade.setId(set.getInt(1));
			grade.setGrade(set.getInt(2));
			grades.add(grade);
		}
		JdbcUtil.closeConnectionStateMent(conn, psm);
		return grades;
	}

	public List<Clazz> getByCollegeAndGrade(int college, int grade) throws SQLException {
		List<Clazz> clazzs = new ArrayList<>();
		Connection conn = JdbcUtil.getConnect();
		PreparedStatement psm;
		if (college != 0 && grade !=0) {
			String sql = "SELECT * FROM class WHERE belongToCollegeId = ? AND gradeId = ?";
			psm = JdbcUtil.getPreparedStatement(conn, sql);
			psm.setInt(1, college);
			psm.setInt(2, grade);
		}else if(college != 0){
			String sql = "SELECT * FROM class WHERE belongToCollegeId = ?";
			psm = JdbcUtil.getPreparedStatement(conn, sql);
			psm.setInt(1, college);
		}else{
			String sql = "SELECT * FROM class WHERE gradeId = ?";
			psm = JdbcUtil.getPreparedStatement(conn, sql);
			psm.setInt(1, grade);
		}
		ResultSet set = psm.executeQuery();
		Clazz clazz;
		while(set.next()){
			clazz = new Clazz();
			clazz.setId(set.getInt(1));
			clazz.setNum(set.getString(2));
			clazz.setGradeId(set.getInt(3));
			clazz.setBelongToCollegeId(set.getInt(4));
			clazzs.add(clazz);
		}
		JdbcUtil.closeConnectionStateMent(conn, psm);
		return clazzs;
	}

}
