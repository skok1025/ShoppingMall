package com.cafe24.mall.vo;

import java.util.List;

public class MemberVo {
	private Long no;
	private String name;
	private String address;
	private String birthDate;
	private String gender;
	private String id;
	private String password;
	private String email;
	private String tel;
	private String regdate;
	
	private List<MemberTermsVo> termlist; // 약관동의 리스트
	
	
	public MemberVo() {
		
	}
	
	// 테스트 용
	public MemberVo(String id, String password) {
		this.id = id;
		this.password = password;
	}
	
	
	public List<MemberTermsVo> getTermlist() {
		return termlist;
	}
	public void setTermlist(List<MemberTermsVo> termlist) {
		this.termlist = termlist;
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	@Override
	public String toString() {
		return "MemberVo [no=" + no + ", name=" + name + ", address=" + address + ", birthDate=" + birthDate
				+ ", gender=" + gender + ", id=" + id + ", password=" + password + ", email=" + email + ", tel=" + tel
				+ ", regdate=" + regdate + ", termlist=" + termlist + "]";
	}
	
	

}
