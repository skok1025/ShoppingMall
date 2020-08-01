package com.cafe24.mall.vo;

public class CouponVo {
	
	private String no;
	private String name;
	private String type;
	private String salerate;
	
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getSalerate() {
		return salerate;
	}
	public void setSalerate(String salerate) {
		this.salerate = salerate;
	}
	@Override
	public String toString() {
		return "CouponVo [no=" + no + ", name=" + name + ", type=" + type + ", salerate=" + salerate + "]";
	}
	
	

}
