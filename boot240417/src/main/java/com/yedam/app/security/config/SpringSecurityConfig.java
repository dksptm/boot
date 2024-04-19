package com.yedam.app.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {
	
	// 암호화
	@Bean
	public PasswordEncoder passwordEncoder() {
		// 내가 입력한 암호화된 비번과 DB에 등록한 암호화된 비번을 확인.
		// BCryptPasswordEncoder : 해시함수 사용.
		return new BCryptPasswordEncoder();
	}
	
	// 테스트용으로 회원정보 그냥 메모리에(DB아님) 저장
	// 메모리상 인증정보 활용
	//@Bean -- userdetails 만들었으니 이제 사용안함
//	public InMemoryUserDetailsManager inMemoryUserDetailsManager() {
//		UserDetails user = User.builder()
//								.username("user1")
//								.password(passwordEncoder().encode("1234"))
//								.roles("USER") // 내부에 ROLE_USER로 등록됨 또는 authorities("ROLE_USER") 그리고 ("USER", "SALES")
//								.build();
//		
//		return new InMemoryUserDetailsManager(user);
//	}
	// 이제 로그인만하면 모든페이지 접속가능
	
	// 인증 및 인가 설정
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		// 권한 등록.(인가 등록)
		http.authorizeHttpRequests()
			.antMatchers("/", "/all") // 경로 등록 메소드 .뒤에 위의 경로는 어떤 인가를 가진 사람이 접속할수 있는지.
				.permitAll() // 모든 사람에게 접근 허용
			.antMatchers("/user/**").hasAnyRole("USER", "ADMIN")// .hasAnyRole 어느하나라도 권한이 있으면 //.hasRole("USER") // ROLE_USER라는 대상을 체크(우리가 입력한 유저에 ROLE_이 붙었다고생각함)
			.antMatchers("/admin/**").hasAuthority("ROLE_ADMIN") // 만약 ROLE_ 안쓰겠다? 그럼 이 메소드 사용
			.anyRequest() // 위의 경로 외의 모든 경우는
				.authenticated() // 인증된 대상만..(인가는 신경안씀)
			.and() // 로그인관련 설정시작
			.formLogin()
				.defaultSuccessUrl("/all")
			.and()
			.logout()
				.logoutSuccessUrl("/all");
		
		// 내장된 formLogin 을 통해 처리하겠다는..
//		http.formLogin()
//			.defaultSuccessUrl("/all");
		// 사실 .authenticated().and().formLogin()... 뒤에 계속 이어붙여도 된다.이렇게 설정에 대해서만.
		// (단 관련된 설정이란 뜻 아님). 빌더방식이라 가능한것.
//		http.logout();
		
		http.csrf().disable(); // cors관련.
		
		return http.build();
	}
	
}
