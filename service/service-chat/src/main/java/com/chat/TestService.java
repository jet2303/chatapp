package com.chat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.chat.model.ChatUser;
import com.chat.repository.ChatUserRepository;

@Component
public class TestService {
	
	@Autowired
	private ChatUserRepository chatUserRepository;
	
	
	
	public int plus(int a, int b) {
		return a+b;
	}
	
	public ChatUser testService(String userId) {
		return chatUserRepository.findByUserId(userId);
	}
}
