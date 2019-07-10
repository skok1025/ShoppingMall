package com.cafe24.config.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebMvc
@PropertySource("classpath:com/cafe24/web/properties/multipart.properties")
public class FileuploadConfig  extends WebMvcConfigurerAdapter{

	@Autowired
	private Environment env;
	
	public CommonsMultipartResolver multipartResolver() {
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
		multipartResolver.setMaxUploadSize(env.getProperty("maxUploadSize",Long.class));
		multipartResolver.setMaxInMemorySize(env.getProperty("maxInMemorySize",Integer.class));
		multipartResolver.setDefaultEncoding(env.getProperty("defaultEncoding"));
		
		return multipartResolver;
	}
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/images/**").addResourceLocations("file:/mall-uploads/");
	}

	
	
}
