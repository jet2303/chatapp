package com.chat.modeldto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class SignupDto {
	
	private String userId;
	private String password;
	
	@NotBlank
	private String userName;
	private String role;
}
