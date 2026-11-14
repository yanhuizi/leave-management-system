package com.huizi.leave.leavemanagementsystem.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import com.huizi.leave.leavemanagementsystem.domain.LeaveApplicationStatus;
import com.huizi.leave.leavemanagementsystem.service.InitParamService;

public class InitServlet implements Servlet {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public ServletConfig getServletConfig() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getServletInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		ServletContext appliation = config.getServletContext();
		List<LeaveApplicationStatus> qingjiaType;
		
		try {
			qingjiaType = InitParamService.getInstance().getLeaveApplicationResultType();
			appliation.setAttribute("qingjiaShenpiResult", qingjiaType);
			appliation.setAttribute("sexTypes", InitParamService
					.getInstance()
					.getSexType());
			appliation.setAttribute("menuTypes", InitParamService
					.getInstance()
					.getMenuType());
			appliation.setAttribute("roleTypes", InitParamService
					.getInstance()
					.getRoleType());
			appliation.setAttribute("qingJiaTypes", InitParamService
					.getInstance()
					.getLeaveApplicationType());
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void service(ServletRequest arg0, ServletResponse arg1) throws ServletException, IOException {
		// TODO Auto-generated method stub

	}

}
