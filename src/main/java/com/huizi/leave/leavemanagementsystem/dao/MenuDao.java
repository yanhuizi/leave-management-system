package com.huizi.leave.leavemanagementsystem.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.huizi.leave.leavemanagementsystem.domain.Menu;
import com.huizi.leave.leavemanagementsystem.util.JdbcUtil;

public class MenuDao {
	public static final int MODULE_MENU_PARENT = 0;
	private static MenuDao instance;
	private MenuDao(){};
	static{
		instance = new MenuDao();
	}
	public static MenuDao getInstance() {
		return instance;
	}
	
	public List<Menu> getAll() throws SQLException {
		List<Menu> allMenu;
		//1、查询模块
		allMenu = getByPid(MODULE_MENU_PARENT);
		//2、查询菜单项
		List<Menu> menuItems;
		List<Menu> menuButtons;
		
		for (Menu menu : allMenu) {
			menuItems = getByPid(menu.getId());
			menu.setSubMenus(menuItems);
			for (Menu item : menuItems) {
				menuButtons = getByPid(item.getId());
				item.setSubMenus(menuButtons);
			}
		}
		return allMenu;
	}
	public List<Menu> getByPid(int pid) throws SQLException{
		List<Menu> menus = new ArrayList<Menu>();
		Connection conn = JdbcUtil.getConnect();
		
		String sql = "SELECT * FROM menu WHERE pid = ?";
		PreparedStatement psm = JdbcUtil.getPreparedStatement(conn, sql);
		psm.setInt(1, pid);
		ResultSet set = psm.executeQuery();
		Menu menu;
		while(set.next()){
			menu = new Menu();
			menu.setId(set.getInt(1));
			menu.setName(set.getString(2));
			menu.setPermission(set.getString(3));
			menu.setPid(set.getInt(4));
			menu.setPids(set.getString(5));
			menu.setUrl(set.getString(6));
			menus.add(menu);
		}
		JdbcUtil.closeConnectionStateMent(conn, psm);
		return menus;
	}
}
