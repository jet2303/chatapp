package com.chat;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.chat.model.ChatMessage;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaProducerService {

	@Value("${kafka.topic.name}")
	private String topicName;
	
	private final KafkaTemplate<String, ChatMessage> kafkaTemplate;
	
    public void sendMessage(ChatMessage message) {
        log.info(">>>>>>>> kafka send message : " + message);
//        this.kafkaTemplate.send(topicName, message);
        this.kafkaTemplate.send("webchat-topic", message);
    }
}
