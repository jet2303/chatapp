package com.chat;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chat.ifs.ChatUserService;
import com.chat.model.ChatUser;
import com.chat.modeldto.ChatUserDto;
import com.chat.modeldto.SignupDto;

import jakarta.validation.Valid;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@RestController
//@RequestMapping
@RequiredArgsConstructor

public class UserController {

	private final ChatUserServiceImpl chatUserService;
	
	@PostMapping("/createUser")
	public ResponseEntity<ChatUser> createUser(@Valid @RequestBody SignupDto signupDto){
		
		ChatUser newUser = chatUserService.createdUser(signupDto);
		
		return ResponseEntity.ok(newUser);
	}
}
