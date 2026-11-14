package com.huizi.leave.leavemanagementsystem.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.huizi.leave.leavemanagementsystem.domain.Menu;
import com.huizi.leave.leavemanagementsystem.domain.Role;
import com.huizi.leave.leavemanagementsystem.service.MenuService;
import com.huizi.leave.leavemanagementsystem.service.RoleService;

/**
 * Servlet implementation class RoleServlet
 */
@WebServlet("/RoleServlet")
public class RoleServlet extends HttpServlet {
	private static final long serialVersionUID    = 1L;
	private static final int OPTION_QUERY         = 1;
	private static final int OPTION_ADD           = 2;
	private static final int OPTION_DO_ADD        = 3;
	private static final int OPTION_UPDATE        = 4;
	private static final int OPTION_DO_UPDATE     = 5;
	private static final int OPTION_DELETE        = 6;
	private static final int OPTION_AUTHORIZE     = 7;
	private static final int OPTION_DO_AUTHORIZE  = 8;
	private static final int OPTION_DETIAL        = 9;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RoleServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int option = Integer.parseInt(request.getParameter("option"));
		switch (option) {
		case OPTION_QUERY:
		{
			try {
				query(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		}
		case OPTION_ADD:
		{
			add(request, response);
			break;
		}
		case OPTION_DO_ADD:
		{
			try {
				doAdd(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		}
		case OPTION_UPDATE:
		{
			try {
				update(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		}
		case OPTION_DO_UPDATE:
		{
			try {
				doUpdate(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		}
		case OPTION_DELETE:
		{
			try {
				delete(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		}
		case OPTION_AUTHORIZE:
		{
			try {
				authorize(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		}
		case OPTION_DO_AUTHORIZE:
		{
			try {
				doAuthroize(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		}
		case OPTION_DETIAL:
		{
			detail(request, response);
			break;
		}
		
		default:
			break;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	private void query(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		List<Role> roles = RoleService.getInstance().getAll();
		request.setAttribute("roles", roles);
		request.getRequestDispatcher("WEB-INF/jsp/role/list.jsp").forward(request, response);
	
	}
	private void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("option", OPTION_DO_ADD);
		request.getRequestDispatcher("WEB-INF/jsp/role/add.jsp").forward(request, response);		
	}
	private void doAdd(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		Role role = new Role();
		role.parse(request);
		if(RoleService.getInstance().add(role))
		{
			request.setAttribute("title", "添加角色");
			request.setAttribute("message", "添加成功");
			request.setAttribute("url", "RoleServlet?option=1");
			request.getRequestDispatcher("public/message.jsp").forward(request, response);
	
		}else{
			request.setAttribute("title", "添加角色");
			request.setAttribute("message", "添加失败");
			request.setAttribute("url", "RoleServlet?option=1");
			request.getRequestDispatcher("public/message.jsp").forward(request, response);
		}
	}
	private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		//查询
		int id = Integer.parseInt(request.getParameter("id"));
		Role role = RoleService.getInstance().getById(id);
		request.setAttribute("role", role);
		request.setAttribute("option", OPTION_DO_UPDATE);
		request.getRequestDispatcher("WEB-INF/jsp/role/update.jsp").forward(request, response);		
	}
	private void doUpdate(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		Role role = new Role();
		role.parse(request);
		if(RoleService.getInstance().update(role))
		{
			request.setAttribute("title", "修改角色");
			request.setAttribute("message", "修改成功");
			request.setAttribute("url", "RoleServlet?option=1");
			request.getRequestDispatcher("public/message.jsp").forward(request, response);
	
		}else{
			request.setAttribute("title", "修改角色");
			request.setAttribute("message", "修改失败");
			request.setAttribute("url", "RoleServlet?option=1");
			request.getRequestDispatcher("public/message.jsp").forward(request, response);
		}
	}
	private void delete(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		if(RoleService.getInstance().deleteById(id))
		{
			request.setAttribute("title", "删除角色");
			request.setAttribute("message", "删除成功");
			request.setAttribute("url", "RoleServlet?option=1");
			request.getRequestDispatcher("public/message.jsp").forward(request, response);
	
		}else{
			request.setAttribute("title", "删除角色");
			request.setAttribute("message", "删除失败");
			request.setAttribute("url", "RoleServlet?option=1");
			request.getRequestDispatcher("public/message.jsp").forward(request, response);
		}
	}
	private void authorize(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		//得到要修改的角色的id
		int id = Integer.parseInt(request.getParameter("id"));
		List<Menu> menus = MenuService.getInstance().getAll();
		//得到要修改的角色
		Role role = RoleService.getInstance().getById(id);
		request.setAttribute("role", role);
		request.setAttribute("menus", menus);
		request.setAttribute("option", OPTION_DO_AUTHORIZE);
		request.getRequestDispatcher("WEB-INF/jsp/role/authorize.jsp").forward(request, response);
	}
	private void doAuthroize(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		int roleId = Integer.parseInt(request.getParameter("id"));
		String[] permissiones = request.getParameterValues("pemission");
		if(RoleService.getInstance().updateRolePermissionById(roleId, permissiones)){
			request.setAttribute("title", "修改角色权限");
			request.setAttribute("message", "修改成功");
			request.setAttribute("url", "RoleServlet?option=1");
			request.getRequestDispatcher("public/message.jsp").forward(request, response);
		}else{
			request.setAttribute("title", "修改角色权限");
			request.setAttribute("message", "修改失败");
			request.setAttribute("url", "RoleServlet?option=1");
			request.getRequestDispatcher("public/message.jsp").forward(request, response);
		
		}
	}
	private void detail(HttpServletRequest request, HttpServletResponse response) {
		
	}
}
