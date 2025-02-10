package com.chat.model.userrole;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserRole {
	ROLE_USER("USER"), ROLE_ADMIN("ADMIN");
	
	private String description;
}
