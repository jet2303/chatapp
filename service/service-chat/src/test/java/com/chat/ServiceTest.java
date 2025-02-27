package com.chat;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import com.chat.ifs.ChatRoomService;
import com.chat.model.ChatRoom;
import com.chat.model.ChatUser;
import com.chat.modeldto.ChatRoomDto;
import com.chat.repository.ChatRoomRepository;
import com.chat.repository.ChatUserRepository;

import lombok.extern.slf4j.Slf4j;

@DataJpaTest
//@SpringBootTest
@Slf4j
class ServiceTest {

	@Mock
	private ChatUserRepository chatUserRepository;
	
	@Mock
	private ChatRoomRepository chatRoomRepository;
	
//	@InjectMocks
//	private TestService testService;
	
	@InjectMocks
	private ChatRoomServiceImpl chatRoomService;
	
	private ChatUser chatUser1, chatUser2;
	private ChatRoom chatRoom1, resRoom1; 
	
	
	@BeforeEach
	public void setUp() {
		chatUser1 = new ChatUser("1", "userName1");
		chatUser2 = new ChatUser("2", "userName2");
	}
	
	@Test
	@DisplayName("create room")
	public void test2() {
		// given
		resRoom1 = new ChatRoom("1", "roomName1");
		resRoom1.addUser(chatUser1);
		resRoom1.addUser(chatUser2);
		
		ChatRoom chatRoom = ChatRoom.builder()
				.roomId("roomId")
				.roomName("roomname")
				.updatedAt(LocalDateTime.now())
				.memberCnt(2)
				.isOpen(true)
				.chatUserList(List.of(new ChatUser("userId1", "userName1")
									 ,new ChatUser("userId2", "userName2")) )
				.build();
		
		// when
		when(chatUserRepository.findByUserIdAndUserStatus(any(String.class), eq(true) )).thenReturn(Optional.of(chatUser1));
		when(chatRoomRepository.save(any(ChatRoom.class))).thenReturn(chatRoom);
		
		ChatRoomDto res = chatRoomService.createChatRoom("1", chatUser1.getUserId());

		// then
		assertEquals(2, res.getChatUserList().size());
	}
	
	@Test
	@DisplayName("join room")
	public void test3() {
		// given		
		chatRoom1 = new ChatRoom("1", "roomName1");
		chatRoom1.addUser(chatUser1);

		resRoom1 = new ChatRoom("1", "roomName1");
		resRoom1.addUser(chatUser1);
		resRoom1.addUser(chatUser2);
		
		// when
		when(chatRoomRepository.findByRoomIdAndIsOpen(any(String.class), eq(true))).thenReturn(Optional.of(chatRoom1));
		when(chatUserRepository.findByUserIdAndUserStatus(any(String.class), eq(true))).thenReturn(Optional.of( chatUser2));
		
		ChatRoomDto result = chatRoomService.joinChatRoom("1");
		
		// then
		assertEquals(2, result.getChatUserList().size());
	}
	
	@Test
	@DisplayName("leave room")
	public void test4() {
		// given		
		chatRoom1 = new ChatRoom("1", "roomName1");
		chatRoom1.addUser(chatUser1);
		chatRoom1.addUser(chatUser2);
		
		resRoom1 = new ChatRoom("1", "roomName1");
		resRoom1.addUser(chatUser1);
	
		// when		
		when(chatRoomRepository.findByRoomIdAndIsOpen(any(String.class), eq(true))).thenReturn(Optional.of(chatRoom1));
		when(chatUserRepository.findByUserIdAndUserStatus(any(String.class), eq(true))).thenReturn(Optional.of(chatUser2));
		
		ChatRoomDto res = chatRoomService.leaveRoom("1");
		
		// then
		assertEquals(1, res.getChatUserList().size());
	}
	
	@Test
	@DisplayName("baseentity 상속 확인")
	public void test5() {
		ChatRoom chatRoom = ChatRoom.builder()
				.roomId("roomId").roomName("roomName").updatedAt(LocalDateTime.now())
				.build();
		
		log.info("{}", chatRoom.toString());
		
		ChatRoom chatRoom1 = new ChatRoom("roomId", "roomName");
		log.info("{}", chatRoom1.toString());
	}

}
