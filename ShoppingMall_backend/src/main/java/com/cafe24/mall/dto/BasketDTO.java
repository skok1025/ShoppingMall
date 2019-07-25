package com.cafe24.mall.dto;

public class BasketDTO {

	private Long no; // 삭제를 위해
	private Long goodsDetailNo; // 주문을 위해
	
	private String thumbnail;
	private String goodsName;
	private String optionName;
	private int cnt;
	private int price;
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public Long getGoodsDetailNo() {
		return goodsDetailNo;
	}
	public void setGoodsDetailNo(Long goodsDetailNo) {
		this.goodsDetailNo = goodsDetailNo;
	}
	public String getThumbnail() {
		return thumbnail;
	}
	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}
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
	public int getCnt() {
		return cnt;
	}
	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return "BasketDTO [no=" + no + ", goodsDetailNo=" + goodsDetailNo + ", thumbnail=" + thumbnail + ", goodsName="
				+ goodsName + ", optionName=" + optionName + ", cnt=" + cnt + ", price=" + price + "]";
	}
	
	
	
}
