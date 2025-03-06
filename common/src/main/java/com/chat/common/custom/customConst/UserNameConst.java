package com.chat.common.custom.customConst;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {UserValidator.class})
@Documented
public @interface UserNameConst {
	
	String message() default "문자열이 아닌 다른것이 입력됨.";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
}
