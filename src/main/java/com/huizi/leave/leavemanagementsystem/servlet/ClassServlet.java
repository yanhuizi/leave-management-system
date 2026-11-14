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

import com.google.gson.Gson;

import com.huizi.leave.leavemanagementsystem.domain.Clazz;
import com.huizi.leave.leavemanagementsystem.domain.College;
import com.huizi.leave.leavemanagementsystem.domain.Grade;
import com.huizi.leave.leavemanagementsystem.domain.Student;
import com.huizi.leave.leavemanagementsystem.domain.Teacher;
import com.huizi.leave.leavemanagementsystem.page.Page;
import com.huizi.leave.leavemanagementsystem.service.ClassService;
import com.huizi.leave.leavemanagementsystem.service.CollegeSevice;
import com.huizi.leave.leavemanagementsystem.service.TeacherService;
import com.huizi.leave.leavemanagementsystem.session.QingjiaSesssion;
import com.huizi.leave.leavemanagementsystem.util.MessageUtil;


/**
 * Servlet implementation class RoleServlet
 */
@WebServlet("/ClassServlet")
public class ClassServlet extends HttpServlet {
	private static final long serialVersionUID    = 1L;
	private static final int OPTION_QUERY         = 1;
	private static final int OPTION_ADD           = 2;
	private static final int OPTION_DO_ADD        = 3;
	private static final int OPTION_UPDATE        = 4;
	private static final int OPTION_DO_UPDATE     = 5;
	
