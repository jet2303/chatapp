package com.chat.testClient;

import java.net.URI;

import org.springframework.web.socket.CloseStatus;

import jakarta.websocket.ClientEndpoint;
import jakarta.websocket.ContainerProvider;
import jakarta.websocket.OnClose;
import jakarta.websocket.OnError;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.WebSocketContainer;

@ClientEndpoint
public class WebSocketClient {

	private Session session;

    @OnOpen
    public void onOpen(Session session) {
        System.out.println("Connected to server");
        this.session = session;
    }

    @OnMessage
    public void onMessage(String message) {
        System.out.println("Received: " + message);
    }

    @OnClose
    public void onClose(Session session, CloseStatus closeStatus) {
        System.out.println("Disconnected from server");
    }

    @OnError
    public void onError(Throwable throwable) {
        System.err.println("Error: " + throwable.getMessage());
    }

    public void sendMessage(String message) {
        try {
            session.getBasicRemote().sendText(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        WebSocketContainer container = ContainerProvider.getWebSocketContainer();
        URI uri = new URI("ws://localhost:8080/publish/hello");

        WebSocketClient client = new WebSocketClient();
        container.connectToServer(client, uri);

        client.sendMessage("Hello Server!");
        Thread.sleep(2000); // 서버로부터 응답 대기
    }
}
