package com.chat.config;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
public class WebSocketHandler extends TextWebSocketHandler{

    private static final ConcurrentHashMap<String, WebSocketSession> CLIENTS = new ConcurrentHashMap<String, WebSocketSession>();
	
    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        CLIENTS.put(session.getId(), session);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws IOException {
		 String id = session.getId();  //메시지를 보낸 아이디
		 CLIENTS.entrySet().forEach( arg -> {
		     if(!arg.getKey().equals(id)) {  //같은 아이디가 아니면 메시지를 전달합니다.
		         try {
		             arg.getValue().sendMessage(message);
		         } catch (IOException e) {
		             e.printStackTrace();
		         }
		     }
		 });
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
    	CLIENTS.remove(session.getId());
    }
}
