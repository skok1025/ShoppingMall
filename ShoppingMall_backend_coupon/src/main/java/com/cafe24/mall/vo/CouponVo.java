package com.cafe24.mall.vo;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Data
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
	
	@Override
	public String toString() {
		return "CouponVo [no=" + no + ", info_no=" + info_no + ", member_no=" + member_no + ", name=" + name
				+ ", sale_type=" + sale_type + ", sale_value=" + sale_value + ", is_used=" + is_used
				+ ", ins_timestamp=" + ins_timestamp + ", upt_timestamp=" + upt_timestamp + ", is_delete=" + is_delete
				+ ", memberNoList=" + memberNoList + "]";
	}
}
