package com.cafe24.config.web;

import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;

@Configuration
public class MVCConfig extends WebMvcConfigurerAdapter{
	//
	// View Resolver
	//	
	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();

		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		resolver.setExposeContextBeansAsAttributes(true);

		return resolver;
	}

	//
	// Defalut Servlet Handler
	//
	
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		//configurer.enable();
	}
	
	
	
	
	
	//
	// Message Converter
	//
	@Bean
	public MappingJackson2HttpMessageConverter mappingConverter() {
		Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder()
				.indentOutput(true)
				.dateFormat(new SimpleDateFormat("yyyy-MM-dd"))
				.modulesToInstall(new ParameterNamesModule());
		
		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter(builder.build());
		converter.setSupportedMediaTypes(Arrays.asList(new MediaType("application","json",Charset.forName("UTF-8"))));
		
		
		return converter;
	}
	
	public StringHttpMessageConverter stringConverter() {
		StringHttpMessageConverter converter = new StringHttpMessageConverter();
		converter.setSupportedMediaTypes(Arrays.asList(new MediaType("text","html",Charset.forName("UTF-8"))));
		return converter;
	}
	
	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		
		converters.add(mappingConverter());
		converters.add(stringConverter());
	}
	
	
	
	
}
