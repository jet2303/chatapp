package com.chat.modeldto;

import java.time.LocalDateTime;

import com.chat.model.ChatRoom;
import com.chat.model.userrole.UserRole;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
