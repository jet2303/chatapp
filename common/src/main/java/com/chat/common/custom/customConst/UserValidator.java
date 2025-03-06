package com.chat.common.custom.customConst;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UserValidator implements ConstraintValidator<UserNameConst, String> {

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		// 정규 표현식 참고 
		// https://inpa.tistory.com/entry/JAVA-%E2%98%95-%EC%A0%95%EA%B7%9C%EC%8B%9DRegular-Expression-%EC%82%AC%EC%9A%A9%EB%B2%95-%EC%A0%95%EB%A6%AC
		Pattern pattern = Pattern.compile("^[a-zA-Z]*$");
		Matcher matcher = pattern.matcher(value);
		return matcher.matches();
	}

	
}
