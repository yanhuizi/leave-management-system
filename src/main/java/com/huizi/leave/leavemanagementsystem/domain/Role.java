package com.huizi.leave.leavemanagementsystem.domain;

import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

public class Role {
	private int id;
	private String name;
	private int eareType;
	private String remark;
	private Set<String> menus;
	
	public void parse(HttpServletRequest request) {
		String strId = request.getParameter("id");
		if (null !=strId) {
			id = Integer.parseInt(strId);
		}
		name = request.getParameter("name");
		remark = request.getParameter("remark");
		eareType = Integer.parseInt(request.getParameter("eareType"));
	}
	
	public String getTypeName(List<RoleType> types,int type) {
		for (RoleType roleType : types) {
			if (roleType.getId() == type) {
				return roleType.getName();
			}
		}
		return "";
	}
	
	public boolean hasPermission(String permisson) {
		return menus.contains(permisson);
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getEareType() {
		return eareType;
	}
	public void setEareType(int eareType) {
		this.eareType = eareType;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Set<String> getMenus() {
		return menus;
	}

	public void setMenus(Set<String> menus) {
		this.menus = menus;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Role other = (Role) obj;
		if (id != other.id)
			return false;
		return true;
	}

}
