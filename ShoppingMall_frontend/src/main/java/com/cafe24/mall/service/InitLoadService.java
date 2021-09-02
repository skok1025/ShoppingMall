package com.cafe24.mall.service;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.cafe24.mall.datasource.DataSource;

@Component
public class InitLoadService implements ApplicationListener<ContextRefreshedEvent>{

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		DataSource.onLoad(); // dataSource.yml 데이터 초기 로드
	}

}
