package com.cafe24.mall.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cafe24.mall.service.ScheduleService;

@Controller
public class ScheduleController {

	@Autowired
	private ScheduleService scheduleService;
	
	@Scheduled(cron="* * * * * ?") // Every seconds
	//@Scheduled(cron="*/10 * * * * ?") //Every 10 seconds
	//@Scheduled(cron="0 */2 14 * * ?") // Every 2 minute (14:00:00 ~ 15:00:00)
	//@Scheduled(cron="0 13 14 * * ?") //Every 14:13:00
	public void doingCronTest() {
		String nowTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
	//System.out.println(nowTime);
	}
	
	/**
	 * 1년이상 된 계정 휴면계정처리
	 */
	@Scheduled(cron="0 0 0 * * ?") //Every 00:00:00
	public void updateDormancy() {
		scheduleService.updateDormancy();
	}

}
