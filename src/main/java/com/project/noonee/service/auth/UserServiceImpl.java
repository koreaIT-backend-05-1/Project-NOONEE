package com.project.noonee.service.auth;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.noonee.domain.user.UserRepository;
import com.project.noonee.web.dto.auth.UserRespDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

	private final UserRepository userRepository;

	@Override
	public List<UserRespDto> getUserList(int page, String searchFlag) throws Exception {
		return null;
	}
}
