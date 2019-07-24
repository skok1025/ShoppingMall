package com.cafe24.mall.dto;

import com.cafe24.mall.vo.ChangeApplyVo;

public class ChangeApplyDTO {

	private Long no;
	private String orderCode;
	private String thumbnail;
	private String goodsName;
	private String optionName;
	public String getOptionName() {
		return optionName;
	}
	public void setOptionName(String optionName) {
		this.optionName = optionName;
	}
	private String changeOptionName;
	private int changeCnt;
	private ChangeApplyVo.status status;
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public String getOrderCode() {
		return orderCode;
	}
	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
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
	public String getChangeOptionName() {
		return changeOptionName;
	}
	public void setChangeOptionName(String changeOptionName) {
		this.changeOptionName = changeOptionName;
	}
	public int getChangeCnt() {
		return changeCnt;
	}
	public void setChangeCnt(int changeCnt) {
		this.changeCnt = changeCnt;
	}
	public ChangeApplyVo.status getStatus() {
		return status;
	}
	public void setStatus(ChangeApplyVo.status status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "ChangeApplyDTO [no=" + no + ", orderCode=" + orderCode + ", thumbnail=" + thumbnail + ", goodsName="
				+ goodsName + ", optionName=" + optionName + ", changeOptionName=" + changeOptionName + ", changeCnt="
				+ changeCnt + ", status=" + status + "]";
	}
	
	
	
}
