package com.huizi.leave.leavemanagementsystem.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.huizi.leave.leavemanagementsystem.domain.LeaveApplication;
import com.huizi.leave.leavemanagementsystem.domain.LeaveApplicationShenPi;
import com.huizi.leave.leavemanagementsystem.domain.LeaveApplicationShenPiView;
import com.huizi.leave.leavemanagementsystem.domain.LeaveApplicationView;
import com.huizi.leave.leavemanagementsystem.page.Page;
import com.huizi.leave.leavemanagementsystem.util.DateUtil;
import com.huizi.leave.leavemanagementsystem.util.JdbcUtil;

public class LeaveApplicationDao {
	private static LeaveApplicationDao instance;
	private LeaveApplicationDao(){};
	static{
		instance = new LeaveApplicationDao();
	}
	public static LeaveApplicationDao getInstance() {
		return instance;
	}
	public int getTotalRocords(int type, int studentId) throws SQLException {
		int totalRecordes = 0;
		Connection conn = JdbcUtil.getConnect();
		StringBuilder builder = new StringBuilder();
		String sql = "";
		builder.append("select count(*) as count from leave_application WHERE studentId = ? ");
		builder.append(getStatusCondition(type));
		sql = builder.toString();
		PreparedStatement psm = JdbcUtil.getPreparedStatement(conn, sql);
		psm.setInt(1, studentId);
		ResultSet set = psm.executeQuery();
		
		if (set.next()) {
			totalRecordes = set.getInt(1);
		}
		JdbcUtil.closeConnectionStateMent(conn, psm);
		return totalRecordes;
	}

	/**
	 * 申请状态筛选条件：0 全部，1 审批中，2 审批通过，3 结束（未通过或已销假）。
	 */
	private String getStatusCondition(int type) {
		switch (type) {
		case 0:
			return "";
		case 2:
			return " AND status = " + LeaveApplication.STATUS_PASS;
		case 3:
			return " AND status >= " + LeaveApplication.STATUS_NOT_PASS;
		default:
			return " AND status < " + LeaveApplication.STATUS_PASS;
		}
	}

