package com.cafe24.mall.dto;

/**
 * 주문코드에 따른 상품상세조회 를 위한 dto
 * @author 김석현
 *
 */
public class OrderGoodsDTO {

	private String goodsName;
	private String optionName;
	private int orderCnt;
	private String orderCode;
	private String orderStatus;
	private int sailingPrice;
	private String invoiceCode;
	
	public String getThumbnail() {
		return thumbnail;
	}
	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}
	private String thumbnail;
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public String getOptionName() {
		return optionName;
	}
	public void setOptionName(String optionName) {
		this.optionName = optionName;
	}
	public int getOrderCnt() {
		return orderCnt;
	}
	public void setOrderCnt(int orderCnt) {
		this.orderCnt = orderCnt;
	}
	public String getOrderCode() {
		return orderCode;
	}
	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}
	public String getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	public int getSailingPrice() {
		return sailingPrice;
	}
	public void setSailingPrice(int sailingPrice) {
		this.sailingPrice = sailingPrice;
	}
	public String getInvoiceCode() {
		return invoiceCode;
	}
	public void setInvoiceCode(String invoiceCode) {
		this.invoiceCode = invoiceCode;
	}
	@Override
	public String toString() {
		return "OrderGoodsDTO [goodsName=" + goodsName + ", optionName=" + optionName + ", orderCnt=" + orderCnt
				+ ", orderCode=" + orderCode + ", orderStatus=" + orderStatus + ", sailingPrice=" + sailingPrice
				+ ", invoiceCode=" + invoiceCode + ", thumbnail=" + thumbnail + "]";
	}
	
	
	
}
