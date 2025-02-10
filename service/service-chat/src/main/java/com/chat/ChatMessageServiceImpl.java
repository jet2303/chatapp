package com.chat;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import com.chat.ifs.ChatMessageService;
import com.chat.ifs.ChatRoomService;
import com.chat.model.ChatMessage;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ChatMessageServiceImpl implements ChatMessageService{

    private final SimpMessagingTemplate messagingTemplate;
    private final ChatRoomService chatRoomService;
    private final KafkaProducerService kafkaProducerService;
    
	@Override
	public void join(ChatMessage message) {
//		Kafka와 STOMP가 같이 쓰이고있는거???
        kafkaProducerService.sendMessage(message);
        messagingTemplate.convertAndSend("/subscribe/chat/room/" + message.getChatRoomId(), message);
	}

	@Override
	public void sndMessage(ChatMessage message) {
        kafkaProducerService.sendMessage(message);
        messagingTemplate.convertAndSend("/subscribe/chat/room/" + message.getChatRoomId(), message);
	}

	@Override
	public void leave(ChatMessage message) {
        kafkaProducerService.sendMessage(message);
        chatRoomService.leaveRoom(message.getChatRoomId());
        messagingTemplate.convertAndSend("/subscribe/chat/room/" + message.getChatRoomId(), message);
	}
}
