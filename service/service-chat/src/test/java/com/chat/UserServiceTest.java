package com.chat;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.jdbc.support.CustomSQLExceptionTranslatorRegistrar;
import org.springframework.messaging.handler.annotation.support.MethodArgumentNotValidException;
import org.springframework.test.context.ActiveProfiles;

import com.chat.common.exception.CustomErrorException;
import com.chat.common.exception.ErrorCode;
import com.chat.domain.modeldto.SignupDto;
import com.chat.ifs.ChatRoomService;
import com.chat.ifs.ChatUserService;
import com.chat.model.ChatRoom;
import com.chat.model.ChatUser;
import com.chat.model.userrole.UserRole;
import com.chat.repository.ChatUserRepository;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;

@DataJpaTest
@ActiveProfiles("dev")
class UserServiceTest {

	@Mock
	private ChatUserRepository chatUserRepository;
	
	@InjectMocks
	private ChatUserServiceImpl chatUserService;
	
	@Test
	@DisplayName("created User")
	void test() {
		// given
		ChatUser output = ChatUser.builder()
				.userId("userId")
				.userName("userName")
				.password("password")
				.userStatus(true)
				.createdAt(LocalDateTime.now())
				.updatedAt(null)
				.role(UserRole.ROLE_USER)
				.chatRoom(new ChatRoom("roomId", "rommName"))
				.build();
		
		SignupDto input = SignupDto.builder()
				.userId("userId")
				.password("password")
				.userName("userName")
				.role(UserRole.ROLE_USER.name())
				.build();
		
		// when
		when(chatUserRepository.save(any(ChatUser.class))).thenReturn(output);
		
		ChatUser result = chatUserService.createdUser(input);
		
		// then
		assertAll(() -> assertEquals("userName", result.getUserName())
					, () -> assertEquals("userId", result.getUserId())
					, () -> assertEquals(true, result.isUserStatus())
					, () -> assertEquals("roomId", result.getChatRoom().getRoomId())
				);
		
	}
	
	@Test
	@DisplayName("failure create user")
	void test2() {
		SignupDto input = SignupDto.builder()
				.userId("userId")
				.password("password")
				.userName("userName")
				.role(UserRole.ROLE_USER.name())
				.build();
		
		when(chatUserRepository.save(any(ChatUser.class))).thenThrow(new CustomErrorException(ErrorCode.FAILURE));
		
		CustomErrorException ex = assertThrows(CustomErrorException.class, () -> chatUserService.createdUser(input));
		
		assertAll( () -> assertEquals(404, ex.getErrorCode().getStatusCode())
					, () -> assertEquals("실패", ex.getErrorCode().getDescription()) );
		
		
	}

	@Test
	@DisplayName("jenkins test")
	void test3() {
		assertEquals("null", "null");
	}
}
