package com.huizi.leave.leavemanagementsystem.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.huizi.leave.leavemanagementsystem.domain.College;
import com.huizi.leave.leavemanagementsystem.domain.Role;
import com.huizi.leave.leavemanagementsystem.domain.Teacher;
import com.huizi.leave.leavemanagementsystem.page.Page;
import com.huizi.leave.leavemanagementsystem.service.CollegeSevice;
import com.huizi.leave.leavemanagementsystem.service.RoleService;
import com.huizi.leave.leavemanagementsystem.service.TeacherService;
import com.huizi.leave.leavemanagementsystem.session.QingjiaSesssion;
import com.huizi.leave.leavemanagementsystem.util.MessageUtil;

/**
 * Servlet implementation class TeacherServlet
 */
@WebServlet("/TeacherServlet")
public class TeacherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final int OPTION_QUERY         = 1;
	private static final int OPTION_ADD           = 2;
	private static final int OPTION_DO_ADD        = 3;
	private static final int OPTION_UPDATE        = 4;
	private static final int OPTION_DO_UPDATE     = 5;
	private static final int OPTION_ROLE          = 6;
	private static final int OPTION_DO_ROLE       = 7;
	private static final int OPTION_DETIAL        = 8;
	private static final int OPTION_PASSWORD      = 9;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TeacherServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int option = Integer.parseInt(request.getParameter("option"));
		HttpSession session = request.getSession();
		QingjiaSesssion qingjiaSesssion = (QingjiaSesssion)session.getAttribute("qSession");
		switch (option) {
		case OPTION_QUERY:
		{
			if (!qingjiaSesssion.hasPermission("baseinfo.teacher.qurey")) {
				MessageUtil.showMessage("查询班级", "没有访问权限", "TeacherServlet?option=1", request, response);
				return;
			}
			try {
				query(request, response);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			break;
		}
		case OPTION_ADD:
		{
			try {
				add(request, response);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			break;
		}
		case OPTION_DO_ADD:
		{
			try {
				doAdd(request, response);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			break;
		}
		case OPTION_UPDATE:
		{
			try {
				update(request, response);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			break;
		}
		case OPTION_DO_UPDATE:
		{
			try {
				doUpdate(request, response);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			break;
		}
		case OPTION_DETIAL:
		{
			try {
				detail(request, response);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			break;
		}
		case OPTION_ROLE:
		{
			try {
				role(request,response);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			break;
		}
		case OPTION_DO_ROLE:
		{
			try {
				doRole(request,response);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			break;
		}
		case OPTION_PASSWORD:
		{
			try {
				ResetPassword(request,response);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			break;
		}
		default:
			break;
		}
	}


	private void query(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException, SQLException {
		//获取查询条件
		Teacher teacher = new Teacher();
		teacher.parse(request);
		//获取学院
		List<College> colleges = CollegeSevice.getInstance().getAll();		
		request.setAttribute("colleges",colleges);
		//将查询条件保存的request
		request.setAttribute("teacher", teacher);
		//获取page
		Page page = (Page) request.getAttribute("page");
		List<Teacher> teachers;
		if (null != page) {//分页查询
			//获取总的记录数
			int totalRecords = TeacherService.getInstance().getTotalRecordes(teacher);
			page.setTotalRocords(totalRecords);
			request.setAttribute("page", page);
			//分页查询，将结果保存到request域
			teachers = TeacherService.getInstance().getAll(teacher,page);
			request.setAttribute("teachers", teachers);
			//在request中设置forwordUrl属性
			String url = new String("WEB-INF/jsp/teacher/list.jsp");
			request.setAttribute("forwordUrl",url);	
		}else{//不分页
			//查询
		}
	}

	private void add(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		request.setAttribute("option", OPTION_DO_ADD);
		List<College> colleges = CollegeSevice.getInstance().getAll();		
		request.setAttribute("colleges",colleges);
		request.getRequestDispatcher("WEB-INF/jsp/teacher/add.jsp").forward(request, response);		
	}
	
	private void doAdd(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException, SQLException {
		Teacher teacher = new Teacher();
		teacher.parse(request);
		teacher.setPassword(teacher.getNum());
		if (TeacherService.getInstance().add(teacher)) {
			MessageUtil.showMessage("添加教师", "添加成功",  "TeacherServlet?option=1&pageNo=1", request, response);
		}else{
			MessageUtil.showMessage("添加教师", "添加失败",  "TeacherServlet?option=1&pageNo=1", request, response);			
		}
	}
	
	private void update(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException, SQLException {
		//查询要修改的教师信息
		int id = Integer.parseInt(request.getParameter("id"));
		Teacher teacher = TeacherService.getInstance().getById(id);
		request.setAttribute("teacher", teacher);
		//设置操作
		request.setAttribute("option", OPTION_DO_UPDATE);
		//查询学院
		List<College> colleges = CollegeSevice.getInstance().getAll();		
		request.setAttribute("colleges",colleges);
		request.getRequestDispatcher("WEB-INF/jsp/teacher/update.jsp").forward(request, response);		
	}

	private void doUpdate(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException, SQLException {
		Teacher teacher = new Teacher();
		teacher.parse(request);
		if (TeacherService.getInstance().update(teacher)) {
			MessageUtil.showMessage("修改教师", "修改成功",  "TeacherServlet?option=1&pageNo=1", request, response);						
		}else{
			MessageUtil.showMessage("修改教师", "修改失败",  "TeacherServlet?option=1&pageNo=1", request, response);						
		}
	}
	private void detail(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException, SQLException {
		int id = Integer.parseInt(request.getParameter("id"));
		Teacher teacher = TeacherService.getInstance().getById(id);
		request.setAttribute("teacher", teacher);
		request.getRequestDispatcher("WEB-INF/jsp/teacher/detail.jsp").forward(request, response);		
	}

	private void role(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException, SQLException {
		int id = Integer.parseInt(request.getParameter("id"));
		request.setAttribute("id", id);
		List<Role> roles = RoleService.getInstance().getAll();
		request.setAttribute("roles", roles);
		request.setAttribute("option", OPTION_DO_ROLE);
		List<Role> userRoles = RoleService.getInstance().getRoleListByUserID(id);
		request.setAttribute("userRoles", userRoles);
		request.getRequestDispatcher("WEB-INF/jsp/teacher/role.jsp").forward(request, response);		
	}

	private void doRole(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException, SQLException {
		int id = Integer.parseInt(request.getParameter("id"));
		String[] permissiones = request.getParameterValues("pemission");
		if (TeacherService.getInstance().updateRoles(id,permissiones)) {
			MessageUtil.showMessage("修改教师角色", "修改成功",  "TeacherServlet?option=1&pageNo=1", request, response);						
		}else{
			MessageUtil.showMessage("修改教师角色", "修改失败",  "TeacherServlet?option=1&pageNo=1", request, response);						
		}
	}
	
	private void ResetPassword(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		if (TeacherService.getInstance().ResetPassword(id)) {
			MessageUtil.showMessage("重置教师密码", "修改成功",  "TeacherServlet?option=1&pageNo=1", request, response);						
		}else{
			MessageUtil.showMessage("重置教师密码", "修改失败",  "TeacherServlet?option=1&pageNo=1", request, response);						
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
