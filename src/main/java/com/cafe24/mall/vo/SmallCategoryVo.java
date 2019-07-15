package com.cafe24.mall.vo;

public class SmallCategoryVo {
	
	
	private Long no;
	private String name;
	private Long bigcategoryNo;
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getBigcategoryNo() {
		return bigcategoryNo;
	}
	public void setBigcategoryNo(Long bigcategoryNo) {
		this.bigcategoryNo = bigcategoryNo;
	}
	@Override
	public String toString() {
		return "SmallCategoryVo [no=" + no + ", name=" + name + ", bigcategoryNo=" + bigcategoryNo + "]";
	}
	
	

}
