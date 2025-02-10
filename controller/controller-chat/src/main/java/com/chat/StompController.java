package com.chat;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.chat.model.ChatMessage;

@Controller
public class StompController {
	
	@MessageMapping("/hello")
	@SendTo("/subscribe/greeting")
	public String join(String str) {
		System.out.println("join method");
		return str;
	}
}
