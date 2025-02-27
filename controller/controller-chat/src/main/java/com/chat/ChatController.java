package com.chat;

import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.chat.ifs.ChatMessageService;
import com.chat.model.ChatMessage;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@EnableJpaRepositories
public class ChatController {

	private final ChatMessageService chatMessageService;
	

    public ChatController(ChatMessageService chatMessageService) {
        this.chatMessageService = chatMessageService;
    }

//    @MessageMapping("/chat/join")
//    public void join(ChatMessage message) {
//        log.info("MessageMapping /chat/join");
//
//        message.setMessage(message.getSendId() + "님이 입장하셨습니다.");
//        chatMessageService.join(message);
//    }
    
//    client가 /publish/chat/join 으로 메시지 보냄
    @MessageMapping("/chat/join")
    @SendTo("/subscribe/webchat-topic")
    public ChatMessage join(@Valid ChatMessage message) {
    	return message;
    }

    @MessageMapping("/chat/leave")
    public void leave(@Valid ChatMessage message) {
        log.info("MessageMapping /chat/leave");
        message.setMessage(message.getSendId() + "님이 퇴장하셨습니다.");
        chatMessageService.leave(message);
    }

    @MessageMapping("/chat/message")
    public void message(@Valid ChatMessage message) {
        // message - sndId, sndName, chatRoomId, chatMessageType 필요.
        log.info("MessageMapping /chat/message");
        chatMessageService.sndMessage(message);
    }

//    권고사항으로 인한 변경
//    @RequestMapping(value = "/chat/message", produces = "application/json", method = { RequestMethod.POST })
    @PostMapping(value = "/chat/message", produces = "application/json")
    public ResponseEntity<? extends CommonResponse<HttpStatusCode>> messagePost(@Valid @RequestBody ChatMessage message) {
        log.info("MessageMapping /chat/message");
        chatMessageService.sndMessage(message);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
}
