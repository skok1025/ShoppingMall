package com.cafe24.mall.vo;

public class MemberTermsVo {
	private Long no;
	private Long memberNo;
	private Long termsNo;
	private String isagree;
	private String regdate;
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public Long getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(Long memberNo) {
		this.memberNo = memberNo;
	}
	public Long getTermsNo() {
		return termsNo;
	}
	public void setTermsNo(Long termsNo) {
		this.termsNo = termsNo;
	}
	public String getIsagree() {
		return isagree;
	}
	public void setIsagree(String isagree) {
		this.isagree = isagree;
	}
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	@Override
	public String toString() {
		return "MemberTermsVo [no=" + no + ", memberNo=" + memberNo + ", termsNo=" + termsNo + ", isagree=" + isagree
				+ ", regdate=" + regdate + "]";
	}
	
	
	

}
