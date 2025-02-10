package com.chat.event;

import org.springframework.context.ApplicationEvent;

import com.chat.model.ChatUser;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class UserRegEvent extends ApplicationEvent{


	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private final ChatUser chatUser;
	private final HttpServletRequest httpServletRequest;
	private final HttpServletResponse httpServletResponse;
	

	public UserRegEvent(ChatUser chatUser, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		super(chatUser);
		this.chatUser = chatUser;
		this.httpServletRequest = httpServletRequest;
		this.httpServletResponse = httpServletResponse;
	}
}
