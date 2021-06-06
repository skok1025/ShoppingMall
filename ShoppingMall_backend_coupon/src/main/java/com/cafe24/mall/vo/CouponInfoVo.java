package com.cafe24.mall.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CouponInfoVo {
	private String info_no;
	private String name;
	private String sale_type;
	private String sale_value;
	private String ins_timestamp;
	private String upt_timestamp;
	private String is_delete;
}
