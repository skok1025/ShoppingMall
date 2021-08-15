package com.cafe24.mall.dto;

public class EavDTO {
	private String no;
	private String order_code;
	private String config_name;
	private String config_value;
	private String ins_timestamp;
	private String upt_timestamp;
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public String getOrder_code() {
		return order_code;
	}
	public void setOrder_code(String order_code) {
		this.order_code = order_code;
	}
	public String getConfig_name() {
		return config_name;
	}
	public void setConfig_name(String config_name) {
		this.config_name = config_name;
	}
	public String getConfig_value() {
		return config_value;
	}
	public void setConfig_value(String config_value) {
		this.config_value = config_value;
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
	@Override
	public String toString() {
		return "EavDTO [no=" + no + ", order_code=" + order_code + ", config_name=" + config_name + ", config_value="
				+ config_value + ", ins_timestamp=" + ins_timestamp + ", upt_timestamp=" + upt_timestamp + "]";
	}
	
	

}
