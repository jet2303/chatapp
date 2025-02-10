package com.chat.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
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
public class ChatMessage {
	
	@Id
	@Column(name = "send_id")
	private String sendId;
	
	@Column(name = "send_name")
	private String sendName;
	
	@Column(name = "chat_room_id")
	private String chatRoomId;
	
	@Column(name = "message")
	private String message;
	
	@Column(name = "chat_message_type")
	private ChatMessageType chatMessageType;
}
