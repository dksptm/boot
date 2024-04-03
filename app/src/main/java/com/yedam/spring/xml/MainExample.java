package com.yedam.spring.xml;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class MainExample {
	
	public static void main(String[] args) {
		
		// 1. 컨테이너 생성.
		ApplicationContext ctx
		= new GenericXmlApplicationContext("classpath:applicationContext.xml");
		
		// 3. bean 반환.-> 사실은 참조주소를 가져오는것.
		// getBean이 반환하는 것이 Object타입이라서 형변환이 필요하다.
		// (getbean에 어떤 클래스타입이 올지 알수없으니깐)
		Restaurant res = (Restaurant)ctx.getBean(Restaurant.class); // 이름말고 클래스로 불러오기.
		
		// 싱글톤확인.
		Restaurant res2 = (Restaurant)ctx.getBean(Restaurant.class);
		if(res == res2) {
			System.out.println("같은대상");
		} else {
			System.out.println("다른 대상");
		}
		
		// 4. 레스토랑 bean도 가져오지만, chef라는 것도 가져옴.
		res.run();
	}

}
