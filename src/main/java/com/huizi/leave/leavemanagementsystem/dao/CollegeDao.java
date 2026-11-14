package com.huizi.leave.leavemanagementsystem.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.huizi.leave.leavemanagementsystem.domain.College;
import com.huizi.leave.leavemanagementsystem.session.QingjiaSesssion;
import com.huizi.leave.leavemanagementsystem.util.JdbcUtil;

public class CollegeDao {
	private static CollegeDao instance;
	private  CollegeDao() {}
	static{
		instance = new CollegeDao();
	}
	public static CollegeDao getInstance() {
		return instance;
	}
	public List<College> getAll() throws SQLException {
		// TODO Auto-generated method stub
		List<College> colleges = new ArrayList<College>();
		Connection conn = JdbcUtil.getConnect();
		
		String sql = "SELECT * FROM college";
		PreparedStatement psm = JdbcUtil.getPreparedStatement(conn, sql);
		ResultSet set = psm.executeQuery();
		College College;
		while(set.next()){
			College = new College();
			College.setId(set.getInt(1));
			College.setName(set.getString(2));
			College.setNum(set.getString(3));
			colleges.add(College);
		}
		JdbcUtil.closeConnectionStateMent(conn, psm);
		return colleges;
	}
	public boolean add(College college) throws SQLException {
		// TODO Auto-generated method stub
		Connection conn = JdbcUtil.getConnect();
		String sql = "INSERT INTO college(name,num) VALUES (?,?)";
		PreparedStatement psm = JdbcUtil.getPreparedStatement(conn, sql);
		psm.setString(1, college.getName());
		psm.setString(2, college.getNum());
		int count = psm.executeUpdate();
		JdbcUtil.closeConnectionStateMent(conn, psm);
		return count==1;
	}
	public College getById(String id) throws SQLException {
		// TODO Auto-generated method stub
		Connection conn = JdbcUtil.getConnect();
		
		String sql = "SELECT * FROM college WHERE id = ?";
		PreparedStatement psm = JdbcUtil.getPreparedStatement(conn, sql);
		psm.setInt(1, Integer.parseInt(id));
		ResultSet set = psm.executeQuery();
		College college = null;
		if(set.next()){
			college = new College();
			college.setId(set.getInt(1));
			college.setName(set.getString(2));
			college.setNum(set.getString(3));
			college.setTel(set.getString(4));
		}
		JdbcUtil.closeConnectionStateMent(conn, psm);
		return college;
	}
	public boolean updateById(College college) throws SQLException {
		// TODO Auto-generated method stub
		Connection conn = JdbcUtil.getConnect();
		String sql = "UPDATE college SET name = ?,num = ? WHERE id = ?";
		PreparedStatement psm = JdbcUtil.getPreparedStatement(conn, sql);
		psm.setString(1, college.getName());
		psm.setString(2, college.getNum());
		psm.setInt(3, college.getId());
		int count = psm.executeUpdate();
		JdbcUtil.closeConnectionStateMent(conn, psm);
		return count==1;
	}
	public List<College> getByRequest(HttpServletRequest request) throws SQLException {
		// TODO Auto-generated method stub
		Connection conn = JdbcUtil.getConnect();
		PreparedStatement psm;
		
		HttpSession session = request.getSession();
		QingjiaSesssion qSesssion = (QingjiaSesssion) session.getAttribute(QingjiaSesssion.QINGJIA_SESSION);
		String sql = "";
		if (qSesssion.getDataArea() == QingjiaSesssion.DATA_AREA_SYSTEM) {
			sql = "SELECT * FROM college";
			psm = JdbcUtil.getPreparedStatement(conn, sql);
		}else{
			sql = "SELECT * FROM college WHERE id = ?";
			psm = JdbcUtil.getPreparedStatement(conn, sql);
			psm.setInt(1, qSesssion.getCollegeId());
		}
		ResultSet set = psm.executeQuery();
		List<College> colleges = new ArrayList<>();
		College College;
		while(set.next()){
			College = new College();
			College.setId(set.getInt(1));
			College.setName(set.getString(2));
			College.setNum(set.getString(3));
			colleges.add(College);
		}
		JdbcUtil.closeConnectionStateMent(conn, psm);
		return colleges;
	}
}
