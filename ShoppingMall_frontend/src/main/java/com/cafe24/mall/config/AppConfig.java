package com.cafe24.mall.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;


@Configuration
@EnableAspectJAutoProxy
@ComponentScan({"com.cafe24.mall.service","com.cafe24.mall.provider","com.cafe24.mall.aspect"})
public class AppConfig {	
}
