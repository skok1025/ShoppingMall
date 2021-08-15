package com.cafe24.mall.vo;

import java.util.List;

public class OrderVo {
	public enum paymentStatus{
		y,n
	}
	
	public enum orderStatus{
		배송준비중, 배송보류, 배송대기, 배송중, 부분배송완료, 배송완료
	}
	
	private Long no; 
	private String code;
	private Long memberNo;
	private String orderName;
	private String orderTel;
	private String password;
	private OrderVo.paymentStatus paymentStatus;
	private OrderVo.orderStatus orderStatus;
	private String paymentWay;
	private String receiverName;
	private String receiverTel1;
	private String receiverTel2;
	private String receiverPostcode;
	private String receiverAddress;
	private String message;
	private String invoiceCode;
	
	private String addr1;
	private String addr2;
	
	private String orderPrice;
	private String orderCalcInfo;
	private String applyCouponNo;
	
	
	
	public String getApplyCouponNo() {
		return applyCouponNo;
	}

	public void setApplyCouponNo(String applyCouponNo) {
		this.applyCouponNo = applyCouponNo;
	}

	public String getOrderPrice() {
		return orderPrice;
	}

	public void setOrderPrice(String orderPrice) {
		this.orderPrice = orderPrice;
	}

	public String getOrderCalcInfo() {
		return orderCalcInfo;
	}

	public void setOrderCalcInfo(String orderCalcInfo) {
		this.orderCalcInfo = orderCalcInfo;
	}

	public String getAddr1() {
		return addr1;
	}

	public void setAddr1(String addr1) {
		this.addr1 = addr1;
	}

	public String getAddr2() {
		return addr2;
	}

	public void setAddr2(String addr2) {
		this.addr2 = addr2;
	}

	private List<OrderGoodsVo> orderGoodsList;

	public Long getNo() {
		return no;
	}

	public void setNo(Long no) {
		this.no = no;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Long getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(Long memberNo) {
		this.memberNo = memberNo;
	}

	public String getOrderName() {
		return orderName;
	}

	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}

	public String getOrderTel() {
		return orderTel;
	}

	public void setOrderTel(String orderTel) {
		this.orderTel = orderTel;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public OrderVo.paymentStatus getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(OrderVo.paymentStatus paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public OrderVo.orderStatus getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(OrderVo.orderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getPaymentWay() {
		return paymentWay;
	}

	public void setPaymentWay(String paymentWay) {
		this.paymentWay = paymentWay;
	}

	public String getReceiverName() {
		return receiverName;
	}

	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}

	public String getReceiverTel1() {
		return receiverTel1;
	}

	public void setReceiverTel1(String receiverTel1) {
		this.receiverTel1 = receiverTel1;
	}

	public String getReceiverTel2() {
		return receiverTel2;
	}

	public void setReceiverTel2(String receiverTel2) {
		this.receiverTel2 = receiverTel2;
	}

	public String getReceiverPostcode() {
		return receiverPostcode;
	}

	public void setReceiverPostcode(String receiverPostcode) {
		this.receiverPostcode = receiverPostcode;
	}

	public String getReceiverAddress() {
		return receiverAddress;
	}

	public void setReceiverAddress(String receiverAddress) {
		this.receiverAddress = receiverAddress;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getInvoiceCode() {
		return invoiceCode;
	}

	public void setInvoiceCode(String invoiceCode) {
		this.invoiceCode = invoiceCode;
	}

	public List<OrderGoodsVo> getOrderGoodsList() {
		return orderGoodsList;
	}

	public void setOrderGoodsList(List<OrderGoodsVo> orderGoodsList) {
		this.orderGoodsList = orderGoodsList;
	}

	@Override
	public String toString() {
		return "OrderVo [no=" + no + ", code=" + code + ", memberNo=" + memberNo + ", orderName=" + orderName
				+ ", orderTel=" + orderTel + ", password=" + password + ", paymentStatus=" + paymentStatus
				+ ", orderStatus=" + orderStatus + ", paymentWay=" + paymentWay + ", receiverName=" + receiverName
				+ ", receiverTel1=" + receiverTel1 + ", receiverTel2=" + receiverTel2 + ", receiverPostcode="
				+ receiverPostcode + ", receiverAddress=" + receiverAddress + ", message=" + message + ", invoiceCode="
				+ invoiceCode + ", addr1=" + addr1 + ", addr2=" + addr2 + ", orderPrice=" + orderPrice
				+ ", orderCalcInfo=" + orderCalcInfo + ", applyCouponNo=" + applyCouponNo + ", orderGoodsList="
				+ orderGoodsList + "]";
	}

	
	
}
