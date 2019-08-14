package com.cafe24.config.web;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.sleuth.annotation.SleuthAnnotationProperties;
import org.springframework.cloud.sleuth.autoconfig.TraceAutoConfiguration;
import org.springframework.cloud.sleuth.zipkin2.ZipkinProperties;
import org.springframework.cloud.sleuth.zipkin2.sender.ZipkinSenderConfigurationImportSelector;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;


@ConditionalOnProperty(value = { "spring.sleuth.enabled", "spring.zipkin.enabled" }, matchIfMissing = true)
@AutoConfigureBefore(TraceAutoConfiguration.class)
@AutoConfigureAfter(
		name = "org.springframework.cloud.autoconfigure.RefreshAutoConfiguration")
@Import({ ZipkinSenderConfigurationImportSelector.class/* , SamplerAutoConfiguration.class */})
@Configuration
@EnableConfigurationProperties(MyZipkinProperties.class)
//@PropertySource("classpath:com/cafe24/config/web/properties/zipkin.properties")
public class ZipkinConfig {
	
	
	@Bean
	public ZipkinProperties zipkin() {
		ZipkinProperties zipkinProperties = new ZipkinProperties();
		//zipkinProperties.setBaseUrl(env.getProperty("spring.zipkin.baseUrl"));
		zipkinProperties.setBaseUrl("http://192.168.1.46:9411/zipkin/");
		
		return zipkinProperties;
	}
	
	@Bean
	public SleuthAnnotationProperties sleuth() {
		SleuthAnnotationProperties slethProperties = new SleuthAnnotationProperties();
		slethProperties.setEnabled(true);
		
		return slethProperties;
	}
	
	
}
