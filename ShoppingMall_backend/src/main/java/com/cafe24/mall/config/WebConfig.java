package com.cafe24.mall.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.cafe24.config.web.MVCConfig;
import com.cafe24.config.web.MessageConfig;
import com.cafe24.config.web.MyZipkinProperties;
import com.cafe24.config.web.SecurityConfig;
import com.cafe24.config.web.SwaggerConfig;
import com.cafe24.config.web.ZipkinConfig;

@Configuration
@EnableAspectJAutoProxy
@EnableWebMvc
@ComponentScan({"com.cafe24.mall.controller","com.cafe24.mall.exception"})
@EnableConfigurationProperties(MyZipkinProperties.class)
@Import({ 
	MVCConfig.class, 
	SecurityConfig.class, 
	MessageConfig.class, 
	SwaggerConfig.class,
	//ZipkinConfig.class   
})
public class WebConfig{
	
}
