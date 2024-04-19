package com.yedam.app.upload.config;

import javax.servlet.MultipartConfigElement;

import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.unit.DataSize;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;

@Configuration
public class MultipartConfig {
	
	// 메소드를 실행한 결과를 등록한다.
	@Bean
	public MultipartResolver multipartResolver() {
		return new StandardServletMultipartResolver();
	}
	
	// 어플리케이션 프로퍼타이즈로 설정못함.
	// 설정하는 빈과 실제동작하는 빈 필요.
	
	@Bean
	public MultipartConfigElement multipartConfigEElement() {
		MultipartConfigFactory factory = new MultipartConfigFactory();
		factory.setMaxRequestSize(DataSize.ofMegabytes(10)); // 전체파일 최대사이즈
		factory.setMaxFileSize(DataSize.ofMegabytes(1)); // 파일하나당 최대사이즈
		//factory.setMaxFileSize(DataSize.ofBytes(500)); // 파일하나당 최대사이즈
		return factory.createMultipartConfig();
	}

}
