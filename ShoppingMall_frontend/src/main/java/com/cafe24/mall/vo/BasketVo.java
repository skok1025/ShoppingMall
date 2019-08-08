package com.cafe24.mall.vo;

import java.util.List;

import com.cafe24.mall.dto.BasketDTO;

public class BasketVo {

	private Long no;
	private String basketCode;
	private Long goodsDetailNo;
	private int cnt;

	List<BasketDTO> goodsBasketList; // 상품 상세보기에서 선택된 상품 상세번호 , 갯수
	
	public List<BasketDTO> getGoodsBasketList() {
		return goodsBasketList;
	}
	public void setGoodsBasketList(List<BasketDTO> goodsBasketList) {
		this.goodsBasketList = goodsBasketList;
	}
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
				+ ", goodsBasketList=" + goodsBasketList + "]";
	}
	
	
	
}
