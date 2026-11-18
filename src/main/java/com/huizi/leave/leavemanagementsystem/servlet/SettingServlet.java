package com.huizi.leave.leavemanagementsystem.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.huizi.leave.leavemanagementsystem.service.StudentService;
import com.huizi.leave.leavemanagementsystem.service.TeacherService;
import com.huizi.leave.leavemanagementsystem.session.QingjiaSesssion;

/**
 * 个人中心：展示当前登录用户的详细信息，并提供修改密码入口。
 */
@WebServlet("/SettingServlet")
public class SettingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		QingjiaSesssion qSession = (QingjiaSesssion) session.getAttribute(QingjiaSesssion.QINGJIA_SESSION);
		try {
			request.setAttribute("userType", qSession.getUserType());
			if (qSession.getUserType() == QingjiaSesssion.USER_TYPE_TEACHER) {
				request.setAttribute("user", TeacherService.getInstance().getById(qSession.getTeacher().getId()));
			} else {
				request.setAttribute("user", StudentService.getInstance().getByid(qSession.getStudent().getId()));
			}
			request.getRequestDispatcher("WEB-INF/jsp/sys/setting.jsp").forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
