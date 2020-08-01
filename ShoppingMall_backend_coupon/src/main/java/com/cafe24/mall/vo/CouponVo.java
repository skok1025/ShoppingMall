package com.cafe24.mall.vo;

import java.util.List;

public class CouponVo {
	
	private String no;
	private String name;
	private String sale_type;
	private String sale_value;
	private String user_id;
	private String is_used;
	private String info_no;
	List<String> userIdList;
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSale_type() {
		return sale_type;
	}
	public void setSale_type(String sale_type) {
		this.sale_type = sale_type;
	}
	public String getSale_value() {
		return sale_value;
	}
	public void setSale_value(String sale_value) {
		this.sale_value = sale_value;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getIs_used() {
		return is_used;
	}
	public void setIs_used(String is_used) {
		this.is_used = is_used;
	}
	public String getInfo_no() {
		return info_no;
	}
	public void setInfo_no(String info_no) {
		this.info_no = info_no;
	}
	public List<String> getUserIdList() {
		return userIdList;
	}
	public void setUserIdList(List<String> userIdList) {
		this.userIdList = userIdList;
	}
	@Override
	public String toString() {
		return "CouponVo [no=" + no + ", name=" + name + ", sale_type=" + sale_type + ", sale_value=" + sale_value
				+ ", user_id=" + user_id + ", is_used=" + is_used + ", info_no=" + info_no + ", userIdList="
				+ userIdList + "]";
	}

}
