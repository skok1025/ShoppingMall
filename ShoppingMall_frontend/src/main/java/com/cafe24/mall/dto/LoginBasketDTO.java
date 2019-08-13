package com.cafe24.mall.dto;

public class LoginBasketDTO {

	private String basketCode;
	private Long memberNo;
	public String getBasketCode() {
		return basketCode;
	}
	public void setBasketCode(String basketCode) {
		this.basketCode = basketCode;
	}
	public Long getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(Long memberNo) {
		this.memberNo = memberNo;
	}
	@Override
	public String toString() {
		return "LoginBasketDTO [basketCode=" + basketCode + ", memberNo=" + memberNo + "]";
	}
	
	
}
