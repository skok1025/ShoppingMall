package com.cafe24.mall.vo;

public class CancelApplyVo {
	public enum status{
		y,n
	}
	
	private Long no;
	private Long orderNo;
	private Long goodsDetailNo;
	private int cancelCnt;
	private String cancelReason;
	private String refundAccount;
	private CancelApplyVo.status status;
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
	public int getCancelCnt() {
		return cancelCnt;
	}
	public void setCancelCnt(int cancelCnt) {
		this.cancelCnt = cancelCnt;
	}
	public String getCancelReason() {
		return cancelReason;
	}
	public void setCancelReason(String cancelReason) {
		this.cancelReason = cancelReason;
	}
	public String getRefundAccount() {
		return refundAccount;
	}
	public void setRefundAccount(String refundAccount) {
		this.refundAccount = refundAccount;
	}
	public CancelApplyVo.status getStatus() {
		return status;
	}
	public void setStatus(CancelApplyVo.status status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "CancelApplyVo [no=" + no + ", orderNo=" + orderNo + ", goodsDetailNo=" + goodsDetailNo + ", cancelCnt="
				+ cancelCnt + ", cancelReason=" + cancelReason + ", refundAccount=" + refundAccount + ", status="
				+ status + "]";
	}
	
	
	
}
