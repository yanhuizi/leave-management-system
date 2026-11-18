package com.huizi.leave.leavemanagementsystem.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.huizi.leave.leavemanagementsystem.domain.StatOverview;
import com.huizi.leave.leavemanagementsystem.service.StatService;

/**
 * 数据统计：统计学院/班级/学生/教师/角色数量及请假申请状态分布。
 */
@WebServlet("/StatServlet")
public class StatServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			StatOverview overview = StatService.getInstance().getOverview();
			request.setAttribute("overview", overview);
			request.getRequestDispatcher("WEB-INF/jsp/sys/stat.jsp").forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
