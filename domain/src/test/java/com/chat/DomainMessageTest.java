package com.chat;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.chat.model.ChatMessage;
import com.chat.model.ChatMessageType;

class DomainMessageTest {

	@Test
	@DisplayName("Message @Valid Test")
	void test() {
		
		ChatMessage chatMessage = ChatMessage.builder()
				.sendId("sendId")
//				.sendName("sendName")
				.chatRoomId("chatroomId")
				.message("message Test")
				.chatMessageType(ChatMessageType.MESSAGE)
				.build();
		
		assertThrows(null, null);
	}

}
