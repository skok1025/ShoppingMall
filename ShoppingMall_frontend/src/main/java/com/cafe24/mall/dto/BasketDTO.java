package com.cafe24.mall.dto;

/**
 * 상품 상세보기에서 선택된 상품상세번호 및 갯수를 담는 DTO
 * @author 김석현
 *
 */
public class BasketDTO {

	private Long goodsDetailNo;
	private Integer cnt;
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
		return "BasketDTO [goodsDetailNo=" + goodsDetailNo + ", cnt=" + cnt + "]";
	}
	
	
	
}
