package com.huizi.leave.leavemanagementsystem.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.huizi.leave.leavemanagementsystem.domain.College;
import com.huizi.leave.leavemanagementsystem.service.CollegeSevice;

/**
 * Servlet implementation class CollegeServlet
 */
@WebServlet("/CollegeServlet")
public class CollegeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final int OPTION_QUERY         = 1;
	private static final int OPTION_ADD           = 2;
	private static final int OPTION_DO_ADD        = 3;
	private static final int OPTION_UPDATE        = 4;
	private static final int OPTION_DO_UPDATE     = 5;   
	private static final int OPTION_DETIAL        = 9;  
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CollegeServlet() {
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
		default:
			break;
		}
	}

	

	private void query(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException, SQLException {
		// TODO Auto-generated method stub
		List<College> colleges = CollegeSevice.getInstance().getAll();
		request.setAttribute("colleges", colleges);
		request.getRequestDispatcher("WEB-INF/jsp/colllege/list.jsp").forward(request, response);
	}

	private void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setAttribute("option", OPTION_DO_ADD);
		
		request.getRequestDispatcher("WEB-INF/jsp/colllege/add.jsp").forward(request, response);		
	}

	private void doAdd(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException, SQLException {
		// TODO Auto-generated method stub
		College college = new College();
		college.parse(request);
		if (CollegeSevice.getInstance().add(college)) {
			request.setAttribute("title", "添加学院");
			request.setAttribute("message", "添加成功");
			request.setAttribute("url", "CollegeServlet?option=1");
			request.getRequestDispatcher("public/message.jsp").forward(request, response);
		}else{
			request.setAttribute("title", "添加学院");
			request.setAttribute("message", "添加失败");
			request.setAttribute("url", "CollegeServlet?option=1");
			request.getRequestDispatcher("public/message.jsp").forward(request, response);
		}
		
	}

	private void update(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException, SQLException {
		// TODO Auto-generated method stub
		String id = request.getParameter("id");
		College college = CollegeSevice.getInstance().getById(id);
		request.setAttribute("college", college);
		request.setAttribute("option", OPTION_DO_UPDATE);
		request.getRequestDispatcher("WEB-INF/jsp/colllege/update.jsp").forward(request, response);		
		
	}

	private void doUpdate(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException, SQLException {
		// TODO Auto-generated method stub
		College college = new College();
		college.parse(request);
		if (CollegeSevice.getInstance().updateById(college)) {
			request.setAttribute("title", "修改学院");
			request.setAttribute("message", "修改成功");
			request.setAttribute("url", "CollegeServlet?option=1");
			request.getRequestDispatcher("public/message.jsp").forward(request, response);
		}else{
			request.setAttribute("title", "修改学院");
			request.setAttribute("message", "修改失败");
			request.setAttribute("url", "CollegeServlet?option=1");
			request.getRequestDispatcher("public/message.jsp").forward(request, response);
		
		}
	}

	private void detail(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		// TODO Auto-generated method stub
		String id = request.getParameter("id");
		College college = CollegeSevice.getInstance().getById(id);
		request.setAttribute("college", college);
		request.getRequestDispatcher("WEB-INF/jsp/colllege/detail.jsp").forward(request, response);		
		
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
