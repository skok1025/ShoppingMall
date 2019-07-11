package com.cafe24.mall.vo;

public class GoodsVo {

	
	private Long no;
	private String name;
	private int seillingPrice;
	private String detail;
	private String displayStatus;
	private String seillingStatus;
	private String regdate;
	private String material;
	private String supplier;
	private String manufacturer;
	private String manufacturingDate;
	private String origin;
	private Long smallcategoryNo;
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
	public String getDisplayStatus() {
		return displayStatus;
	}
	public void setDisplayStatus(String displayStatus) {
		this.displayStatus = displayStatus;
	}
	public String getSeillingStatus() {
		return seillingStatus;
	}
	public void setSeillingStatus(String seillingStatus) {
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
				+ ", displayStatus=" + displayStatus + ", seillingStatus=" + seillingStatus + ", regdate=" + regdate
				+ ", material=" + material + ", supplier=" + supplier + ", manufacturer=" + manufacturer
				+ ", manufacturingDate=" + manufacturingDate + ", origin=" + origin + ", smallcategoryNo="
				+ smallcategoryNo + "]";
	}
	
	
	
}
