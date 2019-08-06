package com.cafe24.mall.security;

import java.security.MessageDigest;

import org.springframework.security.crypto.password.PasswordEncoder;

public class CustomPasswordEncoder implements PasswordEncoder {
	@Override
	public String encode(CharSequence rawPassword) {
		String pwd = rawPassword.toString();
		StringBuilder sb = new StringBuilder("");

		try {
			MessageDigest md = MessageDigest.getInstance("SHA-512");
			md.reset();
			byte[] bytes = md.digest(pwd.getBytes("UTF-8"));

			for(int i = 0; i < bytes.length; i++) {
				String hexString = Integer.toHexString(bytes[i] & 0xff);
				while(hexString.length() < 2) {
					hexString = "0" + hexString;
				}
				sb.append(hexString);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}

		return sb.toString();
	}

	@Override
	public boolean matches(CharSequence rawPassword, String encodedPassword) {
		return encodedPassword.equals(encode(rawPassword.toString()));
	}
}