package com.cafe24.mall;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class}) // no database setting
public class BootApp {
	
	public static void main(String[] args) {
		SpringApplication.run(BootApp.class,args);
	}

}
