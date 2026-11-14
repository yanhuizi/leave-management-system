package com.huizi.leave.leavemanagementsystem.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.huizi.leave.leavemanagementsystem.session.QingjiaSesssion;


/**
 * Servlet Filter implementation class LoginFilter
 */
@WebFilter(
		urlPatterns = { "/*" }, 
		initParams = { 
				@WebInitParam(name = "noFilterUrl", value = "/LoginServlet;/login.jsp;/js;/css;/images")
		})
public class LoginFilter implements Filter {

	private FilterConfig fConfig;
	List<String> noFilterUrlList;
    /**
     * Default constructor. 
     */
    public LoginFilter() {
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
		HttpServletRequest hRequest = (HttpServletRequest)request;
		HttpServletResponse hResponse = (HttpServletResponse)response;
		
		String servletPath =  hRequest.getServletPath();
		String pathInfo    = hRequest.getPathInfo();
		String url = servletPath + (pathInfo==null?"":pathInfo);
		String contextPath = hRequest.getContextPath();
		//System.out.println(url);
		if (checkUrl(url)) {
			chain.doFilter(hRequest, hResponse);
		}else{
			HttpSession session = hRequest.getSession(false);
			if (session == null) {
				request.getRequestDispatcher("/login.jsp").forward(hRequest, hResponse);
			}else{
				
				QingjiaSesssion qSesssion = (QingjiaSesssion) session.getAttribute(QingjiaSesssion.QINGJIA_SESSION);
				if (qSesssion == null) {
					request.getRequestDispatcher("/login.jsp").forward(hRequest, hResponse);
				}else{
					chain.doFilter(request, response);
				}
				
			}
		}
		
	}

	private boolean checkUrl(String url) {
		int pos = url.indexOf('/', 1);
		if (pos == -1) {
			return  noFilterUrlList.contains(url);
		}else{
			url = url.substring(0,pos);
			return noFilterUrlList.contains(url);
		}
	}


	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
		this.fConfig = fConfig;
		String noFilterUrl = fConfig.getInitParameter("noFilterUrl");
		System.out.println(noFilterUrl);
		noFilterUrlList = new ArrayList<>();
		String[] noFilterUrls = noFilterUrl.split(";");
		
		for (String url : noFilterUrls) {
			noFilterUrlList.add(url);
		}
	}

}
