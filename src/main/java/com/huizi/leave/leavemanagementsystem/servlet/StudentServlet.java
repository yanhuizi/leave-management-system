package com.huizi.leave.leavemanagementsystem.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.text.html.HTML;


import com.google.gson.Gson;

import com.huizi.leave.leavemanagementsystem.domain.AjaxResult;
import com.huizi.leave.leavemanagementsystem.domain.Clazz;
import com.huizi.leave.leavemanagementsystem.domain.College;
import com.huizi.leave.leavemanagementsystem.domain.Grade;
import com.huizi.leave.leavemanagementsystem.domain.Student;
import com.huizi.leave.leavemanagementsystem.domain.StudentQueryCondition;
import com.huizi.leave.leavemanagementsystem.page.Page;
import com.huizi.leave.leavemanagementsystem.service.ClassService;
import com.huizi.leave.leavemanagementsystem.service.CollegeSevice;
import com.huizi.leave.leavemanagementsystem.service.StudentService;
import com.huizi.leave.leavemanagementsystem.session.QingjiaSesssion;
import com.huizi.leave.leavemanagementsystem.util.MessageUtil;

/**
 * Servlet implementation class StudentServlet
 */
@WebServlet("/StudentServlet")
public class StudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final int OPTION_QUERY         = 1;
	private static final int OPTION_ADD           = 2;
	private static final int OPTION_DO_ADD        = 3;
	private static final int OPTION_UPDATE        = 4;
	private static final int OPTION_DO_UPDATE     = 5;
	private static final int OPTION_DETIAL        = 8;
	private static final int OPTION_PASSWORD      = 9;  
	private static final int OPTION_QUERY_BY_NUM  = 13;  
	
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StudentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int option = Integer.parseInt(request.getParameter("option"));
		HttpSession session = request.getSession();
		QingjiaSesssion qingjiaSesssion = (QingjiaSesssion)session.getAttribute("qSession");
		switch (option) {
		case OPTION_QUERY:
		{
			if (!qingjiaSesssion.hasPermission("baseinfo.student.query")) {
				MessageUtil.showMessage("查询学生", "没有访问权限", "ClassServlet?option=1", request, response);
				return;
			}
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
			try {
				add(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		}
		case OPTION_DO_ADD:
		{
			try {
				doAdd(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				MessageUtil.showMessage("添加学生", "添加失败", "StudentServlet?option=1&pageNo=1", request, response);
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
		case OPTION_DETIAL:
		{
			try {
				detail(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		}
		case OPTION_PASSWORD:
		{
			try {
				ResetPassword(request,response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		}
		case OPTION_QUERY_BY_NUM:
		{
			try {
				queryByNum(request,response);
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

	

	

	private void query(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException, SQLException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		QingjiaSesssion qSesssion = (QingjiaSesssion) session.getAttribute(QingjiaSesssion.QINGJIA_SESSION);
		//获取查询条件
		StudentQueryCondition condition = new StudentQueryCondition();
		condition.parse(request);
		request.setAttribute("condition", condition);
		getCollegeAndGradeAndClass(request, qSesssion, condition);
		//获取分页信息
		Page page = (Page) request.getAttribute("page");
		//分页条件查询学生信息
		int totalRecordes = StudentService.getInstance().getTotalRecord(qSesssion,condition);
		page.setTotalRocords(totalRecordes);
		request.setAttribute("page", page);
		//分页查询学生信息
		List<Student> students = StudentService.getInstance().queryPage(qSesssion,condition,page);		
		request.setAttribute("students", students);
		String url = new String("WEB-INF/jsp/student/list.jsp");
		request.setAttribute("forwordUrl",url);
	}

	private void getCollegeAndGradeAndClass(HttpServletRequest request, QingjiaSesssion qSesssion,
			StudentQueryCondition condition) throws SQLException {
		//获取学院信息
		List<College> colleges = CollegeSevice.getInstance().getByRequest(request);		
		request.setAttribute("colleges", colleges);
		//获取年级信息
		List<Grade> grades;
		if (qSesssion.getDataArea() >= QingjiaSesssion.DATA_AREA_COLLEGE) {
			grades = ClassService.getInstance().getGrade();
		}else{
			grades = qSesssion.getGrades();
		}
		request.setAttribute("grades", grades);
		//获取班级信息
		List<Clazz> clazzs = new ArrayList<>();
		if (condition != null && (condition.getCollegeId()!=0||condition.getGradeId()!=0)) {
			clazzs = ClassService.getInstance().getByCollegeAndGrade(condition.getCollegeId(), condition.getGradeId());
		}else if (qSesssion.getDataArea()<QingjiaSesssion.DATA_AREA_COLLEGE) {
			clazzs = qSesssion.getClazzes();
		}
		request.setAttribute("clazzs", clazzs);
	}

	private void add(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException, SQLException {
		// TODO Auto-generated method stub
		request.setAttribute("option", OPTION_DO_ADD);
		HttpSession session = request.getSession();
		QingjiaSesssion qSesssion = (QingjiaSesssion) session.getAttribute(QingjiaSesssion.QINGJIA_SESSION);
		
		StudentQueryCondition condition = (StudentQueryCondition) session.getAttribute("addSelected");
		getCollegeAndGradeAndClass(request, qSesssion, condition);
		request.getRequestDispatcher("WEB-INF/jsp/student/add.jsp").forward(request, response);
	}

	private void doAdd(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException, SQLException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		QingjiaSesssion qSesssion = (QingjiaSesssion) session.getAttribute(QingjiaSesssion.QINGJIA_SESSION);
		//添加选择
		StudentQueryCondition condition = new StudentQueryCondition();
		condition.parse(request);
		session.setAttribute("addSelected", condition);
		
		//获取学生信息
		Student student = new Student();
		student.parse(request);
		student.setPassword(student.getNum());
		
		//保存到数据库
		AjaxResult result = null;
		if (StudentService.getInstance().add(student)) {
			result = AjaxResult.success("添加成功");
		}else{
			result = AjaxResult.success("添加失败");
		}
		Gson gson = new Gson();
		response.setContentType("text/plain;charset=utf-8");
		response.getWriter().write(gson.toJson(result));
	}

	private void update(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException, SQLException {
		// TODO Auto-generated method stub
		int id = Integer.parseInt(request.getParameter("id"));
		Student student = StudentService.getInstance().getByid(id);
		request.setAttribute("student", student);
		request.setAttribute("option", OPTION_DO_UPDATE);
		HttpSession session = request.getSession();
		QingjiaSesssion qSesssion = (QingjiaSesssion) session.getAttribute(QingjiaSesssion.QINGJIA_SESSION);
		
		StudentQueryCondition condition = new StudentQueryCondition();
		condition.setCollegeId(student.getBelongToCollegeId());
		condition.setGradeId(student.getGradeId());
		getCollegeAndGradeAndClass(request, qSesssion, condition);
		request.getRequestDispatcher("WEB-INF/jsp/student/update.jsp").forward(request, response);
	}

	private void doUpdate(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException, SQLException {
		// TODO Auto-generated method stub
		//获取学生信息
		Student student = new Student();
		student.parse(request);
				
		//保存到数据库
		AjaxResult result = null;
		if (StudentService.getInstance().update(student)) {
			result = AjaxResult.success("修改成功");
		}else{
			result = AjaxResult.success("修改失败");
		}
		Gson gson = new Gson();
		response.setContentType("text/plain;charset=utf-8");
		response.getWriter().write(gson.toJson(result));
	}

	private void detail(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException, SQLException {
		// TODO Auto-generated method stub
		int id = Integer.parseInt(request.getParameter("id"));
		Student student = StudentService.getInstance().getByid(id);
		request.setAttribute("student", student);
		request.getRequestDispatcher("WEB-INF/jsp/student/detail.jsp").forward(request, response);
	}

	private void ResetPassword(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException, SQLException {
		// TODO Auto-generated method stub
		int id = Integer.parseInt(request.getParameter("id"));
		if (StudentService.getInstance().resetPassword(id)) {
			MessageUtil.showMessage("重置密码", "成功", "StudentServlet?option=1&pageNo=1", request, response);
		}else{
			MessageUtil.showMessage("重置密码", "失败", "StudentServlet?option=1&pageNo=1", request, response);
		}
	}

	private void queryByNum(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException, SQLException  {
		// TODO Auto-generated method stub
		String num = request.getParameter("num");
		Student student = StudentService.getInstance().getByNum(num);
		AjaxResult result = null;
		if (student != null) {
			result = AjaxResult.success("学号为:"+num+"学生存在",student);

		}else{
			result = AjaxResult.failure("学号为:"+num+"学生不存在",student);
		}
		Gson gson = new Gson();
		response.setContentType("text/plain;charset=utf-8");
		response.getWriter().write(gson.toJson(result));
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
