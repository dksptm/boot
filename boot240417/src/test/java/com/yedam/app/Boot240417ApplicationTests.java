package com.yedam.app;

import org.jasypt.encryption.StringEncryptor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Boot240417ApplicationTests {

//	@Test
//	void contextLoads() {
//	}
	
//	@Autowired
//	AaaService aaaService;
//	
//	@Test
//	public void aopTest() {
//		aaaService.insert();
//	}
	
	// 암호화..
	
//	@Autowired
//	PasswordEncoder PasswordEncoder; // PasswordEncoder : 단방향 암호화. 복구불가.(비밀번호에 가장많이 사용)
	
	// 예) 주민번호는 양방향..
	
	// 내가 입력한 암호화된 비번과 DB에 등록한 암호화된 비번이 내부적으로 같은지 아닌지 확인해줌-> 매치 함수사용
	
//	@Test
//	public void testEncoder() {
//		// 사용자가 비번을 입력.
//		String password = "1234";
//		
//		// 실제 DB에 넣을때는 암호화.
//		String enPwd = PasswordEncoder.encode(password);
//		System.out.println("인코딩된 비번 " + enPwd);
//		
//		//$2a$10$7GmZLPyNRyKqytn67gwmM.Bd7sGFXhbzbtaV9rxdKauNfgwjytiZK
//		//$2a$10$91icfenvhuo3DUfgpSl5EexF3jPZL83FGKl7nh6JmRI5JVFdvbFIu
//		
//		// 이 결과를 DB에 등록.
//		// 이제 사용자가 다시 입력한 것을 확인하려면?
//		String inputPw = "1230";
//		boolean matchResult = PasswordEncoder.matches(inputPw, enPwd);
//		// (지금 입력받은 값, 암호화된값-DB에 등록된 값)
//		System.out.println("matchResult : " + matchResult);
//	}
	
	@Autowired
	StringEncryptor jasyptStringEncryptor;
	
	@Test
	public void encryption() {
		String[] strs = {
				"net.sf.log4jdbc.sql.jdbcapi.DriverSpy",
				"jdbc:log4jdbc:oracle:thin:@127.0.0.1:1521:xe",
				"hr",
				"hr"
		};
		
		for(String str : strs) {
			String encStr = jasyptStringEncryptor.encrypt(str);
			System.out.println(encStr);
		}
	}
	

}
