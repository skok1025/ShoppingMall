package com.cafe24.mall.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.cafe24.config.web.FileuploadConfig;
import com.cafe24.config.web.MVCConfig;
import com.cafe24.config.web.MessageConfig;
import com.cafe24.config.web.RestTemplateConfig;

@Configuration
@EnableAspectJAutoProxy
@EnableWebMvc
@ComponentScan({ "com.cafe24.mall.controller" })
@Import({MVCConfig.class, MessageConfig.class, FileuploadConfig.class})
public class WebConfig{
	
}
