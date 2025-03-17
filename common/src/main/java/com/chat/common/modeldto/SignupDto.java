package com.chat.common.modeldto;


import com.chat.common.custom.customConst.UserNameConst;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignupDto {
	
	@NotBlank
	private String userId;
	@NotBlank
	private String password;
	
	@UserNameConst
	private String userName;
	@NotBlank
	private String role;
}
