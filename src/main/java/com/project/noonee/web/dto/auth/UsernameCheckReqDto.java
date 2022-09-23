package com.project.noonee.web.dto.auth;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class UsernameCheckReqDto {
	@NotBlank
	@Size(max = 16, min = 4, message = "4글자 이상 16글자 이하로 입력")
	private String username;
}
