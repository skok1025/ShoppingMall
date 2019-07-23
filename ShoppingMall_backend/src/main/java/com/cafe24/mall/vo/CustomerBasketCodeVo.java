package com.cafe24.mall.vo;

public class CustomerBasketCodeVo {

	private String code; 
	private Long memberNo;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Long getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(Long memberNo) {
		this.memberNo = memberNo;
	}
	@Override
	public String toString() {
		return "CustomerBasketCodeVo [code=" + code + ", memberNo=" + memberNo + "]";
	}
	
	
}
