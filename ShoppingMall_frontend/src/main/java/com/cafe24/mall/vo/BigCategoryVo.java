package com.cafe24.mall.vo;

import java.util.List;

import org.hibernate.validator.constraints.Length;

public class BigCategoryVo {
	
	private Long no;
	
	@Length(max = 10, message = "카테고리명은 10자 이하 입니다.")
	private String name;
	
	private int presult;
	
	private List<SmallCategoryVo> smallCategoryList;
	
	
	
	public List<SmallCategoryVo> getSmallCategoryList() {
		return smallCategoryList;
	}
	public void setSmallCategoryList(List<SmallCategoryVo> smallCategoryList) {
		this.smallCategoryList = smallCategoryList;
	}
	public int getPresult() {
		return presult;
	}
	public void setPresult(int presult) {
		this.presult = presult;
	}
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
	@Override
	public String toString() {
		return "BigCategoryVo [no=" + no + ", name=" + name + ", presult=" + presult + ", smallCategoryList="
				+ smallCategoryList + "]";
	}
	
	
	

}
