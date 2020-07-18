package com.cafe24.mall.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ScheduleController {

	@Scheduled(cron="* * * * * ?") // Every seconds
	//@Scheduled(cron="*/10 * * * * ?") //Every 10 seconds
	//@Scheduled(cron="0 */2 14 * * ?") // Every 2 minute (14:00:00 ~ 15:00:00)
	//@Scheduled(cron="0 13 14 * * ?") //Every 14:13:00
	public void doingCronTest() {
		String nowTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
		System.out.println(nowTime);
	}

}
