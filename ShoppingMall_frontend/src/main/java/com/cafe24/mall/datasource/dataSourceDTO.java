package com.cafe24.mall.datasource;

public class dataSourceDTO {
	private cafe24smsDTO cafe24sms;
	private naversmtpDTO naversmtp;

	public cafe24smsDTO getCafe24sms() {
		return cafe24sms;
	}

	public void setCafe24sms(cafe24smsDTO cafe24sms) {
		this.cafe24sms = cafe24sms;
	}

	public naversmtpDTO getNaversmtp() {
		return naversmtp;
	}

	public void setNaversmtp(naversmtpDTO naversmtp) {
		this.naversmtp = naversmtp;
	}

	public static class cafe24smsDTO {
		private String sms_url;
		private String user_id;
		private String secure;
		private String sender_phone1;
		private String sender_phone2;
		private String sender_phone3;

		public String getSms_url() {
			return sms_url;
		}

		public void setSms_url(String sms_url) {
			this.sms_url = sms_url;
		}

		public String getUser_id() {
			return user_id;
		}

		public void setUser_id(String user_id) {
			this.user_id = user_id;
		}

		public String getSecure() {
			return secure;
		}

		public void setSecure(String secure) {
			this.secure = secure;
		}

		public String getSender_phone1() {
			return sender_phone1;
		}

		public void setSender_phone1(String sender_phone1) {
			this.sender_phone1 = sender_phone1;
		}

		public String getSender_phone2() {
			return sender_phone2;
		}

		public void setSender_phone2(String sender_phone2) {
			this.sender_phone2 = sender_phone2;
		}

		public String getSender_phone3() {
			return sender_phone3;
		}

		public void setSender_phone3(String sender_phone3) {
			this.sender_phone3 = sender_phone3;
		}

	}

	public static class naversmtpDTO {
		private String host;
		private String port;
		private String from;
		private String starttlsEnable;
		private String auth;
		private String debug;
		private String socketFactoryPort;
		private String socketFactoryClass;
		private String socketFactoryFallback;
		private String senderEmail;
		private String senderEmailPassword;
		private String encoding;

		public String getEncoding() {
			return encoding;
		}

		public void setEncoding(String encoding) {
			this.encoding = encoding;
		}

		public String getHost() {
			return host;
		}

		public void setHost(String host) {
			this.host = host;
		}

		public String getPort() {
			return port;
		}

		public void setPort(String port) {
			this.port = port;
		}

		public String getFrom() {
			return from;
		}

		public void setFrom(String from) {
			this.from = from;
		}

		public String getStarttlsEnable() {
			return starttlsEnable;
		}

		public void setStarttlsEnable(String starttlsEnable) {
			this.starttlsEnable = starttlsEnable;
		}

		public String getAuth() {
			return auth;
		}

		public void setAuth(String auth) {
			this.auth = auth;
		}

		public String getDebug() {
			return debug;
		}

		public void setDebug(String debug) {
			this.debug = debug;
		}

		public String getSocketFactoryPort() {
			return socketFactoryPort;
		}

		public void setSocketFactoryPort(String socketFactoryPort) {
			this.socketFactoryPort = socketFactoryPort;
		}

		public String getSocketFactoryClass() {
			return socketFactoryClass;
		}

		public void setSocketFactoryClass(String socketFactoryClass) {
			this.socketFactoryClass = socketFactoryClass;
		}

		public String getSocketFactoryFallback() {
			return socketFactoryFallback;
		}

		public void setSocketFactoryFallback(String socketFactoryFallback) {
			this.socketFactoryFallback = socketFactoryFallback;
		}

		public String getSenderEmail() {
			return senderEmail;
		}

		public void setSenderEmail(String senderEmail) {
			this.senderEmail = senderEmail;
		}

		public String getSenderEmailPassword() {
			return senderEmailPassword;
		}

		public void setSenderEmailPassword(String senderEmailPassword) {
			this.senderEmailPassword = senderEmailPassword;
		}

	}

}
