package com.cafe24.mall.vo;

import java.util.List;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

public class MemberVo {
	private Long no;
	@NotEmpty
	@Length(max = 10, min = 2)
	private String name;
	private String address;
	private String birthDate;
	private String gender;
	
	@NotEmpty
	@Pattern(regexp = "^[a-zA-Z0-9]{4,18}$")
	private String id;
	
	@NotEmpty
	//@Pattern(regexp = "(?=.*\\d{1,50})(?=.*[~`!@#$%\\^&*()-+=]{1,50})(?=.*[a-zA-Z]{2,50}).{8,50}$", message = "숫자, 특문 각 1회 이상, 영문은 2개 이상 사용하여 8자리 이상 입력")
	private String password;
	
	@Email
	private String email;
	private String tel;
	private String regdate;
	
	private List<MemberTermsVo> termlist; // 약관동의 리스트
	
	private String newPw;
	private String confirmPw;
	
	
	private String basketCode; // 로그인 시, 쿠키에 있는 장바구니 코드
	
	
	public String getBasketCode() {
		return basketCode;
	}

	public void setBasketCode(String basketCode) {
		this.basketCode = basketCode;
	}

	public String getNewPw() {
		return newPw;
	}

	public void setNewPw(String newPw) {
		this.newPw = newPw;
	}

	public String getConfirmPw() {
		return confirmPw;
	}

	public void setConfirmPw(String confirmPw) {
		this.confirmPw = confirmPw;
	}

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
				+ ", regdate=" + regdate + ", termlist=" + termlist + ", newPw=" + newPw + ", confirmPw=" + confirmPw
				+ ", basketCode=" + basketCode + "]";
	}
	
	

}
