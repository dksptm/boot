package com.yedam.app.security.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@SuppressWarnings("serial")
public class LoginUserVO implements UserDetails {
	
	// 시큐리티가 요구하는 VO - 일반적인 VO와 안맞음..(예. 아작스로 json 전송)
	// 기존시큐리티는 메모리에서 정보를 가져오는데
	// 이제는 DB에서 가져오게하기위함
	// provider는 건드리지않고(이것도 커스텀은 가능함) 프로바이더한테 반환하기위한 작업
	// 내가 html form에 입력한 정보는 시큐리티 컨택스트가 가지고 있고 프로바이터가 확인작업
	// 세션도 여기에 같이 묶임.
	// 세션을 쓰지않고 서버쪽에서는 AuthenticationPrincipal UserDetails user 이런식으로 사용이 가능하다.
	
	private UserVO userVO;
	
	public LoginUserVO(UserVO userVO) {
		this.userVO = userVO;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() { // 권한
		// 가지고오는 권한 : 나는 현재 모두 String.
		// Collection : List, Set의 부모.
		List<GrantedAuthority> auth = new ArrayList<>();
		auth.add(new SimpleGrantedAuthority(userVO.getRoleName()));
		return auth;
	}

	@Override
	public String getPassword() {
		return userVO.getPassword();
	}

	@Override
	public String getUsername() {
		return userVO.getLoginId();
	}

	@Override
	public boolean isAccountNonExpired() { // 계정 만료 여부
		return true;
	}

	@Override
	public boolean isAccountNonLocked() { // 계정 잠금여부.
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() { // 계정 패스워드 만료여부.
		return true;
	}

	@Override
	public boolean isEnabled() { // 계정 사용여부.
		return true;
	}

}
