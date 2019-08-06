package com.cafe24.mall.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;

import com.cafe24.config.app.AppSecurityConfig;
import com.cafe24.config.web.FileuploadConfig;
import com.cafe24.config.web.MVCConfig;
import com.cafe24.config.web.MessageConfig;
import com.cafe24.config.web.RestTemplateConfig;


@Configuration
@EnableAspectJAutoProxy
@ComponentScan({"com.cafe24.mall.service","com.cafe24.mall.provider","com.cafe24.mall.aspect",
	"com.cafe24.mall.security"})
@Import({RestTemplateConfig.class, AppSecurityConfig.class})
public class AppConfig {	
}
