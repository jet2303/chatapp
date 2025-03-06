package com.chat.modeldto;

import java.util.List;

import com.chat.model.ChatUser;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Builder
public class ChatRoomDto {

	private String roomId;
	
    private String roomName;

    private String updatedAt;

    private int memberCnt;

    private boolean isOpen;
    
    private List<ChatUser> chatUserList;
//    private List<String> chatUserList;
}
