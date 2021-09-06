package com.cafe24.mall.service;

import java.util.Properties;
import java.util.Random;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Service;

import com.cafe24.mall.datasource.DataSource;
import com.cafe24.mall.util.SMTPAuthenticatior;

@Service
public class EmailService {

	/**
	 * 이메일 전송
	 * @param from 보내는 사람
	 * @param to 받는 사람
	 * @param subject 제목
	 * @param content 내용
	 * @return 메일 전송 여부
	 */
	public boolean sendEmail(String from, String to, String subject, String content) {		
		Properties p = new Properties(); // 정보를 담을 객체
		 
		p.put("mail.smtp.host",DataSource.data.getNaversmtp().getHost()); // 네이버 SMTP
		 
		p.put("mail.smtp.port", DataSource.data.getNaversmtp().getPort());
		p.put("mail.smtp.starttls.enable", DataSource.data.getNaversmtp().getStarttlsEnable());
		p.put("mail.smtp.auth", DataSource.data.getNaversmtp().getAuth());
		p.put("mail.smtp.debug", DataSource.data.getNaversmtp().getDebug());
		p.put("mail.smtp.socketFactory.port", DataSource.data.getNaversmtp().getSocketFactoryPort());
		p.put("mail.smtp.socketFactory.class", DataSource.data.getNaversmtp().getSocketFactoryClass());
		p.put("mail.smtp.socketFactory.fallback", DataSource.data.getNaversmtp().getSocketFactoryFallback());
		// SMTP 서버에 접속하기 위한 정보들
		
		try{
		    Authenticator auth = new SMTPAuthenticatior();
		    Session ses = Session.getInstance(p, auth);
		     
		    ses.setDebug(true);
		     
		    MimeMessage msg = new MimeMessage(ses); // 메일의 내용을 담을 객체
		    msg.setSubject(subject); // 제목
		     
		    Address fromAddr = new InternetAddress(from);
		    msg.setFrom(fromAddr); // 보내는 사람
		     
		    Address toAddr = new InternetAddress(to);
		    msg.addRecipient(Message.RecipientType.TO, toAddr); // 받는 사람
		     
		    msg.setContent(content, DataSource.data.getNaversmtp().getEncoding()); // 내용과 인코딩
		     
		    Transport.send(msg); // 전송
		    
		} catch(Exception e){
		    e.printStackTrace();
		    return false;
		}
		
		return true;
	}

	
}
