package com.project.noonee.service.auth;

import com.project.noonee.web.dto.auth.PasswordCheckReqDto;
import com.project.noonee.web.dto.auth.UsernameCheckReqDto;

public interface AuthService {
	public boolean checkPassword(PasswordCheckReqDto passwordCheckReqDto) throws Exception;
	public boolean checkUsername(UsernameCheckReqDto usernameCheckReqDto) throws Exception;
	public boolean signup() throws Exception;
}