	public List<LeaveApplicationView> getPage(int type, int studentId, Page page) throws SQLException {
		List<LeaveApplicationView> applications = new ArrayList<LeaveApplicationView>();
		Connection conn = JdbcUtil.getConnect();
		StringBuilder builder = new StringBuilder();
		String sql = "";
		builder.append("select *  from leave_application_view WHERE studentId = ? ");
		builder.append(getStatusCondition(type));
		builder.append(" ORDER BY id DESC  ");
		builder.append(" limit " + ((page.getPageNo()-1)*page.getPageSize()+ "," +page.getPageSize()));
		sql = builder.toString();
		PreparedStatement psm = JdbcUtil.getPreparedStatement(conn, sql);
		psm.setInt(1, studentId);
		ResultSet set = psm.executeQuery();
		while (set.next()) {
			getFromSet(applications, set);
		}
		JdbcUtil.closeConnectionStateMent(conn, psm);
		return applications;
	}
	private void getFromSet(List<LeaveApplicationView> applications, ResultSet set) throws SQLException {
		LeaveApplicationView apllicaton;
		apllicaton = new LeaveApplicationView();
		apllicaton.setId(set.getInt(1));
		apllicaton.setStudentId(set.getInt(2));
		apllicaton.setType(set.getInt(3));
		apllicaton.setPath(set.getString(4));
		apllicaton.setReason(set.getString(5));
		apllicaton.setBegintime(set.getTimestamp(6));
		apllicaton.setEndtime(set.getTimestamp(7));
		apllicaton.setStatus(set.getInt(8));
		apllicaton.setStudentName(set.getString(9));
		apllicaton.setNum(set.getString(10));
		apllicaton.setTypeName(set.getString(11));
		apllicaton.setStatusName(set.getString(12));
		applications.add(apllicaton);
	}
	public boolean add(LeaveApplication appply) throws SQLException {
		Connection conn = JdbcUtil.getConnect();
		String sql = "INSERT INTO leave_application(studentId,type,path,reason,begintime,endtime,status) VALUES (?,?,?,?,?,?,?)";
		PreparedStatement psm = JdbcUtil.getPreparedStatement(conn, sql);
		psm.setInt(1, appply.getStudentId());
		psm.setInt(2, appply.getType());
		psm.setString(3, appply.getPath());
		psm.setString(4, appply.getReason());
		psm.setString(5, DateUtil.dateToString(appply.getBegintime()));
		psm.setString(6, DateUtil.dateToString(appply.getEndtime()));
		psm.setInt(7, appply.getStatus());
		int count = psm.executeUpdate();
		JdbcUtil.closeConnectionStateMent(conn, psm);
		return count==1;
	}
	public LeaveApplication getById(int id) throws SQLException {
		LeaveApplication application = new LeaveApplication();
		Connection conn = JdbcUtil.getConnect();
		
		String sql = "SELECT * FROM leave_application WHERE id = ?";
		PreparedStatement psm = JdbcUtil.getPreparedStatement(conn, sql);
		psm.setInt(1, id);
		ResultSet set = psm.executeQuery();
		
		if(set.next()){
			application.setId(set.getInt(1));
			application.setStudentId(set.getInt(2));
			application.setType(set.getInt(3));
			application.setPath(set.getString(4));
			application.setReason(set.getString(5));
			application.setBegintime(set.getTimestamp(6));
			application.setEndtime(set.getTimestamp(7));
			application.setStatus(set.getInt(8));
		}else{
			application = null;
		}
		JdbcUtil.closeConnectionStateMent(conn, psm);
		return application;
	}
	public boolean update(LeaveApplication application) throws SQLException {
		Connection conn = JdbcUtil.getConnect();
		String sql = "UPDATE leave_application SET type=?,path=?,reason=?,begintime=?,endtime=? WHERE id=?";
		PreparedStatement psm = JdbcUtil.getPreparedStatement(conn, sql);	
		psm.setInt(1, application.getType());
		psm.setString(2, application.getPath());
		psm.setString(3, application.getReason());
		psm.setString(4, DateUtil.dateToString(application.getBegintime()));
		psm.setString(5, DateUtil.dateToString(application.getEndtime()));
		psm.setInt(6, application.getId());
		int count = psm.executeUpdate();
		JdbcUtil.closeConnectionStateMent(conn, psm);
		return count==1;
	}
	public LeaveApplicationView getDetailById(int id) throws SQLException {
		LeaveApplicationView application = new LeaveApplicationView();
		Connection conn = JdbcUtil.getConnect();
		
		String sql = "SELECT * FROM leave_application_view WHERE id = ?";
		PreparedStatement psm = JdbcUtil.getPreparedStatement(conn, sql);
		psm.setInt(1, id);
		ResultSet set = psm.executeQuery();
		
		if(set.next()){
			application.setId(set.getInt(1));
			application.setStudentId(set.getInt(2));
			application.setType(set.getInt(3));
			application.setPath(set.getString(4));
			application.setReason(set.getString(5));
			application.setBegintime(set.getTimestamp(6));
			application.setEndtime(set.getTimestamp(7));
			application.setStatus(set.getInt(8));
			application.setStudentName(set.getString(9));
			application.setNum(set.getString(10));
			application.setTypeName(set.getString(11));
			application.setStatusName(set.getString(12));
		}else{
			application = null;
		}
		JdbcUtil.closeConnectionStateMent(conn, psm);
		return application;
	}
	public List<LeaveApplicationShenPiView> getShenpiesById(int id) throws SQLException {
		List<LeaveApplicationShenPiView> shenpies = new ArrayList<LeaveApplicationShenPiView>();
		Connection conn = JdbcUtil.getConnect();
		String sql = "select *  from leave_application_shen_pi_view WHERE applicationId = ?";
		PreparedStatement psm = JdbcUtil.getPreparedStatement(conn, sql);
		psm.setInt(1, id);
		ResultSet set = psm.executeQuery();
		LeaveApplicationShenPiView shenPiView ;
		while (set.next()) {
			shenPiView = new LeaveApplicationShenPiView();
			shenPiView.setId(set.getInt(1));
			shenPiView.setApplicationId(set.getInt(2));
			shenPiView.setTeacherId(set.getInt(3));
			shenPiView.setResult(set.getInt(4));
			shenPiView.setOpinion(set.getString(5));
			shenPiView.setTeacherName(set.getString(6));
			shenPiView.setAproveTypeName(set.getString(7));
			shenpies.add(shenPiView);
		}
		JdbcUtil.closeConnectionStateMent(conn, psm);
		return shenpies;
	}
	public int getBanzhurenShenPiTotalRocords(int statusDaiShenPi, String manageClasses) throws SQLException {
		int totalRecordes = 0;
		Connection conn = JdbcUtil.getConnect();
		String sql = "select count(*) as count from leave_application_view WHERE status = ? AND classId in (?) ";
		PreparedStatement psm = JdbcUtil.getPreparedStatement(conn, sql);
		psm.setInt(1, statusDaiShenPi);
		psm.setString(2, manageClasses);
		ResultSet set = psm.executeQuery();
		
		if (set.next()) {
			totalRecordes = set.getInt(1);
		}
		JdbcUtil.closeConnectionStateMent(conn, psm);
		return totalRecordes;
	}
	public List<LeaveApplicationView> getBanzhurenShenPiPage(int statusDaiShenPi, String manageClasses, Page page) throws SQLException {
		List<LeaveApplicationView> applications = new ArrayList<LeaveApplicationView>();
		Connection conn = JdbcUtil.getConnect();
		StringBuilder builder = new StringBuilder("select * from leave_application_view WHERE status = ? AND classId in (?) ");
		String sql = "";
		builder.append(" ORDER BY id DESC  ");
		builder.append(" limit " + ((page.getPageNo()-1)*page.getPageSize()+ "," +page.getPageSize()));
		sql = builder.toString();
		PreparedStatement psm = JdbcUtil.getPreparedStatement(conn, sql);
		psm.setInt(1, statusDaiShenPi);
		psm.setString(2, manageClasses);
		ResultSet set = psm.executeQuery();
		LeaveApplicationView apllicaton;
		while (set.next()) {
			getFromSet(applications, set);
		}
		JdbcUtil.closeConnectionStateMent(conn, psm);
		return applications;
	}
	public int getYuanShenPiTotalRocords(int statusBanzhrenPass, int collegeId) throws SQLException {
		int totalRecordes = 0;
		Connection conn = JdbcUtil.getConnect();
		String sql = "select count(*) as count from leave_application_view WHERE status = ? AND belongToCollegeId = ? ";
		PreparedStatement psm = JdbcUtil.getPreparedStatement(conn, sql);
		psm.setInt(1, statusBanzhrenPass);
		psm.setInt(2, collegeId);
		ResultSet set = psm.executeQuery();
		
		if (set.next()) {
			totalRecordes = set.getInt(1);
		}
		JdbcUtil.closeConnectionStateMent(conn, psm);
		return totalRecordes;
	}
	public List<LeaveApplicationView> getYuanShenPiPage(int statusBanzhrenPass, int collegeId, Page page) throws SQLException {
		List<LeaveApplicationView> applications = new ArrayList<LeaveApplicationView>();
		Connection conn = JdbcUtil.getConnect();
		StringBuilder builder = new StringBuilder("select * from leave_application_view WHERE status = ? AND belongToCollegeId = ? ");
		String sql = "";
		builder.append(" ORDER BY id DESC  ");
		builder.append(" limit " + ((page.getPageNo()-1)*page.getPageSize()+ "," +page.getPageSize()));
		sql = builder.toString();
		PreparedStatement psm = JdbcUtil.getPreparedStatement(conn, sql);
		psm.setInt(1, statusBanzhrenPass);
		psm.setInt(2, collegeId);
		ResultSet set = psm.executeQuery();
		LeaveApplicationView apllicaton;
		while (set.next()) {
			getFromSet(applications, set);
		}
		JdbcUtil.closeConnectionStateMent(conn, psm);
		return applications;
	}
	public boolean shenpi(LeaveApplication application, LeaveApplicationShenPi shenPi) throws SQLException {
		boolean result = true;
		Connection conn = JdbcUtil.getConnect();
		conn.setAutoCommit(false);
		String sql = "UPDATE leave_application SET status=? WHERE id=?";
		PreparedStatement psm = JdbcUtil.getPreparedStatement(conn, sql);	
		psm.setInt(1, application.getStatus());
		psm.setInt(2, application.getId());
		int count = psm.executeUpdate();
		if (count<1) {
			conn.rollback();
			result = false;
		}
		if (result) {
			//添加审批记录
			sql = "INSERT INTO leave_application_shen_pi(applicationId,teacherId,result,opinion) VALUES (?,?,?,?)";
			psm = JdbcUtil.getPreparedStatement(conn, sql);
			psm.setInt(1, shenPi.getApplicationId());
			psm.setInt(2, shenPi.getTeacherId());
			psm.setInt(3, shenPi.getResult());
			psm.setString(4, shenPi.getOpinion());
			
			count = psm.executeUpdate();
			if (count<1) {
				conn.rollback();
				result = false;
			}else{
				conn.commit();
			}
		}
		JdbcUtil.closeConnectionStateMent(conn, psm);
		return result;
	}

	/**
	 * 销假：只有“审批通过”的申请才能置为“结束”。
	 */
	public boolean over(int id) throws SQLException {
		Connection conn = JdbcUtil.getConnect();
		String sql = "UPDATE leave_application SET status=? WHERE id=? AND status=?";
		PreparedStatement psm = JdbcUtil.getPreparedStatement(conn, sql);
		psm.setInt(1, LeaveApplication.STATUS_OVER);
		psm.setInt(2, id);
		psm.setInt(3, LeaveApplication.STATUS_PASS);
		int count = psm.executeUpdate();
		JdbcUtil.closeConnectionStateMent(conn, psm);
		return count == 1;
	}
	
	
}
