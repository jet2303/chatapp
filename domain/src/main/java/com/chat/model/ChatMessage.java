package com.chat.model;

import jakarta.annotation.Nonnull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class ChatMessage {
	
	@Id
	@Column(name = "send_id")
	@NotNull
	private String sendId;
	
	@Column(name = "send_name")
	@NotNull
	private String sendName;
	
	@Column(name = "chat_room_id")
	@NotNull
	private String chatRoomId;
	
	@Column(name = "message")
//	@NotNull
	private String message;
	
	@Column(name = "chat_message_type")
	@NotNull
	private ChatMessageType chatMessageType;
}
