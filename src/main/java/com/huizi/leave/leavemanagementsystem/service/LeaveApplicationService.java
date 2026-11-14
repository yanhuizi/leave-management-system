package com.huizi.leave.leavemanagementsystem.service;

import java.sql.SQLException;
import java.util.List;


import com.huizi.leave.leavemanagementsystem.dao.LeaveApplicationDao;
import com.huizi.leave.leavemanagementsystem.domain.LeaveApplication;
import com.huizi.leave.leavemanagementsystem.domain.LeaveApplicationShenPi;
import com.huizi.leave.leavemanagementsystem.domain.LeaveApplicationShenPiView;
import com.huizi.leave.leavemanagementsystem.domain.LeaveApplicationView;
import com.huizi.leave.leavemanagementsystem.page.Page;

public class LeaveApplicationService {
	
	//查询请假申请类型
	private static final int QUERY_TYPE_PROCESSING = 1; //处理中 
	private static final int QUERY_TYPE_PASS       = 2; //处理中 
	private static final int QUERY_TYPE_OVER       = 3; //处理中 
		
	//审批人
	private static final int PROVER_BANZHUREN       = 1; //班主任审批
	private static final int PROVER_FUDAOYUAN       = 2; //辅导员审批
	private static final int PROVER_YUANLINGDAO     = 3; //院领导审批
		
	private static LeaveApplicationService instance;
	private LeaveApplicationService(){};
	static{
		instance = new LeaveApplicationService();
	}
	public static LeaveApplicationService getInstance() {
		return instance;
	}
	public int getTotalRocords(int type, int studentId) throws SQLException {
		return LeaveApplicationDao.getInstance().getTotalRocords(type,studentId);
	}
	public List<LeaveApplicationView> getPage(int type, int studentId, Page page) throws SQLException {
		return LeaveApplicationDao.getInstance().getPage(type,studentId,page);
	}
	public boolean add(LeaveApplication appply) throws SQLException {
		return LeaveApplicationDao.getInstance().add(appply);
	}
	public LeaveApplication getById(int id) throws SQLException {
		return LeaveApplicationDao.getInstance().getById(id);
	}
	public boolean update(LeaveApplication application) throws SQLException {
		return LeaveApplicationDao.getInstance().update(application);
	}
	public LeaveApplicationView getDetailById(int id) throws SQLException {
		return LeaveApplicationDao.getInstance().getDetailById(id);
	}
	public List<LeaveApplicationShenPiView> getShenpiesById(int id) throws SQLException {
		return LeaveApplicationDao.getInstance().getShenpiesById(id);
	}
	public int getBanzhurenShenPiTotalRocords(int statusDaiShenPi, String manageClasses) throws SQLException {
		return LeaveApplicationDao.getInstance().getBanzhurenShenPiTotalRocords(statusDaiShenPi, manageClasses);
	}
	public List<LeaveApplicationView> getBanzhurenShenPiPage(int statusDaiShenPi, String manageClasses, Page page) throws SQLException {
		return LeaveApplicationDao.getInstance().getBanzhurenShenPiPage(statusDaiShenPi, manageClasses, page);
	}
	public int getYuanShenPiTotalRocords(int statusBanzhrenPass, int collegeId) throws SQLException {
		return LeaveApplicationDao.getInstance().getYuanShenPiTotalRocords(statusBanzhrenPass, collegeId);
	}
	public List<LeaveApplicationView> getYuanShenPiPage(int statusBanzhrenPass, int collegeId, Page page) throws SQLException {
		return LeaveApplicationDao.getInstance().getYuanShenPiPage(statusBanzhrenPass, collegeId, page);
	}
	public boolean shenpi(LeaveApplication application, LeaveApplicationShenPi shenPi) throws SQLException {
		shenPi.setApplicationId(application.getId());
		if (shenPi.getResult() == LeaveApplicationShenPi.RESULT_TYPE_AGREE) {
			switch (application.getType()) {
			case LeaveApplication.TYPE_IN_ONE:
			{//一天以内
				application.setStatus(LeaveApplication.STATUS_PASS);
				break;
			}
			case LeaveApplication.TYPE_BETWEEM_ONE_THREE:
			{//一天外三天以内
				if (application.getStatus() == LeaveApplication.STATUS_DAI_SHEN_PI) {
					application.setStatus(LeaveApplication.STATUS_BANZHREN_PASS);
				}else{
					application.setStatus(LeaveApplication.STATUS_PASS);
				}
				break;
			}
			case LeaveApplication.TYPE_GREATER_THREE:
			case LeaveApplication.TYPE_OUTER_ENSHI:
			{//三天以外
				if (application.getStatus() == LeaveApplication.STATUS_DAI_SHEN_PI) {
					application.setStatus(LeaveApplication.STATUS_BANZHREN_PASS);
				}else if(application.getStatus() == LeaveApplication.STATUS_BANZHREN_PASS){
					application.setStatus(LeaveApplication.STATUS_FUDAOYUAN_PASS);
				}else{
					application.setStatus(LeaveApplication.STATUS_PASS);
				}
				break;
			}
			default:
				break;
			}
		}else{
			application.setStatus(LeaveApplication.STATUS_NOT_PASS);
		}
		return LeaveApplicationDao.getInstance().shenpi(application, shenPi);
	}

	/**
	 * 销假：把审批通过的申请置为“结束”。
	 */
	public boolean over(int id) throws SQLException {
		return LeaveApplicationDao.getInstance().over(id);
	}
}
