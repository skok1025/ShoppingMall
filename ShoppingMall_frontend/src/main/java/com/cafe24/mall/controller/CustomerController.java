package com.cafe24.mall.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import javax.mail.Transport;
import javax.mail.Message;

import java.util.Properties;
import java.util.Random;

import javax.mail.Address;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.Session;
import javax.mail.Authenticator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cafe24.mall.datasource.DataSource;
import com.cafe24.mall.service.CustomerService;
import com.cafe24.mall.util.SMSCafe24Service;
import com.cafe24.mall.util.SMTPAuthenticatior;
import com.cafe24.mall.vo.MemberVo;

@Controller
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private SMSCafe24Service smsService;
	
	
	
	@GetMapping("/join")
	public String join(@ModelAttribute("memberVo") MemberVo memberVo) {
		return "index/join";
	}
	
	@PostMapping("/join")
	public String join(
			@ModelAttribute("memberVo") @Valid MemberVo memberVo,
			BindingResult bindingResult,
			Model model
			) {
		
		if(bindingResult.hasErrors()) {
			model.addAttribute(bindingResult.getModel());
			return "index/join";
		}
		
		Integer result = customerService.joinMember(memberVo);
		
		String message = memberVo.getName()+"("+memberVo.getId()+") 님  [SK Mall] 회원가입을 축하드립니다~~~. ";
		
		try {
			smsService.cafe24SMSService(message, memberVo.getTel(), null, null, null, null, null, null, null, null, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(result == 1) {
			return "redirect:/login?joinsuccess=yes";
		}
		
		// 실패한 경우
		model.addAttribute("joinfail", "yes");
		return "index/join";

	}
	
	@ResponseBody	
	@GetMapping("/checkid")
	public Integer checkid(@RequestParam String id) {
		return customerService.checkid(id);
	}
	
	@ResponseBody
	@PostMapping("/checkemail")
	public String checkEmail(String to) {
		Random rand = new Random();
		
		String code = (rand.nextInt(90000)+10000)+"";
		
		String from = "skok1025@naver.com";
		//String to = req.getParameter("to");
		String subject = "SK mall 인증번호입니다.";
		String content = "인증번호 :"+code;
		
		Properties p = new Properties(); // 정보를 담을 객체
		 
		DataSource.onLoad();
		
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
		     
		    msg.setContent(content, "text/html;charset=UTF-8"); // 내용과 인코딩
		     
		    Transport.send(msg); // 전송
		    
		} catch(Exception e){
		    e.printStackTrace();
		    // 오류 발생시 뒤로 돌아가도록
		    return "error";
		}
		
		return code;
	}
	

	
	
	
}
