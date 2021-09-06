package com.cafe24.mall.service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe24.mall.datasource.DataSource;
import com.cafe24.mall.provider.CustomerProvider;
import com.cafe24.mall.util.SMSCafe24Service;
import com.cafe24.mall.vo.MemberVo;

@Service
public class CustomerService {

	@Autowired
	private SMSCafe24Service smsService;
	
	@Autowired
	private EmailService emailService;

	@Autowired
	private CustomerProvider customerProvider;

	/**
	 * 가입 축하 sms 메세지 전송
	 * 
	 * @param memberVo 가입 멤버 vo
	 */
	public void sendJoinSms(MemberVo memberVo) {
		String message = memberVo.getName() + "(" + memberVo.getId() + ") 님  [SK Mall] 회원가입을 축하드립니다~~~. ";

		try {
			smsService.cafe24SMSService(message, memberVo.getTel(), null, null, null, null, null, null, null, null,
					null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return;
	}

	public Integer checkid(String id) {
		return customerProvider.checkid(id);
	}

	public Integer joinMember(MemberVo memberVo) {
		return customerProvider.insertMember(memberVo);
	}

	/**
	 * 인증코드를 해당 메일에 전송
	 * @param to 받는 사람 메일
	 * @return
	 */
	public Object sendEmailAuthCode(String to) {
		Random rand = new Random();

		String code = (rand.nextInt(90000) + 10000) + "";
		String subject = "SK mall 인증번호입니다.";
		String content = "인증번호 :" + code;
		String from = DataSource.data.getNaversmtp().getFrom();

		return emailService.sendEmail(from, to, subject, content) ? code : false;
	}

}
