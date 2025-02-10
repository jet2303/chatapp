package com.chat;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.chat.model.ChatMessage;
import com.chat.repository.ChatMessageRepository;


@Service
public class KafkaConsumerService {
    
	private final ChatMessageRepository messageRepository;
	
    public KafkaConsumerService(ChatMessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @KafkaListener(topics = "webchat-topic", groupId = "webchat")
    public void consume(ChatMessage message) throws Exception {
        System.out.println("<<<<<<<< kafka consume receive message : " + message);
        messageRepository.save(message);
    }
}
