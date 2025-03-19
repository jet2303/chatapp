package com.chat;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import com.chat.domain.modeldto.ChatRoomDto;
import com.chat.ifs.ChatMessageService;
import com.chat.ifs.ChatRoomService;
import com.chat.model.ChatRoom;
import com.chat.model.ChatUser;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.Cookie;

//@ExtendWith(MockitoExtension.class)
@WebMvcTest(controllers = ChatRoomController.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
class ChatRoomControllerTest {

	private MockMvc mockMvc;
	
	private ObjectMapper objectMapper = new ObjectMapper();
	
	@MockBean
	private ChatRoomServiceImpl chatRoomService;
	
//	@MockBean
//	private ChatMessageService chatMessageService;
	
	@Autowired
	private WebApplicationContext wac;
	
	@BeforeEach
	public void setUp() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(wac)
				.addFilter(new CharacterEncodingFilter("UTF-8", true))
				//	.apply(SecurityMockConfigures.springSecurity())
				.build();
	}
	
	@Test
	@DisplayName(value = "체팅방 생성")
	public void createRoom() throws Exception{
		// given
		ChatRoom retRoom = new ChatRoom("roomId", "roomName");
		ChatRoomDto retRoomDto = ChatRoomDto.builder()
				.roomId("roomId")
				.roomName("roomName")
				.updatedAt(LocalDateTime.now().toString())
				.memberCnt(2)
				.isOpen(true)
				.chatUserList(List.of(new ChatUser("userId1", "userName1"), new ChatUser("userId2", "userName2")))
				.build();
		
		Map<String, String> sendData = new HashMap<>();
		sendData.put("roomName", "roomNameKey");		
		
		// when
		when(chatRoomService.createChatRoom(any(String.class), any(String.class))).thenReturn(retRoomDto);
		
		this.mockMvc.perform(post("/chatRooms/createRoom")
							.cookie(new Cookie("userId", "1"))
							.contentType(MediaType.APPLICATION_JSON)
//							.content(objectMapper.writeValueAsBytes(mockHttpServletRequest))
							.content(objectMapper.writeValueAsBytes(sendData)) )
					
					.andExpect(status().isOk())
					.andDo(print());
	}
	
	@Test
	@DisplayName(value = "채팅방 UI 불러오기")
	public void chatRoom() throws Exception{
		// given
		ChatRoom retRoom = new ChatRoom("roomId", "roomName");
		ChatRoomDto retRoomDto = ChatRoomDto.builder()
				.roomId("roomId")
				.roomName("roomName")
				.updatedAt(LocalDateTime.now().toString())
				.memberCnt(2)
				.isOpen(true)
				.chatUserList(List.of(new ChatUser("userId1", "userName1"), new ChatUser("userId2", "userName2")))
				.build();
//		retRoom.addUser(null);
		when(chatRoomService.joinChatRoom(any(String.class))).thenReturn(retRoomDto);
		
	    this.mockMvc.perform(get("/chatRooms/room/1"))
//	    						.param("roomId", "1"))
			        .andExpect(status().isOk())
			        .andDo(print());
	}
}
