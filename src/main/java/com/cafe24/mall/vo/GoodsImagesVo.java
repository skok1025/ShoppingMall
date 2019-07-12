package com.cafe24.mall.vo;

public class GoodsImagesVo {
	public enum isMain{
		y,n
	}
	
	private Long no;
	private Long goodsNo;
	private String image;
	private GoodsImagesVo.isMain ismain;
	
	public GoodsImagesVo() {
		
	}
	
	public GoodsImagesVo(Long no, String image) {
		this.no = no;
		
		this.image = image;
		
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
	public GoodsImagesVo.isMain getIsmain() {
		return ismain;
	}
	public void setIsmain(GoodsImagesVo.isMain ismain) {
		this.ismain = ismain;
	}
	@Override
	public String toString() {
		return "GoodsImagesVo [no=" + no + ", goodsNo=" + goodsNo + ", image=" + image + ", ismain=" + ismain + "]";
	}
	
	
	
	
	
}
