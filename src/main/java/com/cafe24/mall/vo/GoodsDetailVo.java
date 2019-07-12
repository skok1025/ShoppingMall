package com.cafe24.mall.vo;

public class GoodsDetailVo {
	private Long no;
	private Long goodsNo;
	private String optionName;
	private int inventoryQty;
	private int availableQty;
	
	public GoodsDetailVo() {
		
	}
	
	public GoodsDetailVo(Long no, Long goodsNo, String optionName, int inventoryQty, int availableQty) {
		this.no = no;
		this.goodsNo = goodsNo;
		this.optionName = optionName;
		this.inventoryQty = inventoryQty;
		this.availableQty = availableQty;
	}




	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public Long getGoodsNo() {
		return goodsNo;
	}
	public void setGoodsNo(Long goodsNo) {
		this.goodsNo = goodsNo;
	}
	public String getOptionName() {
		return optionName;
	}
	public void setOptionName(String optionName) {
		this.optionName = optionName;
	}
	public int getInventoryQty() {
		return inventoryQty;
	}
	public void setInventoryQty(int inventoryQty) {
		this.inventoryQty = inventoryQty;
	}
	public int getAvailableQty() {
		return availableQty;
	}
	public void setAvailableQty(int availableQty) {
		this.availableQty = availableQty;
	}
	@Override
	public String toString() {
		return "GoodsDetailVo [no=" + no + ", goodsNo=" + goodsNo + ", optionName=" + optionName + ", inventoryQty="
				+ inventoryQty + ", availableQty=" + availableQty + "]";
	}
	
	

}
