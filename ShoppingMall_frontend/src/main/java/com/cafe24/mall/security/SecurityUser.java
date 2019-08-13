package com.cafe24.mall.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class SecurityUser implements UserDetails{
   private static final long serialVersionUID = 1L;
   
   //security fields
   private Collection<? extends GrantedAuthority> authorities;
   private String username;// email (Principal) biz name
   private String password;// credential
   
   //domain fields(principal, 보호할 사용자 중요 데이터)
   private Long no;
   private String name; // 성명 domain data
   private String birthDate;
   private String gender;
   private String id;
   private String email;
   private String tel;
   private String regdate;
   
   
   private String basketCode; // 비회원일때 장바구니 코드
   
   
   public String getBasketCode() {
	return basketCode;
}

public void setBasketCode(String basketCode) {
	this.basketCode = basketCode;
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

   public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
      this.authorities = authorities;
   }

   public void setUsername(String username) {
      this.username = username;
   }

   public void setPassword(String password) {
      this.password = password;
   }

   @Override
   public Collection<? extends GrantedAuthority> getAuthorities() {
      return authorities;
   }

   @Override
   public String getPassword() {
      return password;
   }

   @Override
   public String getUsername() {
      return username;
   }

   @Override
   public boolean isAccountNonExpired() {
      return true;
   }

   @Override
   public boolean isAccountNonLocked() {
      return true;
   }

   @Override
   public boolean isCredentialsNonExpired() {
      return true;
   }

   @Override
   public boolean isEnabled() {
      return true;
   }

}