package com.project.noonee.web.dto.auth;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.project.noonee.domain.user.User;

import lombok.Data;

@Data
public class SignupReqDto {
	
	@NotBlank
	@Email
	private String email;

	@NotBlank
	@Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[-~!@#$%^&*_+=])[a-zA-Z\\d-~!@#$%^&*_+=]{8,16}$") 
	private String password;
	
	@NotBlank
	@Pattern(regexp = "^[가-힇]*$", message = "한글만 입력 가능합니다.")
	private String username;
	
	@NotBlank
	@Pattern(regexp = "^(?=.*\\d)[\\d]{11}$", message = "휴대폰 번호 11자리를 입력해 주세요.")
	private String userphone;
	
	@AssertTrue(message = "아이디 중복확인이 되지 않았습니다.")
	private boolean checkUsernameFlag;
	
//	@AssertTrue(message = "비밀번호 확인이 되지 않았습니다.")
//	private boolean checkPasswordFlag;
	
	public User toEntity() {
		return User.builder()
				.user_email(email)
				.user_password(new BCryptPasswordEncoder().encode(password))
				.user_name(username)
				.user_phone(userphone)
				.user_roles("ROLE_USER")
				.build();
	}
}







