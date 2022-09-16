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
		http.csrf().disable();  
		http.authorizeRequests() 
			.antMatchers("/", "/main") 
			.authenticated() 
			.anyRequest() 
			.permitAll()  
			.and()  
			.formLogin() 
			.loginPage("/auth/signin") 
			.loginProcessingUrl("/auth/signin") 
			.failureHandler(new AuthFailureHandler())
			.defaultSuccessUrl("/");
	}
}
