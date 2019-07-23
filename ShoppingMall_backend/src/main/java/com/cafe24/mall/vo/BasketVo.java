package com.cafe24.mall.vo;

public class BasketVo {

	private Long no;
	private String basketCode;
	private Long goodsDetailNo;
	private int cnt;
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public String getBasketCode() {
		return basketCode;
	}
	public void setBasketCode(String basketCode) {
		this.basketCode = basketCode;
	}
	public Long getGoodsDetailNo() {
		return goodsDetailNo;
	}
	public void setGoodsDetailNo(Long goodsDetailNo) {
		this.goodsDetailNo = goodsDetailNo;
	}
	public int getCnt() {
		return cnt;
	}
	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
	@Override
	public String toString() {
		return "BasketVo [no=" + no + ", basketCode=" + basketCode + ", goodsDetailNo=" + goodsDetailNo + ", cnt=" + cnt
				+ "]";
	}
	
	
	
}
