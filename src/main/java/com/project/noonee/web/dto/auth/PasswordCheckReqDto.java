package com.project.noonee.web.dto.auth;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class PasswordCheckReqDto {
	
	@NotBlank
	private String password;
}
