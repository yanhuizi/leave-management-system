package com.huizi.leave.leavemanagementsystem.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.huizi.leave.leavemanagementsystem.domain.LeaveApplication;
import com.huizi.leave.leavemanagementsystem.domain.LeaveApplicationShenPi;
import com.huizi.leave.leavemanagementsystem.domain.LeaveApplicationShenPiView;
import com.huizi.leave.leavemanagementsystem.domain.LeaveApplicationView;
import com.huizi.leave.leavemanagementsystem.domain.Student;
import com.huizi.leave.leavemanagementsystem.domain.Teacher;
import com.huizi.leave.leavemanagementsystem.page.Page;
import com.huizi.leave.leavemanagementsystem.service.LeaveApplicationService;
import com.huizi.leave.leavemanagementsystem.session.QingjiaSesssion;
import com.huizi.leave.leavemanagementsystem.util.DateUtil;
import com.huizi.leave.leavemanagementsystem.util.MessageUtil;

/**
 * Servlet implementation class LeaveApplicationServlet
 */
@WebServlet("/LeaveApplicationServlet")
public class LeaveApplicationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final int OPTION_QUERY         = 1;
	private static final int OPTION_ADD           = 2;
	private static final int OPTION_DO_ADD        = 3;
	private static final int OPTION_UPDATE        = 4;
	private static final int OPTION_DO_UPDATE     = 5;
	//销假
	private static final int OPTION_OVER          = 6;
	private static final int OPTION_DO_OVER       = 7;
	private static final int OPTION_DETIAL        = 8;
	private static final int OPTION_SHENPI_LIST   = 9;
	private static final int OPTION_SHENPI        = 10;
	private static final int OPTION_DO_SHENPI     = 11;
    
	//查询请假申请类型
	private static final int QUERY_TYPE_PROCESSING = 1; //处理中 
	private static final int QUERY_TYPE_PASS       = 2; //处理中 
	private static final int QUERY_TYPE_OVER       = 3; //处理中 
	
	//审批人
	private static final int PROVER_BANZHUREN       = 1; //班主任审批
	private static final int PROVER_FUDAOYUAN       = 2; //辅导员审批
	private static final int PROVER_YUANLINGDAO     = 3; //院领导审批
	
	/**
     * @see HttpServlet#HttpServlet()
     */
    public LeaveApplicationServlet() {
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
			if (!qingjiaSesssion.hasPermission("applicaton.myapplication.query")) {
				MessageUtil.showMessage("查询班级", "没有访问权限", "TeacherServlet?option=1", request, response);
				return;
			}
			try {
				request.setAttribute("option", option);
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
				try {
					doAdd(request, response);
				} catch (ParseException e) {
					e.printStackTrace();
				}
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
				try {
					doUpdate(request, response);
				} catch (ParseException e) {
					e.printStackTrace();
				}
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
		case OPTION_OVER:
		{
			try {
				over(request,response);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			break;
		}
		case OPTION_DO_OVER:
		{
			try {
				doOver(request,response);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			break;
		}
		case OPTION_SHENPI_LIST:
		{
			try {
				shenpiList(request,response);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			break;
		}case OPTION_SHENPI:
		{
			try {
				shenpi(request,response);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			break;
		}
		case OPTION_DO_SHENPI:
		{
			try {
				try {
					doShenpi(request,response);
				} catch (ParseException e) {
					e.printStackTrace();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			break;
		}
		default:
			break;
		}
	}
	private void query(HttpServletRequest request, HttpServletResponse response)throws SQLException, ServletException, IOException  {
		//1、获取查询条件
		int type = Integer.parseInt((String) request.getParameter("type"));
		request.setAttribute("type", type);
		//2、获取学生的id
		HttpSession session = request.getSession();
		QingjiaSesssion qSesssion = (QingjiaSesssion) session.getAttribute(QingjiaSesssion.QINGJIA_SESSION);
		int studentId = qSesssion.getStudent().getId();
		
		//3、获取page
		Page page = (Page) request.getAttribute(Page.PAGE);
		//4、获取记录总数
		int totalRocords = LeaveApplicationService.getInstance().getTotalRocords(type,studentId);
		page.setTotalRocords(totalRocords);
		request.setAttribute(Page.PAGE, page);
		//5、获取分页数据
		List<LeaveApplicationView> appliations = LeaveApplicationService.getInstance().getPage(type,studentId,page);
		request.setAttribute("appliations", appliations);
		//6、设置分页过滤器的rul
		
		String url = new String("WEB-INF/jsp/apply/list.jsp");
		request.setAttribute("forwordUrl",url);	
	}

	private void add(HttpServletRequest request, HttpServletResponse response)throws SQLException, ServletException, IOException  {
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String now = format.format(date).replace(' ', 'T');
		request.setAttribute("now", now);
		request.setAttribute("option", OPTION_DO_ADD);
		request.getRequestDispatcher("WEB-INF/jsp/apply/add.jsp").forward(request, response);
	}

	private void doAdd(HttpServletRequest request, HttpServletResponse response)throws SQLException, ServletException, IOException, ParseException  {
		LeaveApplication appply = new LeaveApplication();
		appply.parse(request);
		HttpSession session = request.getSession();
		QingjiaSesssion qSesssion = (QingjiaSesssion) session.getAttribute(QingjiaSesssion.QINGJIA_SESSION);
		Student student = qSesssion.getStudent();
		appply.setStudentId(student.getId());
		appply.setStatus(LeaveApplication.STATUS_DAI_SHEN_PI);
		if (LeaveApplicationService.getInstance().add(appply)) {
			MessageUtil.showMessage("添加请假申请", "添加成功", "LeaveApplicationServlet?option=1&pageNo=1&type=1", request, response);
		}else {
			MessageUtil.showMessage("添加请假申请", "添加失败", "LeaveApplicationServlet?option=1&pageNo=1&type=1", request, response);
			
		}
	}

	private void update(HttpServletRequest request, HttpServletResponse response)throws SQLException, ServletException, IOException  {
		int id = Integer.parseInt(request.getParameter("id"));
		LeaveApplication application = LeaveApplicationService.getInstance().getById(id);
		application.setH5Begintime(DateUtil.dateToH5String(application.getBegintime()));
		application.setH5Endtime(DateUtil.dateToH5String(application.getEndtime()));
		request.setAttribute("application", application);
		
		request.setAttribute("option", OPTION_DO_UPDATE);
		request.getRequestDispatcher("WEB-INF/jsp/apply/update.jsp").forward(request, response);
	}

	private void doUpdate(HttpServletRequest request, HttpServletResponse response)throws SQLException, ServletException, IOException, ParseException  {
		LeaveApplication application = new LeaveApplication();
		application.parse(request);
		if (LeaveApplicationService.getInstance().update(application)) {
			MessageUtil.showMessage("修改请假申请", "修改成功", "LeaveApplicationServlet?option=1&pageNo=1&type=1", request, response);
		}else {
			MessageUtil.showMessage("修改请假申请", "修改失败", "LeaveApplicationServlet?option=1&pageNo=1&type=1", request, response);
			
		}
	}

	private void detail(HttpServletRequest request, HttpServletResponse response)throws SQLException, ServletException, IOException  {
		//获取基本信息
		int id = Integer.parseInt(request.getParameter("id"));
		LeaveApplicationView application = LeaveApplicationService.getInstance().getDetailById(id);
		request.setAttribute("application", application);
		//获取审批信息
		List<LeaveApplicationShenPiView> shenPies = LeaveApplicationService.getInstance().getShenpiesById(id);
		request.setAttribute("shenPies", shenPies);
		
		request.getRequestDispatcher("WEB-INF/jsp/apply/detail.jsp").forward(request, response);
	}
	private void shenpiList(HttpServletRequest request, HttpServletResponse response)throws SQLException, ServletException, IOException  {
		//1、获取查询条件
		int who = Integer.parseInt((String) request.getParameter("who"));
		request.setAttribute("shenpi", true);
		//2、获取学生的id
		HttpSession session = request.getSession();
		session.setAttribute("who", who);
		QingjiaSesssion qSesssion = (QingjiaSesssion) session.getAttribute(QingjiaSesssion.QINGJIA_SESSION);
		Teacher teacher = qSesssion.getTeacher();
				
		//3、获取page
		Page page = (Page) request.getAttribute(Page.PAGE);
		request.setAttribute("page", page);
		//4、获取记录总数
		int totalRocords = 0;
		List<LeaveApplicationView> appliations = null;
		switch (who) {
		case PROVER_BANZHUREN:{
			totalRocords = LeaveApplicationService.getInstance().getBanzhurenShenPiTotalRocords(LeaveApplication.STATUS_DAI_SHEN_PI,qSesssion.getManageClasses());
			appliations = LeaveApplicationService.getInstance().getBanzhurenShenPiPage(LeaveApplication.STATUS_DAI_SHEN_PI,qSesssion.getManageClasses(),page);
			break;
		}
		case PROVER_FUDAOYUAN:{
			totalRocords = LeaveApplicationService.getInstance().getYuanShenPiTotalRocords(LeaveApplication.STATUS_BANZHREN_PASS,teacher.getCollegeId());
			appliations = LeaveApplicationService.getInstance().getYuanShenPiPage(LeaveApplication.STATUS_BANZHREN_PASS,teacher.getCollegeId(),page);
			break;
		}
		case PROVER_YUANLINGDAO:{
			totalRocords = LeaveApplicationService.getInstance().getYuanShenPiTotalRocords(LeaveApplication.STATUS_FUDAOYUAN_PASS,teacher.getCollegeId());
			appliations = LeaveApplicationService.getInstance().getYuanShenPiPage(LeaveApplication.STATUS_FUDAOYUAN_PASS,teacher.getCollegeId(),page);
			break;
		}
		default:
			break;
		}
		page.setTotalRocords(totalRocords);
		request.setAttribute(Page.PAGE, page);
		//5、获取分页数据
		request.setAttribute("appliations", appliations);
		//6、设置分页过滤器的rul
		String url = new String("WEB-INF/jsp/apply/list.jsp");
		request.setAttribute("forwordUrl",url);	
	}
	private void shenpi(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		request.setAttribute("shenpi", true);
		request.setAttribute("option", OPTION_DO_SHENPI);
		detail(request, response);
	}
	private void doShenpi(HttpServletRequest request, HttpServletResponse response)throws SQLException, ServletException, IOException, ParseException  {
		LeaveApplication application = new LeaveApplication();
		application.parse(request);
		LeaveApplicationShenPi shenPi = new LeaveApplicationShenPi();
		shenPi.parse(request);
		HttpSession session = request.getSession();
		int who = (int) session.getAttribute("who");
		QingjiaSesssion qSesssion = (QingjiaSesssion) session.getAttribute(QingjiaSesssion.QINGJIA_SESSION);
		Teacher teacher = qSesssion.getTeacher();
		shenPi.setTeacherId(teacher.getId());
		if (LeaveApplicationService.getInstance().shenpi(application,shenPi)) {
			MessageUtil.showMessage("请假审批", "审批成功", "LeaveApplicationServlet?option=9&pageNo=1&who="+who, request, response);	
		}else{
			MessageUtil.showMessage("请假审批", "审批失败", "LeaveApplicationServlet?option=9&pageNo=1&who="+who, request, response);		
		}
	}
	private void over(HttpServletRequest request, HttpServletResponse response)throws SQLException, ServletException, IOException  {
		String url = "LeaveApplicationServlet?option=1&pageNo=1&type=1";
		HttpSession session = request.getSession();
		QingjiaSesssion qSesssion = (QingjiaSesssion) session.getAttribute(QingjiaSesssion.QINGJIA_SESSION);
		if (!qSesssion.hasPermission("applicaton.myapplication.update") || null == qSesssion.getStudent()) {
			MessageUtil.showMessage("销假", "没有访问权限", url, request, response);
			return;
		}
		//1、查询要销假的申请
		int id = Integer.parseInt(request.getParameter("id"));
		LeaveApplicationView application = LeaveApplicationService.getInstance().getDetailById(id);
		if (null == application || application.getStudentId() != qSesssion.getStudent().getId()) {
			MessageUtil.showMessage("销假", "要销假的申请不存在", url, request, response);
			return;
		}
		//2、只有审批通过的申请才能销假
		if (application.getStatus() != LeaveApplication.STATUS_PASS) {
			MessageUtil.showMessage("销假", "只有审批通过的申请才能销假", url, request, response);
			return;
		}
		//3、进入销假确认页
		request.setAttribute("application", application);
		request.setAttribute("option", OPTION_DO_OVER);
		request.getRequestDispatcher("WEB-INF/jsp/apply/over.jsp").forward(request, response);
	}

	private void doOver(HttpServletRequest request, HttpServletResponse response)throws SQLException, ServletException, IOException  {
		String url = "LeaveApplicationServlet?option=1&pageNo=1&type=3";
		HttpSession session = request.getSession();
		QingjiaSesssion qSesssion = (QingjiaSesssion) session.getAttribute(QingjiaSesssion.QINGJIA_SESSION);
		if (!qSesssion.hasPermission("applicaton.myapplication.update") || null == qSesssion.getStudent()) {
			MessageUtil.showMessage("销假", "没有访问权限", url, request, response);
			return;
		}
		int id = Integer.parseInt(request.getParameter("id"));
		LeaveApplicationView application = LeaveApplicationService.getInstance().getDetailById(id);
		if (null == application || application.getStudentId() != qSesssion.getStudent().getId()) {
			MessageUtil.showMessage("销假", "要销假的申请不存在", url, request, response);
			return;
		}
		if (LeaveApplicationService.getInstance().over(id)) {
			MessageUtil.showMessage("销假", "销假成功，本次请假已结束", url, request, response);
		}else{
			MessageUtil.showMessage("销假", "销假失败，只有审批通过的申请才能销假", url, request, response);
		}
	}

	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
