package com.cafe24.mall.vo;

public class ChangeApplyVo {
	
	public enum status{
		y,n
	}
	
	private Long no;
	private Long orderNo;
	private Long goodsDetailNo;
	private Long changeGoodsDetailNo;
	private int changeCnt;
	private ChangeApplyVo.status status;
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
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
	public Long getChangeGoodsDetailNo() {
		return changeGoodsDetailNo;
	}
	public void setChangeGoodsDetailNo(Long changeGoodsDetailNo) {
		this.changeGoodsDetailNo = changeGoodsDetailNo;
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
		return "ChangeApplyVo [no=" + no + ", orderNo=" + orderNo + ", goodsDetailNo=" + goodsDetailNo
				+ ", changeGoodsDetailNo=" + changeGoodsDetailNo + ", changeCnt=" + changeCnt + ", status=" + status
				+ "]";
	}
	
	

}