	private static final int OPTION_DETIAL        = 9;
	private static final int OPTION_BANZHUREN     = 10;
	private static final int OPTION_QUERY_BANZHUREN  = 11;
	private static final int OPTION_DO_BANZHUREN     = 12; 
	private static final int OPTION_AJAX_CLASSES     = 13; 
	
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ClassServlet() {
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
			if (!qingjiaSesssion.hasPermission("baseinfo.class.query")) {
				MessageUtil.showMessage("查询班级", "没有访问权限", "ClassServlet?option=1", request, response);
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
		case OPTION_BANZHUREN:
		{
			banzhuren(request,response);
			break;
		}
		case OPTION_QUERY_BANZHUREN:
		{
			try {
				queryBanzhuren(request,response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		}
		case OPTION_DO_BANZHUREN:{
			try {
				doBanzhuren(request,response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		}
		case OPTION_AJAX_CLASSES:{
			try {
				ajaxClasses(request,response);
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
	//ajax获取班级
	private void ajaxClasses(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		// TODO Auto-generated method stub
		String college = request.getParameter("college");
		String grade   = request.getParameter("grade");
		int collegeId = 0;
		int gradeId   = 0;
		if (college != null) {
			collegeId = Integer.parseInt(college);
		}
		if (grade != null) {
			gradeId = Integer.parseInt(grade);
			
		}
		List<Clazz> clazzs = null;
		HttpSession session = request.getSession(false);
		QingjiaSesssion qSesssion = (QingjiaSesssion) session.getAttribute(QingjiaSesssion.QINGJIA_SESSION);
		if (qSesssion.getDataArea() <= QingjiaSesssion.DATA_AREA_CLASS) {
			clazzs = qSesssion.getClazzes();
		}else {
			clazzs = ClassService.getInstance().getByCollegeAndGrade(collegeId,gradeId);			
		}
		Gson gson = new Gson();
		String strClasses = gson.toJson(clazzs);
		response.setContentType("text/plain;charset=utf-8");
		response.getWriter().write(strClasses);
	}

	private void queryBanzhuren(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		// TODO Auto-generated method stub
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("teacher");
		List<Teacher> teachers = TeacherService.getInstance().getByName(name);
		request.setAttribute("id", id);
		request.setAttribute("teachers", teachers);
		request.setAttribute("name", name);
		request.getRequestDispatcher("WEB-INF/jsp/class/banzhuren.jsp").forward(request, response);
	}

	private void banzhuren(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int id = Integer.parseInt(request.getParameter("id"));
		request.setAttribute("id", id);
		request.setAttribute("name", "");
		request.getRequestDispatcher("WEB-INF/jsp/class/banzhuren.jsp").forward(request, response);
	}
	
	private void doBanzhuren(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		// TODO Auto-generated method stub
		int classid = Integer.parseInt(request.getParameter("classid"));
		int teacherid = Integer.parseInt(request.getParameter("teacherid"));
		if(ClassService.getInstance().setClassBanzhuren(classid,teacherid))
		{
			request.setAttribute("title", "修改班主任");
			request.setAttribute("message", "修改成功");
			request.setAttribute("url", "ClassServlet?option=1");
			request.getRequestDispatcher("public/message.jsp").forward(request, response);
		}else{
			request.setAttribute("title", "修改班主任");
			request.setAttribute("message", "修改失败");
			request.setAttribute("url", "ClassServlet?option=1");
			request.getRequestDispatcher("public/message.jsp").forward(request, response);
		
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
		String whereClause = getWhereClause(request);
		Clazz clazz = new Clazz();
		clazz.parse(request);
		List<Clazz> classes = null;
		
		List<Grade> grades = ClassService.getInstance().getGrade();		
		request.setAttribute("grades", grades);
		List<College> colleges = CollegeSevice.getInstance().getAll();		
		request.setAttribute("colleges",colleges);
		//保存查询条件
		request.setAttribute("classes", classes);
		request.setAttribute("clazz", clazz);
		//获取page
		Page page = (Page) request.getAttribute("page");
		//判断page ！=null
		if (page != null) {
			//获取总的页数
			int totalRecords = ClassService.getInstance().getTotalRecord(clazz,whereClause);
			page.setTotalRocords(totalRecords);
			classes = ClassService.getInstance().getAll(clazz,whereClause,page);
			request.setAttribute("page", page);
			request.setAttribute("classes", classes);
			String url = new String("WEB-INF/jsp/class/list.jsp");
			request.setAttribute("forwordUrl",url);	
			//分页查询数据
		}else{
			classes = ClassService.getInstance().getAll(clazz,whereClause,null);
			request.setAttribute("classes", classes);
			request.getRequestDispatcher("WEB-INF/jsp/class/list.jsp").forward(request, response);
		}
	}
	private String getWhereClause(HttpServletRequest request) {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		QingjiaSesssion qSesssion = (QingjiaSesssion) session.getAttribute("qSession");
		int dataArea = qSesssion.getDataArea();
		StringBuffer whereClause =new StringBuffer();
		switch (dataArea) {
		case QingjiaSesssion.DATA_AREA_PERSON:
		{
			//1、判断是否是学生
			if (qSesssion.getUserType() == QingjiaSesssion.USER_TYPE_STUDENT) {
				Student student = qSesssion.getStudent();
				whereClause.append("id = " + student.getClassId());
			}else if(qSesssion.getUserType() == QingjiaSesssion.USER_TYPE_TEACHER) {
				//2、判断是否是老师
				whereClause.append("belongToCollegeId = " + qSesssion.getCollegeId());
			}
			break;
		}
		case QingjiaSesssion.DATA_AREA_CLASS:
		{
			whereClause.append("id in ("+qSesssion.getManageClasses() + ")");
			break;
		}
		case QingjiaSesssion.DATA_AREA_COLLEGE:
		{
			whereClause.append("belongToCollegeId = " + qSesssion.getCollegeId());
			break;
		}
		case QingjiaSesssion.DATA_AREA_SYSTEM:
		{
			return null;
		}
		default:
			break;
		}
		return whereClause.toString();
	}

	private void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		request.setAttribute("option", OPTION_DO_ADD);
		List<Grade> grades = ClassService.getInstance().getGrade();		
		request.setAttribute("grades", grades);
		List<College> colleges = CollegeSevice.getInstance().getAll();		
		request.setAttribute("colleges",colleges);
		request.getRequestDispatcher("WEB-INF/jsp/class/add.jsp").forward(request, response);		
	}
	private void doAdd(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		
		Clazz clazz = new Clazz();
		clazz.parse(request);
		if(ClassService.getInstance().add(clazz))
		{
			request.setAttribute("title", "添加班级");
			request.setAttribute("message", "添加成功");
			request.setAttribute("url", "ClassServlet?option=1");
			request.getRequestDispatcher("public/message.jsp").forward(request, response);
	
		}else{
			request.setAttribute("title", "添加班级");
			request.setAttribute("message", "添加失败");
			request.setAttribute("url", "ClassServlet?option=1");
			request.getRequestDispatcher("public/message.jsp").forward(request, response);
		}
		
	}
	private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		//查询
		int id = Integer.parseInt(request.getParameter("id"));
		Clazz clazz = ClassService.getInstance().getById(id);
		request.setAttribute("clazz", clazz);
		request.setAttribute("option", OPTION_DO_UPDATE);
		List<Grade> grades = ClassService.getInstance().getGrade();		
		request.setAttribute("grades", grades);
		List<College> colleges = CollegeSevice.getInstance().getAll();		
		request.setAttribute("colleges",colleges);
		request.getRequestDispatcher("WEB-INF/jsp/class/update.jsp").forward(request, response);		
	}
	private void doUpdate(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		Clazz clazz = new Clazz();
		clazz.parse(request);
		if(ClassService.getInstance().update(clazz))
		{
			request.setAttribute("title", "修改班级");
			request.setAttribute("message", "修改成功");
			request.setAttribute("url", "ClassServlet?option=1");
			request.getRequestDispatcher("public/message.jsp").forward(request, response);
	
		}else{
			request.setAttribute("title", "修改班级");
			request.setAttribute("message", "修改失败");
			request.setAttribute("url", "ClassServlet?option=1");
			request.getRequestDispatcher("public/message.jsp").forward(request, response);
		}
	}
	
	private void detail(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Clazz clazz = ClassService.getInstance().getDetailById(id);
		request.setAttribute("clazz", clazz);
		request.getRequestDispatcher("WEB-INF/jsp/class/detail.jsp").forward(request, response);
	}
}
