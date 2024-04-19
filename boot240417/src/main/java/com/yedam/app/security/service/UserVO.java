package com.yedam.app.security.service;

import lombok.Data;

// mapper가 요구하는 VO - 시큐리티가 요구하는 VO와 다름(시큐리티는 UserDetails를 구현한 클래스를 요구한다)

@Data
public class UserVO {

	private String loginId;
	private String password;
	private String roleName;	
	
}
