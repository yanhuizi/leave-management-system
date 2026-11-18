package com.huizi.leave.leavemanagementsystem.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.huizi.leave.leavemanagementsystem.domain.Teacher;
import com.huizi.leave.leavemanagementsystem.page.Page;
import com.huizi.leave.leavemanagementsystem.util.JdbcUtil;

public class TeacherDao {
	private static TeacherDao instance;
	private  TeacherDao() {}
	static{
		instance = new TeacherDao();
	}
	public static TeacherDao getInstance() {
		return instance;
	}
	
	//根据教师编号，查询教师
	public Teacher getByNum(String num) throws SQLException {
		Teacher teacher = new Teacher();
		Connection conn = JdbcUtil.getConnect();
		String sql = "select * from teacher where num like ?";
		PreparedStatement psm = JdbcUtil.getPreparedStatement(conn, sql);
		psm.setString(1, num);
		ResultSet set = psm.executeQuery();
		if (set.next()) {
			teacher.setId(set.getInt(1));
			teacher.setName(set.getString(2));
			teacher.setNum(set.getString(3));
			teacher.setPassword(set.getString(4));
			teacher.setCollegeId(set.getInt(5));
		}else{
			teacher = null;
		}
		JdbcUtil.closeConnectionStateMent(conn, psm);
		return teacher;
	}

	public List<Teacher> getByName(String name) throws SQLException {
		List<Teacher> teacheres = new ArrayList<Teacher>();
		Connection conn = JdbcUtil.getConnect();
		String sql = "select * from teacher_view where name like ?";
		PreparedStatement psm = JdbcUtil.getPreparedStatement(conn, sql);
		psm.setString(1, "%" + name +"%");
		ResultSet set = psm.executeQuery();
		
		while (set.next()) {
			getFromSet(teacheres, set);
		}
		JdbcUtil.closeConnectionStateMent(conn, psm);
		return teacheres;
	}

	private void getFromSet(List<Teacher> teacheres, ResultSet set) throws SQLException {
		Teacher teacher;
		teacher = new Teacher();
		teacher.setId(set.getInt(1));
		teacher.setName(set.getString(2));
		teacher.setNum(set.getString(3));
		teacher.setPassword(set.getString(4));
		teacher.setCollegeId(set.getInt(5));
		teacher.setPhone(set.getNString(6));
		teacher.setCollegeName(set.getString(7));
		teacheres.add(teacher);
	}

	public int getTotalRecordes(Teacher teacher) throws SQLException {
		int totalRecordes = 0;
		Connection conn = JdbcUtil.getConnect();
		StringBuilder builder = new StringBuilder();
		String sql = "";
		builder.append("select count(*) as count from teacher ");
		PreparedStatement psm;
		if (teacher.getCollegeId()!=0 || (teacher.getName()!=null&&!"".equals(teacher.getName()))){
			if (teacher.getCollegeId()!=0 && (teacher.getName()!=null&&!"".equals(teacher.getName()))){
				builder.append(" WHERE collegeId =? AND name LIKE ?");
				sql = builder.toString();
				psm = JdbcUtil.getPreparedStatement(conn, sql);
				psm.setInt(1, teacher.getCollegeId());
				psm.setString(2, "%" + teacher.getName() + "%");
			}else if(teacher.getCollegeId()!=0){
				builder.append(" WHERE collegeId = ?");
				sql = builder.toString();
				psm = JdbcUtil.getPreparedStatement(conn, sql);
				psm.setInt(1, teacher.getCollegeId());
			}else{
				builder.append(" WHERE name LIKE ?");
				sql = builder.toString();
				psm = JdbcUtil.getPreparedStatement(conn, sql);
				psm.setString(1, "%" + teacher.getName() + "%");
			}
		}else{
			sql = "select count(*) as count from teacher ";
			psm = JdbcUtil.getPreparedStatement(conn, sql);
		}
		ResultSet set = psm.executeQuery();
		
		if (set.next()) {
			totalRecordes = set.getInt(1);
		}
		JdbcUtil.closeConnectionStateMent(conn, psm);
		return totalRecordes;
	}

