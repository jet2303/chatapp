package com.chat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.chat.ifs.ChatMessageService;

@ExtendWith(MockitoExtension.class)
class ChatControllerTest {
	
	@Mock
	private ChatMessageService chatMessageService;

	@InjectMocks
	private ChatController chatController;


	@Test
	public void testJoin() {
		throw new RuntimeException("not yet implemented");
	}

	@Test
	public void testLeave() {
		throw new RuntimeException("not yet implemented");
	}

	@Test
	public void testMessage() {
		throw new RuntimeException("not yet implemented");
	}

	@Test
	public void testMessagePost() {
		throw new RuntimeException("not yet implemented");
	}

}
