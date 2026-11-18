package com.huizi.leave.leavemanagementsystem.service;

import java.sql.SQLException;

import com.huizi.leave.leavemanagementsystem.dao.StatDao;
import com.huizi.leave.leavemanagementsystem.domain.StatOverview;

public class StatService {
	private static StatService instance;
	private StatService() {}
	static {
		instance = new StatService();
	}
	public static StatService getInstance() {
		return instance;
	}
	public StatOverview getOverview() throws SQLException {
		return StatDao.getInstance().getOverview();
	}
}
