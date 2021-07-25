package com.cafe24.mall.vo;


public class CouponInfoVo {
	private String info_no;
	private String name;
	private String sale_type;
	private String sale_value;
	private String ins_timestamp;
	private String upt_timestamp;
	private String is_delete;
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
	public String getIns_timestamp() {
		return ins_timestamp;
	}
	public void setIns_timestamp(String ins_timestamp) {
		this.ins_timestamp = ins_timestamp;
	}
	public String getUpt_timestamp() {
		return upt_timestamp;
	}
	public void setUpt_timestamp(String upt_timestamp) {
		this.upt_timestamp = upt_timestamp;
	}
	public String getIs_delete() {
		return is_delete;
	}
	public void setIs_delete(String is_delete) {
		this.is_delete = is_delete;
	}
	@Override
	public String toString() {
		return "CouponInfoVo [info_no=" + info_no + ", name=" + name + ", sale_type=" + sale_type + ", sale_value="
				+ sale_value + ", ins_timestamp=" + ins_timestamp + ", upt_timestamp=" + upt_timestamp + ", is_delete="
				+ is_delete + "]";
	}
	
	
}
