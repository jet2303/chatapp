package com.chat;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ChatService {

	private final RedisTemplate<String, String> redisTemplate;
	
	public void sendMessage(String channel, String message) {
		redisTemplate.convertAndSend(channel, message);
	}
}
