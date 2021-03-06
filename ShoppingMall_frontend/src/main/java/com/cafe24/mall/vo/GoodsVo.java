package com.cafe24.mall.vo;

import java.util.List;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

public class GoodsVo {

	public enum status{
		y,n
	}
	
	private Long no;
	@NotEmpty
	@Length(max = 20)
	private String name;
	//@Pattern(regexp = "^[0-9]*$",message = "판매가격에 숫자만 입력")
	private int seillingPrice;
	private String detail;
	private String viewdetail;
	
	public String getViewdetail() {
		return viewdetail;
	}
	public void setViewdetail(String viewdetail) {
		this.viewdetail = viewdetail;
	}
	private GoodsVo.status displayStatus;
	private GoodsVo.status seillingStatus;
	private String regdate;
	private String material;
	private String supplier;
	private String manufacturer;
	private String manufacturingDate;
	private String origin;
	private Long smallcategoryNo;
	
	private List<GoodsImagesVo> goodsImagesList; // 상품의 이미지들
	private List<GoodsDetailVo> goodsDetailList; // 상품의 옵션에 대한 정보 리스트
	
	private String thumbnail;
	private String bigcategoryName;
	private String smallcategoryName;
	
	private String optionListTxt;
	
//	private MultipartFile mainattach;
//	private MultipartFile subattach1;
//	private MultipartFile subattach2;
//	private MultipartFile subattach3;
	
	
	
	
	
	
	public String getOptionListTxt() {
		return optionListTxt;
	}
	public void setOptionListTxt(String optionListTxt) {
		this.optionListTxt = optionListTxt;
	}
	public String getBigcategoryName() {
		return bigcategoryName;
	}
	public void setBigcategoryName(String bigcategoryName) {
		this.bigcategoryName = bigcategoryName;
	}
	public String getSmallcategoryName() {
		return smallcategoryName;
	}
	public void setSmallcategoryName(String smallcategoryName) {
		this.smallcategoryName = smallcategoryName;
	}
	public String getThumbnail() {
		return thumbnail;
	}
	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}
	public List<GoodsDetailVo> getGoodsDetailList() {
		return goodsDetailList;
	}
	public void setGoodsDetailList(List<GoodsDetailVo> goodsDetailList) {
		this.goodsDetailList = goodsDetailList;
	}
	public List<GoodsImagesVo> getGoodsImagesList() {
		return goodsImagesList;
	}
	public void setGoodsImagesList(List<GoodsImagesVo> goodsImagesList) {
		this.goodsImagesList = goodsImagesList;
	}
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getSeillingPrice() {
		return seillingPrice;
	}
	public void setSeillingPrice(int seillingPrice) {
		this.seillingPrice = seillingPrice;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public GoodsVo.status getDisplayStatus() {
		return displayStatus;
	}
	public void setDisplayStatus(GoodsVo.status displayStatus) {
		this.displayStatus = displayStatus;
	}
	public GoodsVo.status getSeillingStatus() {
		return seillingStatus;
	}
	public void setSeillingStatus(GoodsVo.status seillingStatus) {
		this.seillingStatus = seillingStatus;
	}
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	public String getMaterial() {
		return material;
	}
	public void setMaterial(String material) {
		this.material = material;
	}
	public String getSupplier() {
		return supplier;
	}
	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}
	public String getManufacturer() {
		return manufacturer;
	}
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
	public String getManufacturingDate() {
		return manufacturingDate;
	}
	public void setManufacturingDate(String manufacturingDate) {
		if("".equals(manufacturingDate)) {
			return;
		}
		this.manufacturingDate = manufacturingDate;
	}
	public String getOrigin() {
		return origin;
	}
	public void setOrigin(String origin) {
		this.origin = origin;
	}
	public Long getSmallcategoryNo() {
		return smallcategoryNo;
	}
	public void setSmallcategoryNo(Long smallcategoryNo) {
		this.smallcategoryNo = smallcategoryNo;
	}
	@Override
	public String toString() {
		return "GoodsVo [no=" + no + ", name=" + name + ", seillingPrice=" + seillingPrice + ", detail=" + detail
				+ ", viewdetail=" + viewdetail + ", displayStatus=" + displayStatus + ", seillingStatus="
				+ seillingStatus + ", regdate=" + regdate + ", material=" + material + ", supplier=" + supplier
				+ ", manufacturer=" + manufacturer + ", manufacturingDate=" + manufacturingDate + ", origin=" + origin
				+ ", smallcategoryNo=" + smallcategoryNo + ", goodsImagesList=" + goodsImagesList + ", goodsDetailList="
				+ goodsDetailList + ", thumbnail=" + thumbnail + ", bigcategoryName=" + bigcategoryName
				+ ", smallcategoryName=" + smallcategoryName + ", optionListTxt=" + optionListTxt + "]";
	}
	
	
	
	
}
