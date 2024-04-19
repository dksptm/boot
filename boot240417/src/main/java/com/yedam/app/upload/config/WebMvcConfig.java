package com.yedam.app.upload.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
	// 이 클래스가 할수있는 것들..
	// 인터셉터 등록
	// 뷰리졸버 등록
	// 리소스 핸들링
	
	// 리소스 핸들링.
//	@Override
//	public void addResourceHandlers(ResourceHandlerRegistry registry) {
//		registry.addResourceHandler("/images/**") // 브라우저 핸들러 접근하는 경로. (http://localhost:8080/images/dog33.jpg)
//				.addResourceLocations("file:///C:/upload/", ""); // 실제 물리적 위치 (동일한 경로에 여러개의 위치 매핑 가능)
//		WebMvcConfigurer.super.addResourceHandlers(registry); 
//	}
	
	// 만약 운영체제가 다르다면? 우분투는 드라이버(c,d 드라이버)라는 개념자체가 없다.
	@Value("${file.upload.path}")
	private String uploadPath;
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/images/**") 
				.addResourceLocations("file:///" + uploadPath, "");
		WebMvcConfigurer.super.addResourceHandlers(registry); 
	}
	

}
