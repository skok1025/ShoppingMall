package com.cafe24.mall.vo;

public class OrderGoodsVo {

	public enum status{
		배송준비중,배송보류,배송대기,배송중,배송완료
	}
	
	private Long orderNo;
	private Long goodsDetailNo;
	private Integer cnt;
	private OrderGoodsVo.status status;
	private int sailingPrice;
	
	private String basketCode; // 장바구니 삭제를 위한 코드
	
	
	
	public String getBasketCode() {
		return basketCode;
	}

	public void setBasketCode(String basketCode) {
		this.basketCode = basketCode;
	}

	public OrderGoodsVo() {
	}
	
	public OrderGoodsVo(Long goodsDetailNo, int cnt, com.cafe24.mall.vo.OrderGoodsVo.status status) {
		
		this.goodsDetailNo = goodsDetailNo;
		this.cnt = cnt;
		this.status = status;
	}
	public Long getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(Long orderNo) {
		this.orderNo = orderNo;
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
	public OrderGoodsVo.status getStatus() {
		return status;
	}
	public void setStatus(OrderGoodsVo.status status) {
		this.status = status;
	}
	public int getSailingPrice() {
		return sailingPrice;
	}
	public void setSailingPrice(int sailingPrice) {
		this.sailingPrice = sailingPrice;
	}
	@Override
	public String toString() {
		return "OrderGoodsVo [orderNo=" + orderNo + ", goodsDetailNo=" + goodsDetailNo + ", cnt=" + cnt + ", status="
				+ status + ", sailingPrice=" + sailingPrice + ", basketCode=" + basketCode + "]";
	}
	
	
}
