package com.chat;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.chat.model.ChatMessage;
import com.chat.model.ChatMessageType;
import com.chat.repository.ChatMessageRepository;

@SpringBootTest(classes = TestApplication.class)
public class ChatMessageRepositoryTest {
	   @Autowired
	    private ChatMessageRepository repository;

	    private ChatMessage chatMessage;
	    private ChatMessage chatMessage2;

	    @BeforeEach
	    public void setUp(){
	        chatMessage = new ChatMessage("snd_id", "snd_name", "room_id", "message_test",ChatMessageType.MESSAGE);
	        chatMessage2 = new ChatMessage("snd_id2", "snd_name2", "room_id2", "message_test2",ChatMessageType.MESSAGE);
	    }

	    @AfterEach
	    public void setDown(){
	        repository.deleteAll();
	    }

	    @Test
	    @DisplayName(value = "ChatMessageRepository 저장 확인")
	    public void repositoryTest1(){
	        saveData();
	        ChatMessage savedMessage = repository.findById("snd_id").get();
	        assertAll( () -> assertEquals("snd_id", savedMessage.getSendId())
	            , () -> assertEquals("snd_name", savedMessage.getSendName()) ); 
	    }

	    @Test
	    @DisplayName(value= "ChatMessageRepository 메서드 확인")
	    public void repositoryTest2(){
	        saveData();
	        Long dataCnt = repository.count();
	        assertEquals(2L, dataCnt);
	    }

	    private void saveData(){
	        repository.save(chatMessage);
	        repository.save(chatMessage2);
	    }
}