	public List<Teacher> getAll(Teacher teacher, Page page) throws SQLException {
		List<Teacher> teachers = new ArrayList<Teacher>();
		Connection conn = JdbcUtil.getConnect();
		StringBuilder builder = new StringBuilder();
		String sql = "";
		builder.append("select * from teacher_view ");
		PreparedStatement psm;
		if (teacher.getCollegeId()!=0 || (teacher.getName()!=null&&!"".equals(teacher.getName()))){
			if (teacher.getCollegeId()!=0 && (teacher.getName()!=null&&!"".equals(teacher.getName()))){
				builder.append(" WHERE collegeId =? AND name LIKE ?");
				builder.append(" ORDER BY id DESC ");
				if (null != page) {
					builder.append(" limit " + ((page.getPageNo()-1)*page.getPageSize()+ "," +page.getPageSize()));
				}
				sql = builder.toString();
				psm = JdbcUtil.getPreparedStatement(conn, sql);
				psm.setInt(1, teacher.getCollegeId());
				psm.setString(2, "%" + teacher.getName() + "%");
			}else if(teacher.getCollegeId()!=0){
				builder.append(" WHERE collegeId = ?");
				builder.append(" ORDER BY id DESC ");
				if (null != page) {
					builder.append(" limit " + ((page.getPageNo()-1)*page.getPageSize()+ "," +page.getPageSize()));
				}
				sql = builder.toString();
				psm = JdbcUtil.getPreparedStatement(conn, sql);
				psm.setInt(1, teacher.getCollegeId());
			}else{
				builder.append(" WHERE name LIKE ?");
				builder.append(" ORDER BY id DESC ");
				if (null != page) {
					builder.append(" limit " + ((page.getPageNo()-1)*page.getPageSize()+ "," +page.getPageSize()));
				}
				sql = builder.toString();
				psm = JdbcUtil.getPreparedStatement(conn, sql);
				psm.setString(1, "%" + teacher.getName() + "%");
			}
		}else{
			builder.append(" ORDER BY id DESC ");
			if (null != page) {
				builder.append(" limit " + ((page.getPageNo()-1)*page.getPageSize()+ "," +page.getPageSize()));
			}
			sql = builder.toString();
			psm = JdbcUtil.getPreparedStatement(conn, sql);
		}
		ResultSet set = psm.executeQuery();
		
		while (set.next()) {
			getFromSet(teachers, set);
		}
		JdbcUtil.closeConnectionStateMent(conn, psm);
		return teachers;
	}

	public boolean add(Teacher teacher) throws SQLException {
		Connection conn = JdbcUtil.getConnect();
		String sql = "INSERT INTO teacher(name,num,password,collegeId,phone) VALUES (?,?,?,?,?)";
		PreparedStatement psm = JdbcUtil.getPreparedStatement(conn, sql);
		psm.setString(1, teacher.getName());
		psm.setString(2, teacher.getNum());
		psm.setString(3, teacher.getPassword());
		psm.setInt(4, teacher.getCollegeId());
		psm.setString(5, teacher.getPhone());
		
		int count = psm.executeUpdate();
		JdbcUtil.closeConnectionStateMent(conn, psm);
		return count==1;
	}

	public Teacher getById(int id) throws SQLException {
		Connection conn = JdbcUtil.getConnect();
		String sql = "select * from teacher_view where id = ?";
		PreparedStatement psm = JdbcUtil.getPreparedStatement(conn, sql);
		psm.setInt(1, id);
		ResultSet set = psm.executeQuery();
		List<Teacher> teacheres = new ArrayList<>();
		if (set.next()) {
			getFromSet(teacheres, set);
		}
		JdbcUtil.closeConnectionStateMent(conn, psm);
		return teacheres.size()>0?teacheres.get(0):null;
	}

	public boolean update(Teacher teacher) throws SQLException {
		Connection conn = JdbcUtil.getConnect();
		String sql = "UPDATE teacher SET name = ?,num = ?,collegeId = ?,phone = ? WHERE id = ?";
		PreparedStatement psm = JdbcUtil.getPreparedStatement(conn, sql);
		psm.setString(1, teacher.getName());
		psm.setString(2, teacher.getNum());
		psm.setInt(3, teacher.getCollegeId());
		psm.setString(4, teacher.getPhone());
		psm.setInt(5, teacher.getId());
		int count = psm.executeUpdate();
		JdbcUtil.closeConnectionStateMent(conn, psm);
		return count==1;
	}

	public boolean updateRoles(int id, String[] permissiones) throws SQLException {
		Connection conn = JdbcUtil.getConnect();
		PreparedStatement psm;
		//开启mysql的事务
		conn.setAutoCommit(false);
		try {
			//1、删除原有的权限
			String sql = "delete FROM user_role WHERE userid = ?";
			psm = JdbcUtil.getPreparedStatement(conn, sql);
			psm.setInt(1, id);
			psm.execute();
			//2、添加新的权限
			if (permissiones != null) {
				for (String permission : permissiones) {
					sql = "INSERT INTO user_role(userid,roleid) VALUES(?,?) ";
					psm = JdbcUtil.getPreparedStatement(conn, sql);
					psm.setInt(1, id);
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

	public boolean ResetPassword(int id) throws SQLException {
		Connection conn = JdbcUtil.getConnect();
		String sql = "UPDATE teacher SET password = num WHERE id = ?";
		PreparedStatement psm = JdbcUtil.getPreparedStatement(conn, sql);
		psm.setInt(1, id);
		int count = psm.executeUpdate();
		JdbcUtil.closeConnectionStateMent(conn, psm);
		return count==1;
	}
	public boolean updatePassword(int id, String newPassword) throws SQLException {
		Connection conn = JdbcUtil.getConnect();
		String sql = "UPDATE teacher SET password = ?  WHERE id = ?";
		PreparedStatement psm = JdbcUtil.getPreparedStatement(conn, sql);
		psm.setString(1, newPassword);
		psm.setInt(2, id);
		int count = psm.executeUpdate();
		JdbcUtil.closeConnectionStateMent(conn, psm);
		return count==1;
	}
}
