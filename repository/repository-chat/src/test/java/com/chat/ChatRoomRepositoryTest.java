package com.chat;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.chat.model.ChatRoom;
import com.chat.model.ChatUser;
import com.chat.repository.ChatRoomRepository;

@SpringBootTest(classes = TestApplication.class)
class ChatRoomRepositoryTest {

    @Autowired
    private ChatRoomRepository chatRoomRepository;

    private ChatRoom chatRoom;

//    private List<ChatUser> userList = new ArrayList<>();
    private ChatUser chatUser1;
    private ChatUser chatUser2;

    @BeforeEach
    public void setUp(){
        chatUser1 = new ChatUser("userId1", "userName1");
        chatUser2 = new ChatUser("userId2", "userName2");
        
        chatRoom = new ChatRoom("roomId1", "roomName1");
        
        chatRoom.addUser(chatUser1);
        chatRoom.addUser(chatUser2);
        chatUser1.setRoom(chatRoom);
        chatUser2.setRoom(chatRoom);
    }

    @AfterEach
    public void setDown(){
        chatRoomRepository.deleteAll();
    }

    @Test
    @Transactional
    public void chatRoomRepositoryTest1(){
        // chatRoom.addUser(chatUser1);
        // chatRoom.addUser(chatUser2);
        
        saveChatRoom();
        ChatRoom findResult = chatRoomRepository.findByRoomId("roomId1").get();
        
        assertAll( 
            () -> assertEquals("roomName1", findResult.getRoomName())

            , () -> assertEquals(2, findResult.getChatUserList().size())
        );
        
    }

    @Test
    public void chatRoomRepositoryTest2(){
        saveChatRoom();
        assertEquals(1, chatRoomRepository.findAll().size()); 
        
    }


    private void saveChatRoom(){
        chatRoomRepository.save(chatRoom);
    }

}
