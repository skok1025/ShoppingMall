package com.cafe24.mall.vo;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

public class CouponVo {
	private Long no;
	private String info_no;
	private String member_no;
	private String name;
	private String sale_type;
	private String sale_value;
	private String is_used;
	private String ins_timestamp;
	private String upt_timestamp;
	private String is_delete;
	private List<String> memberNoList;

	public Long getNo() {
		return no;
	}

	public void setNo(Long no) {
		this.no = no;
	}

	public String getInfo_no() {
		return info_no;
	}

	public void setInfo_no(String info_no) {
		this.info_no = info_no;
	}

	public String getMember_no() {
		return member_no;
	}

	public void setMember_no(String member_no) {
		this.member_no = member_no;
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

	public String getIs_used() {
		return is_used;
	}

	public void setIs_used(String is_used) {
		this.is_used = is_used;
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

	public List<String> getMemberNoList() {
		return memberNoList;
	}

	public void setMemberNoList(List<String> memberNoList) {
		this.memberNoList = memberNoList;
	}

	@Override
	public String toString() {
		return "CouponVo [no=" + no + ", info_no=" + info_no + ", member_no=" + member_no + ", name=" + name
				+ ", sale_type=" + sale_type + ", sale_value=" + sale_value + ", is_used=" + is_used
				+ ", ins_timestamp=" + ins_timestamp + ", upt_timestamp=" + upt_timestamp + ", is_delete=" + is_delete
				+ ", memberNoList=" + memberNoList + "]";
	}
}
