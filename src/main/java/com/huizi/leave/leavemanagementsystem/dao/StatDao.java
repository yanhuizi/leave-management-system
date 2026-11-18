package com.huizi.leave.leavemanagementsystem.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.huizi.leave.leavemanagementsystem.domain.StatOverview;
import com.huizi.leave.leavemanagementsystem.util.JdbcUtil;

public class StatDao {
	private static StatDao instance;
	private StatDao() {}
	static {
		instance = new StatDao();
	}
	public static StatDao getInstance() {
		return instance;
	}

	private int count(String sql, Object... params) throws SQLException {
		Connection conn = JdbcUtil.getConnect();
		PreparedStatement psm = JdbcUtil.getPreparedStatement(conn, sql);
		for (int i = 0; i < params.length; i++) {
			psm.setObject(i + 1, params[i]);
		}
		ResultSet set = psm.executeQuery();
		int count = 0;
		if (set.next()) {
			count = set.getInt(1);
		}
		JdbcUtil.closeConnectionStateMent(conn, psm);
		return count;
	}

	public StatOverview getOverview() throws SQLException {
		StatOverview overview = new StatOverview();
		overview.setCollegeCount(count("SELECT COUNT(*) FROM college"));
		overview.setClassCount(count("SELECT COUNT(*) FROM class"));
		overview.setStudentCount(count("SELECT COUNT(*) FROM student"));
		overview.setTeacherCount(count("SELECT COUNT(*) FROM teacher"));
		overview.setRoleCount(count("SELECT COUNT(*) FROM role"));
		overview.setPendingCount(count("SELECT COUNT(*) FROM leave_application WHERE status = ?", 1));
		overview.setPassCount(count("SELECT COUNT(*) FROM leave_application WHERE status = ?", 4));
		overview.setOverCount(count("SELECT COUNT(*) FROM leave_application WHERE status = ?", 6));
		return overview;
	}
}
