package com.project.noonee.service.auth;

import org.springframework.stereotype.Service;

import com.project.noonee.domain.user.UserRepository;
import com.project.noonee.web.dto.auth.PasswordCheckReqDto;
import com.project.noonee.web.dto.auth.UsernameCheckReqDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

	private final UserRepository userRepository;
	
	@Override
	public boolean checkUsername(UsernameCheckReqDto usernameCheckReqDto) throws Exception {
		
		return userRepository.findUserByUsername(usernameCheckReqDto.getUsername()) == null;
	}
	
	@Override
	public boolean checkPassword(PasswordCheckReqDto passwordCheckReqDto) throws Exception {
		
		return userRepository.findUserByUsername(passwordCheckReqDto.getPassword()) == null;
	}

	@Override
	public boolean signup() throws Exception{
		return false;
	}

}






