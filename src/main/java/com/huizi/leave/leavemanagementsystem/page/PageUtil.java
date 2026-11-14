package com.huizi.leave.leavemanagementsystem.page;

import java.util.Enumeration;

import javax.servlet.SessionTrackingMode;
import javax.servlet.http.HttpServletRequest;

public class PageUtil {
	private Page page;
	private String pageHref;
	private String firstHref;
	private String preHref;
	private String nextHref;
	private String endHref;
	public boolean init(Page page,HttpServletRequest request) {
		this.page = page;
		String servletPath = request.getServletPath();
		StringBuffer buffer =new StringBuffer(servletPath+"?");
		Enumeration<String> names = request.getParameterNames();
		String name;
		String value;
		while(names.hasMoreElements()){
			name = names.nextElement();
			if (!"pageNo".equals(name)) {
				value = request.getParameter(name);
				buffer.append(name);
				buffer.append("=");
				buffer.append(value);
				buffer.append("&");
			}
		}
		this.pageHref = buffer.toString();
		this.pageHref = pageHref.substring(1);
		firstHref = pageHref + "pageNo=1";
		if (page.getPageNo()-1 <=0) {
			preHref   = pageHref + "pageNo=1";
		}else{
			preHref   = pageHref + "pageNo="+(this.page.getPageNo()-1);
		}
		if (page.getPageNo() >=page.getTotalPages()) {
			nextHref  = pageHref + "pageNo=" + page.getTotalPages();
		}else{
			nextHref  = pageHref + "pageNo="+(this.page.getPageNo()+1);
		}
		
		endHref   = pageHref + "pageNo=" + this.page.getTotalPages();
		return true;
	}
	public String isShow() {
		return this.page.getTotalPages() > 1?"":"style='display:none'";
	}
	public String hasPre() {
		return this.page.getPageNo() > 1?"":"style='display:none'";
	}
	
	public String hasNext() {
		return this.page.getPageNo() < page.getTotalPages()?"":"style='display:none'";
	}
	public String getPageHref() {
		return pageHref;
	}
	public void setPageHref(String pageHref) {
		this.pageHref = pageHref;
	}
	public String getFirstHref() {
		return firstHref;
	}
	public void setFirstHref(String firstHref) {
		this.firstHref = firstHref;
	}
	public String getPreHref() {
		return preHref;
	}
	public void setPreHref(String preHref) {
		this.preHref = preHref;
	}
	public String getNextHref() {
		return nextHref;
	}
	public void setNextHref(String nextHref) {
		this.nextHref = nextHref;
	}
	public String getEndHref() {
		return endHref;
	}
	public void setEndHref(String endHref) {
		this.endHref = endHref;
	}
	
}
