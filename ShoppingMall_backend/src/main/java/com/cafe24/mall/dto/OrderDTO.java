package com.cafe24.mall.dto;

public class OrderDTO {
	
	private String code; // 주문코드
	private String regdate; // 주문 날짜
	private String titleGoodsName; // 대표상품명
	private int orderGoodsCnt; // 주문한 상품수
	private int total; // 전체 주문금액
	
	private String memberId; // 회원아이디
	private String memberName; // 회원명
	private String orderName; // 주문자명
	private String orderTel; // 주문자 연락처
	
	public String getCode() {
		return code;
	}

	public String getOrderTel() {
		return orderTel;
	}
	public void setOrderTel(String orderTel) {
		this.orderTel = orderTel;
	}
	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getOrderName() {
		return orderName;
	}

	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}

	public void setCode(String code) {
		this.code = code;
	}
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	public String getTitleGoodsName() {
		return titleGoodsName;
	}
	public void setTitleGoodsName(String titleGoodsName) {
		this.titleGoodsName = titleGoodsName;
	}
	public int getOrderGoodsCnt() {
		return orderGoodsCnt;
	}
	public void setOrderGoodsCnt(int orderGoodsCnt) {
		this.orderGoodsCnt = orderGoodsCnt;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	@Override
	public String toString() {
		return "OrderDTO [code=" + code + ", regdate=" + regdate + ", titleGoodsName=" + titleGoodsName
				+ ", orderGoodsCnt=" + orderGoodsCnt + ", total=" + total + ", memberId=" + memberId + ", memberName="
				+ memberName + ", orderName=" + orderName + ", orderTel=" + orderTel + "]";
	}
	
	
	

}
