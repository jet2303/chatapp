package com.chat.common.modeldto;

import java.time.LocalDateTime;


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
