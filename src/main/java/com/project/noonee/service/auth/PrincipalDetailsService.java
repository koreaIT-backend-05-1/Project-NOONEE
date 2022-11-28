package com.project.noonee.service.auth;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.project.noonee.domain.user.User;
import com.project.noonee.domain.user.UserRepository;
import com.project.noonee.web.dto.auth.SignupReqDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PrincipalDetailsService implements UserDetailsService {
	
	private final UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String useremail) throws UsernameNotFoundException {
		User userEntity = null;
		
		try {
			userEntity = userRepository.findUserByUsername(useremail);
		} catch (Exception e) {
			e.printStackTrace();
			throw new UsernameNotFoundException(useremail);
		}
		
		if(userEntity == null) {
			throw new UsernameNotFoundException(useremail + "사용 할 수 없는 이메일입니다.");
		}
		
		return new PrincipalDetails(userEntity);
	}
	
	public boolean addUser(SignupReqDto signupReqDto) throws Exception {
		
		return userRepository.save(signupReqDto.toEntity()) > 0;
	}

}






