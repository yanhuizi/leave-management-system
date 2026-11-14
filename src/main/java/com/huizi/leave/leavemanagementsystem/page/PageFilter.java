package com.huizi.leave.leavemanagementsystem.page;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

/**
 * Servlet Filter implementation class PageFilter
 */
public class PageFilter implements Filter {

    /**
     * Default constructor. 
     */
    public PageFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here	
		String strPageNo = request.getParameter("pageNo");
		if (null != strPageNo) {
			int pageNo = Integer.parseInt(strPageNo);
			Page page = new Page();
			page.setPageNo(pageNo);
			request.setAttribute("page", page);
		}
		
		// pass the request along the filter chain
		chain.doFilter(request, response);
		HttpServletRequest hRequest = (HttpServletRequest)request;
		//判断request中是否有page属性
		Page page = (Page) hRequest.getAttribute("page");
		if (page !=null) {
			//创建一个PageUtil对象
			if (page.getTotalRocords() == 0) {
				page.setTotalPages(0);
			}else{
				page.setTotalPages((page.getTotalRocords()%page.getPageSize())==0?
						page.getTotalRocords()/page.getPageSize():page.getTotalRocords()/page.getPageSize()+1);
			}
			PageUtil pageUtil = new PageUtil();
			pageUtil.init(page, hRequest);
			hRequest.setAttribute("pageUtil", pageUtil);
			//forward到目标页面
			String url = (String) hRequest.getAttribute("forwordUrl");
			if (null != url) {
				hRequest.getRequestDispatcher(url).forward(hRequest, response);
			}
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
