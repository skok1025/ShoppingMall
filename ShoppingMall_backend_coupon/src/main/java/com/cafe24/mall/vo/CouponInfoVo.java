package com.cafe24.mall.vo;

import java.util.List;

public class CouponInfoVo {
	private String info_no;
	private String name;
	private String sale_type;
	private String sale_value;
	private String issued_timestamp;
	public String getInfo_no() {
		return info_no;
	}
	public void setInfo_no(String info_no) {
		this.info_no = info_no;
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
	public String getIssued_timestamp() {
		return issued_timestamp;
	}
	public void setIssued_timestamp(String issued_timestamp) {
		this.issued_timestamp = issued_timestamp;
	}
	@Override
	public String toString() {
		return "CouponInfoVo [info_no=" + info_no + ", name=" + name + ", sale_type=" + sale_type + ", sale_value="
				+ sale_value + ", issued_timestamp=" + issued_timestamp + "]";
	}
	
	
	
	
	
}
