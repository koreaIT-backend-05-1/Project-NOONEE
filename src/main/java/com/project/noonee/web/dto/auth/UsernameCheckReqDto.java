package com.project.noonee.web.dto.auth;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class UsernameCheckReqDto {
	
	@NotBlank
	@Email
	private String username;
	
}
