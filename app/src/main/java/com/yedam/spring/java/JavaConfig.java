package com.yedam.spring.java;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JavaConfig {
	
	@Bean(name = "chf")
	public Chef chef() {
		// 보통 이 클래스가 가져야하는 정보를 계속 추가하고 최종형태를 추가하는 방식을 취함.
		return new Chef();
	}
	
	@Bean
	public Restaurant restaurant(Chef chef) {
		Restaurant res = new Restaurant();
		res.setChef(chef);
		return res;
	}
	
}
