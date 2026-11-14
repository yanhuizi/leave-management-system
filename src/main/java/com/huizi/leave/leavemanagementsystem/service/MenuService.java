package com.huizi.leave.leavemanagementsystem.service;

import java.sql.SQLException;
import java.util.List;

import com.huizi.leave.leavemanagementsystem.dao.MenuDao;
import com.huizi.leave.leavemanagementsystem.domain.Menu;

public class MenuService {
	
	private static MenuService instance;
	private MenuService(){};
	static{
		instance = new MenuService();
	}
	public static MenuService getInstance() {
		return instance;
	}
	
	public List<Menu> getAll() throws SQLException {
		return MenuDao.getInstance().getAll();
	}
}
