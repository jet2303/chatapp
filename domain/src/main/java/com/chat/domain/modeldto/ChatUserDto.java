package com.chat.domain.modeldto;

import java.time.LocalDateTime;

import com.chat.model.ChatRoom;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ChatUserDto {

    private String userId;
	
    private String userName;
	
    private boolean userStatus;

    private LocalDateTime createdAt;
	
    private LocalDateTime updatedAt;

    private String role;
    
    private String password;

    private ChatRoom chatRoom;
}
