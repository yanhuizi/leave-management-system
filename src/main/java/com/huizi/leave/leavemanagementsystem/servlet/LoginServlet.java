package com.huizi.leave.leavemanagementsystem.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.huizi.leave.leavemanagementsystem.domain.Clazz;
import com.huizi.leave.leavemanagementsystem.domain.Grade;
import com.huizi.leave.leavemanagementsystem.domain.Role;
import com.huizi.leave.leavemanagementsystem.domain.Student;
import com.huizi.leave.leavemanagementsystem.domain.Teacher;
import com.huizi.leave.leavemanagementsystem.service.ClassService;
import com.huizi.leave.leavemanagementsystem.service.PermissionService;
import com.huizi.leave.leavemanagementsystem.service.RoleService;
import com.huizi.leave.leavemanagementsystem.service.StudentService;
import com.huizi.leave.leavemanagementsystem.service.TeacherService;
import com.huizi.leave.leavemanagementsystem.session.QingjiaSesssion;
import com.huizi.leave.leavemanagementsystem.util.MessageUtil;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final int  LOGIN_IN = 1;   
	private static final int  LOGIN_OUT = 2;  
	private static final int  LOGIN_SUCCESS = 3;  
	private static final int  LOGIN_USERAME_ERROR = 4;  
	private static final int  LOGIN_PASSWORD_ERROR = 5;
	private static final int  LOGIN_OTHRE_ERROR = 6;
	private static final int  ROLE_BANZHUREN = 2;
	private static final int  ROLE_FUDAOYUAN = 3;
	private static final int  ROLE_LINGDAO   = 4;
	private static final int  UPDATE_PASSWORD   = 3;
	private static final int  UPDATE_DO_PASSWORD   = 4;
	
	
	private static final String  ADDMINISTRTOR = "administrator";   
	
    
	/**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int option = Integer.parseInt(request.getParameter("option"));
		switch (option) {
		case LOGIN_IN:
		{
			try {
				loginIn(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		}
		case LOGIN_OUT:{
			request.getSession().invalidate();
			request.getRequestDispatcher("login.jsp").forward(request, response);
			break;
		}
		case UPDATE_PASSWORD:
		{
			updatePassword(request, response);
			break;
		}
		case UPDATE_DO_PASSWORD:
		{
			try {
				doUpdatePassword(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		}
		default:
			break;
		}
	}
	

	private void loginIn(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException, ServletException
	{
		String num = request.getParameter("num");
		String password = request.getParameter("password");
		int result;
		if (num.trim().length() == 7) {
			//教师
			result = teacherLogin(num,password,request,response);
		}else{
			//学生
			result = studentLogin(num,password, request,response);
		}
		if (result != LOGIN_SUCCESS) {
			request.getRequestDispatcher("public/message.jsp").forward(request, response);
		}else{
			response.sendRedirect("public/main.html");
		}
		
	}
	
	private int teacherLogin(String num,String password,HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
		//1、查询教师
		Teacher teacher = TeacherService.getInstance().getByNum(num);
		if (teacher==null) {
			request.setAttribute("title", "登录失败");
			request.setAttribute("message", "用户名错误");
			request.setAttribute("url", "login.jsp");
			return LOGIN_USERAME_ERROR;
		}
		
		//2、比较密码
		if (!password.equals(teacher.getPassword())) {
			request.setAttribute("title", "登录失败");
			request.setAttribute("message", "密码错误");
			request.setAttribute("url", "login.jsp");
			return LOGIN_PASSWORD_ERROR;
		}
		
		//3、设置session的用户类型与教师
		QingjiaSesssion qSession = new QingjiaSesssion();
		qSession.setCollegeId(teacher.getCollegeId());
		qSession.setUserType(QingjiaSesssion.USER_TYPE_TEACHER);
		qSession.setTeacher(teacher);
		
		//4、查询角色
		List<Role> roles = RoleService.getInstance().getRoleListByUserID(teacher.getId());
		qSession.setRoles(roles);
		for (Role role : roles) {
			if(qSession.getDataArea()<role.getEareType())
			{
				qSession.setDataArea(role.getEareType());
			}
			if (role.getId() == ROLE_BANZHUREN) {
				qSession.setBanzhuren(true);
				//5、如具有班主任角色，查找器所管理的班级
				List<Clazz> clazzs = ClassService.getInstance().getByBanzhurenId(teacher.getId());
				qSession.setClazzes(clazzs);
				StringBuilder strClasses = new StringBuilder();
				StringBuilder strGrades = new StringBuilder();
				for (Clazz clazz : clazzs) {
					strClasses.append(clazz.getId());
					strClasses.append(",");
					strGrades.append(clazz.getGradeId());
					strGrades.append(",");
				}
				if (strClasses.length()>0) {
					strClasses.replace(strClasses.length()-1, strClasses.length(), "");
				}
				if (strGrades.length()>0) {
					strGrades.replace(strGrades.length()-1, strGrades.length(), "");
				}
				qSession.setManageClasses(strClasses.toString());
				//班主任所管理的班级的年级
				List<Grade> grades = ClassService.getInstance().getGradesByIds(strGrades.toString());
				qSession.setGrades(grades);
			}else if (role.getId() == ROLE_FUDAOYUAN) {
				
				qSession.setFudaoyuan(true);
			}else if (role.getId() == ROLE_LINGDAO) {
				qSession.setXuyuanLingdao(true);
			}
		}
		//4、查找权限
		qSession.setPermissiones(PermissionService.getInstance().getPermissionByTeacherId(teacher.getId()));
		
		HttpSession session = request.getSession();
		session.setAttribute(QingjiaSesssion.QINGJIA_SESSION, qSession);
		return LOGIN_SUCCESS;
	}
	private int studentLogin(String num,String password,HttpServletRequest request, HttpServletResponse response) throws SQLException {
		Student student = StudentService.getInstance().getByNum(num);
		//1、判断用户是否存在
		if (student == null) {
			request.setAttribute("title", "登录失败");
			request.setAttribute("message", "用户名错误");
			request.setAttribute("url", "login.jsp");
			return LOGIN_USERAME_ERROR;
		}
		if (!password.equals(student.getPassword())) {
			request.setAttribute("title", "登录失败");
			request.setAttribute("message", "密码错误");
			request.setAttribute("url", "login.jsp");
			return LOGIN_PASSWORD_ERROR;
		}
		QingjiaSesssion qSession = new QingjiaSesssion();
		qSession.setUserType(QingjiaSesssion.USER_TYPE_STUDENT);
		qSession.setStudent(student);
		qSession.setClassId(student.getClassId());
		Clazz clazz = ClassService.getInstance().getById(student.getClassId());
		qSession.setCollegeId(clazz.getGradeId());
		qSession.setClazz(clazz);
		List<Clazz> clazzs = new ArrayList<Clazz>();
		clazzs.add(clazz);
		qSession.setClazzes(clazzs);
		List<Grade> grades = ClassService.getInstance().getGradesByIds(""+clazz.getId());
		qSession.setGrades(grades);
		qSession.setPermissiones(PermissionService.getInstance().getPermissionByStudentRoleId());
		
		HttpSession session = request.getSession();
		session.setAttribute("qSession", qSession);
		return LOGIN_SUCCESS;
	}
	
	private void updatePassword(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setAttribute("option", UPDATE_DO_PASSWORD);
		request.getRequestDispatcher("WEB-INF/jsp/sys/updatePassword.jsp").forward(request, response);
	}
	private void doUpdatePassword(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		// TODO Auto-generated method stub
		String old = request.getParameter("old");
		String newPassword = request.getParameter("new");
		String again = request.getParameter("again");
		HttpSession session = request.getSession();
		QingjiaSesssion qSesssion = (QingjiaSesssion) session.getAttribute(QingjiaSesssion.QINGJIA_SESSION);
		
		if (qSesssion.getUserType() == QingjiaSesssion.USER_TYPE_STUDENT) {
			//比较旧密码对不对
			Student student = qSesssion.getStudent();
			if (!student.getPassword().equals(old)) {
				MessageUtil.showMessage("修改密码", "旧密码不正确", "LoginServlet?option=3", request, response);
			}else{
				if (!newPassword.equals(again)) {
					MessageUtil.showMessage("修改密码", "新密码两次输入不同", "LoginServlet?option=3", request, response);				
				}else {
					if(StudentService.getInstance().updatePassword(student.getId(),newPassword))
					{
						request.getSession().invalidate();
						MessageUtil.showMessage("修改密码", "修改成功", "login.jsp", request, response);				
					}else{
						MessageUtil.showMessage("修改密码", "修改失败", "LoginServlet?option=3", request, response);				
					}
				}
			}
		}else{
			
		}
	}

	
}
