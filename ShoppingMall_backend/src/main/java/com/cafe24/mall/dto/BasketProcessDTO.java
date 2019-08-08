package com.cafe24.mall.dto;

public class BasketProcessDTO {
	
	private Long memberNo;
	private Long goodsDetailNo;
	private Integer cnt;
	public Long getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(Long memberNo) {
		this.memberNo = memberNo;
	}
	public Long getGoodsDetailNo() {
		return goodsDetailNo;
	}
	public void setGoodsDetailNo(Long goodsDetailNo) {
		this.goodsDetailNo = goodsDetailNo;
	}
	public Integer getCnt() {
		return cnt;
	}
	public void setCnt(Integer cnt) {
		this.cnt = cnt;
	}
	@Override
	public String toString() {
		return "BasketProcessDTO [memberNo=" + memberNo + ", goodsDetailNo=" + goodsDetailNo + ", cnt=" + cnt + "]";
	}
	
	

}
