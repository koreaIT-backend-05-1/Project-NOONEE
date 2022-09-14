package com.project.noonee.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.project.noonee.config.auth.AuthFailureHandler;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Bean 
	public BCryptPasswordEncoder passwordEncoder() { 
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable(); // csrf 요청 위조 
		http.authorizeRequests() // 인증관련된 세팅
			.antMatchers("/", "/index", "/mypage/**") // 우리가 지정한 요청
			.authenticated() // 인증을 거쳐라
			.anyRequest() // 다른 모든요청은
			.permitAll() // 모두 접근 권한을 부여한다 
			.and() // 설정 추가 
			.formLogin() // 로그인 방식은 form로그인을 사용하겠다
			.loginPage("/auth/signin") // 로그인 페이지는 해당 get요청을 통해 접근한다  해당 주소로 유도해주는 명령
			.loginProcessingUrl("/auth/signin") // 로그인 요청(post), controller: postmapping
			.failureHandler(new AuthFailureHandler())
			.defaultSuccessUrl("/");
	}
}
