package com.chat.domain.modeldto;

import java.util.List;

import com.chat.model.ChatUser;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Builder
public class ChatRoomDto {

	@NotBlank
	private String roomId;
	
	@NotBlank
    private String roomName;

	
    private String updatedAt;

    @NotBlank
    private int memberCnt;

    @NotBlank
    private boolean isOpen;
    
    private List<ChatUser> chatUserList;
//    private List<String> chatUserList;
}
