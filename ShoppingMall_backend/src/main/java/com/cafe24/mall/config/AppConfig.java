package com.cafe24.mall.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;

import com.cafe24.config.app.DBConfig;
import com.cafe24.config.app.MyBatisConfig;

@Configuration
@EnableAspectJAutoProxy
@ComponentScan({"com.cafe24.mall.service","com.cafe24.mall.repository","com.cafe24.mall.aspect"})
@Import({ 
	DBConfig.class, 
	MyBatisConfig.class, 
	//AuthorizationServerConfig.class, 
	//ResourceServerConfig.class 
 })
public class AppConfig {	
}
