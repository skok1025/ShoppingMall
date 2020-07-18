package com.cafe24.mall.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe24.mall.repository.ScheduleDao;

@Service
public class ScheduleService {

	@Autowired
	private ScheduleDao scheduleDao;

	public void updateDormancy() {
		scheduleDao.updateDormancy();
	}
	
}
