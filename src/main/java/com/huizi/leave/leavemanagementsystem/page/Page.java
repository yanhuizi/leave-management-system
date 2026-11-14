package com.huizi.leave.leavemanagementsystem.page;

public class Page {
	public static final int PAGE_SIZE = 2;
	public static final String PAGE = "page";
	private int pageNo;
	private static int pageSize = PAGE_SIZE;
	private int totalRocords;
	private int totalPages;
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public static int getPageSize() {
		return pageSize;
	}
	public static void setPageSize(int pageSize) {
		Page.pageSize = pageSize;
	}
	public int getTotalPages() {
		return totalPages;
	}
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}
	public int getTotalRocords() {
		return totalRocords;
	}
	public void setTotalRocords(int totalRocords) {
		this.totalRocords = totalRocords;
	}
	
}
