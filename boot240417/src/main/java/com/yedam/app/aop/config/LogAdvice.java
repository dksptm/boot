package com.yedam.app.aop.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect // advice와 pointcut 결합하는 정보 정의.
@Component
public class LogAdvice {
	
	// 포인트컷 : 비즈니스 로직과 관련된 메소드 중에서 Advice(공통코드)가 적용될 메소드.
	@Pointcut("within(com.yedam.app.emp.service.impl.*)")
	public void allPointCut() {
		/**
		 * within(com.yedam.app.emp.service.impl.*) 은 단순한 표현식
		 * 이를 불러내는 것이 public void allPointCut() 메소드.
		 * 메소드가 이 포인트컷의 이름이 되어주는 것.
		 * **/
		
	}
	
	// Weaving : 특정 포인트컷 + Advice + 타이밍
	@Around("allPointCut()")
	public Object logger(ProceedingJoinPoint joinPoint) throws Throwable {
		// ProceedingJoinPoint : 지금 실행하고 있는 비지니스 로직을 전달받음(메소드에 대한 전체정보)
		// 지금 실행을 요청받은 메소드정보 자체를 매개변수로 전달받음.
		
		// Aop가 적용되는 메서드의 이름
		String signatuerStr = joinPoint.getSignature().toString();
		System.out.println("시작 : " + signatuerStr);
		
		// 공통기능
		System.out.println("핵심 기능 전 실행 - 공통기능 : " + System.currentTimeMillis()); //시작시간
		
		try {
			// 비지니스 메소드를 실행.
			System.out.println("서비스 메소드시작");
			Object obj = joinPoint.proceed(); // 실제로 비지니스 로직 실행하는 코드.(언제쯤 실제 메소드기능을 실행할지 알려주고있음)
			System.out.println("서비스 메소드끝");
			return obj;
		} finally { // return obj; 로 끝나는 동시에 실행되어야 하는것을 finally로...
			System.out.println("핵심 기능 후 실행 - 공통기능 : " + System.currentTimeMillis()); //끝시간
			System.out.println("끝 : " + signatuerStr);
		}
	}
	
	// 실제로 트랜잭션에 적용.
	// 로그도 aop로.
	
	@Before("allPointCut()")
	public void beforeAdvice(JoinPoint joinPoint) { // ProceedingJoinPoint : @Around 에서만.(proceed() 사용가능)
		String signatuerStr = joinPoint.getSignature().toString();
		System.out.println("시작 : " + signatuerStr);
	}
	
	@After("allPointCut()")
	public void afterAdvice(JoinPoint joinPoint) { // ProceedingJoinPoint : @Around 에서만.(proceed() 사용가능)
		String signatuerStr = joinPoint.getSignature().toString();
		System.out.println("끝 : " + signatuerStr);
	}
	
	

}
