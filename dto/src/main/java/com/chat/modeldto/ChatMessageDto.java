package com.chat.modeldto;

import com.chat.model.ChatMessageType;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ChatMessageDto {

	@NotNull
	private String sendId;
	
	@NotNull
	private String sendName;
	
	@NotNull
	private String chatRoomId;
	
	private String message;
	
	@NotNull
	private String chatMessageType;
	
}
