package com.project.noonee.web.dto.auth;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UserRespDto {
	private int userCode;
	private String userName;
	private String userEmail;
	private String userPhone;
}
