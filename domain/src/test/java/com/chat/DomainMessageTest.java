package com.chat;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.chat.model.ChatMessage;
import com.chat.model.ChatMessageType;
import com.chat.model.ChatUser;

import lombok.extern.slf4j.Slf4j;

@Slf4j
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
		log.info("{}", chatMessage.toString());
	}
	
	@Test
	void test1() {
//		ChatUser chatUser = new ChatUser("userId1", "UserName1");
		ChatUser chatUser = ChatUser.builder().userId("userId1").userName("UserName1").build();
		
		assertEquals("userId1", chatUser.getUserId());
		assertEquals("UserName1", chatUser.getUserName());
	}

}
