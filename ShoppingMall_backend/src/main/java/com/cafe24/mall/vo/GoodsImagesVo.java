package com.cafe24.mall.vo;

public class GoodsImagesVo {
	public enum status{
		y,n
	}
	
	private Long no;
	private Long goodsNo;
	private String image;
	private GoodsImagesVo.status ismain;
	
	public GoodsImagesVo() {
		
	}
	
	
	public GoodsImagesVo(Long goodsNo, String image, status ismain) {
		this.goodsNo = goodsNo;
		this.image = image;
		this.ismain = ismain;
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
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public GoodsImagesVo.status getIsmain() {
		return ismain;
	}
	public void setIsmain(GoodsImagesVo.status ismain) {
		this.ismain = ismain;
	}
	@Override
	public String toString() {
		return "GoodsImagesVo [no=" + no + ", goodsNo=" + goodsNo + ", image=" + image + ", ismain=" + ismain + "]";
	}
	
	
	
	
	
}
