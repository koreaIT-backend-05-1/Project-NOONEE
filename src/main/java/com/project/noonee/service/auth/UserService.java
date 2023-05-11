package com.project.noonee.service.auth;

import java.util.List;

import com.project.noonee.web.dto.auth.UserRespDto;

public interface UserService {

	public List<UserRespDto> getUserList(int page, String searchFlag) throws Exception;
}
