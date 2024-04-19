package com.yedam.app.security.service;

// 진짜 회원정보를 가져오는. 구현클래스만들면 알아서 기존꺼를 대체한다.

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.yedam.app.security.mapper.UserMapper;

@Service
public class CustomerUserDetailsService implements UserDetailsService {

	@Autowired
	UserMapper userMapper;
	
	// Provider 필요로하는, 사용하는 메소드.
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		UserVO userVO = userMapper.getuserInfo(username);
		
		// 매퍼를 실행했는데 돌아오는 정보가 없다 -> 회원이 아님 -> 예외를 발생시키기.(예외를 강제로 던지는 방식으로 처리해야 한다)
		if(userVO == null) {
			// 자바가 낚아채기 위한 처리!!
			throw new UsernameNotFoundException("No User");
		}
		
		return new LoginUserVO(userVO);
		// 이렇게 시큐리티에서 요구하는 대상(UserDetailsService를 구현한 클래스)를 잘 만들어주면
		// 시큐리티는 알아서 들고감(시큐리티에 빈으로 따로 등록 안해도 된다) 
	}

}
