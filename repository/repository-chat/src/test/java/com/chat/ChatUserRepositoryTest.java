package com.chat;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.chat.model.ChatRoom;
import com.chat.model.ChatUser;
import com.chat.repository.ChatRoomRepository;
import com.chat.repository.ChatUserRepository;

@SpringBootTest(classes = TestApplication.class)
class ChatUserRepositoryTest {
	
	@Autowired
    private ChatRoomRepository chatRoomRepository;
    @Autowired
    private ChatUserRepository chatUserRepository;

    private ChatRoom chatRoom;

    private ChatUser chatUser1;
    private ChatUser chatUser2;

    @BeforeEach
    public void setUp(){
        chatUser1 = new ChatUser("userId1", "userName1");
        chatUser2 = new ChatUser("userId2", "userName2");
        
        chatRoom = new ChatRoom("roomId1", "roomName1");
        
        // chatRoom.addUser(chatUser1);
        // chatRoom.addUser(chatUser2);
        chatUser1.setRoom(chatRoom);
        chatUser2.setRoom(chatRoom);

    }

    @AfterEach
    public void setDown(){
        
    }

    @Test
    @DisplayName(value = "chatroom / chatuser 연관관계 save")
    public void test1(){
        chatRoomRepository.save(chatRoom);
        chatUserRepository.save(chatUser1);
        chatUserRepository.save(chatUser2);

        ChatUser res = chatUserRepository.findById("userId1").get();
        assertAll(
            () -> assertEquals("roomId1", res.getChatRoom().getRoomId())
        );
    }


}
