package com.chat;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

import com.chat.config.jpaConfig.JpaAuditingConfig;
import com.chat.model.BaseEntity;
import com.chat.model.ChatRoom;
import com.chat.model.ChatUser;
import com.chat.model.userrole.UserRole;
import com.chat.repository.ChatRoomRepository;
import com.chat.repository.ChatUserRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
//@SpringBootTest(classes = TestApplication.class)
@SpringBootTest
@Import(JpaAuditingConfig.class)
class ChatUserRepositoryTest {
	
	@Autowired
    private ChatRoomRepository chatRoomRepository;
    @Autowired
    private ChatUserRepository chatUserRepository;

    private ChatRoom chatRoom;

    private ChatUser chatUser1;
    private ChatUser chatUser2;
    private ChatUser chatUser3;

    @Autowired
    ApplicationContext context;
    
    @BeforeEach
    public void setUp(){
        chatUser1 = new ChatUser("userId1", "userName1");
        chatUser2 = new ChatUser("userId2", "userName2");
        chatUser3 = ChatUser.builder()
        		.userId("userId3")
        		.userName("userName3")
        		.userStatus(true).role(UserRole.ROLE_USER).build();
        
        chatRoom = new ChatRoom("roomId1", "roomName1");
        
        chatUser1.prePersist();
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
        chatUserRepository.save(chatUser3);

//        ChatUser res = chatUserRepository.findById("userId1").get();
        ChatUser res = chatUserRepository.findByUserId("userId1").get();
        log.info("{}, {}, {}", res.getUserId(), res.getUserName(), res.getRole());
        log.info("{}, {}", res.getCreatedBy(), res.getCreatedAt());
        assertAll(
            () -> assertEquals("roomId1", res.getChatRoom().getRoomId())
        );
    }
    
    @Test
    void printAllBeans() {
        String[] beans = context.getBeanDefinitionNames();
        Arrays.stream(beans)
            .filter(name -> name.toLowerCase().contains("audit"))
            .forEach(System.out::println);
    }
    
    @Test
    void auditingEntityListenerIsLoaded() {
        boolean isEntityListenerPresent = context.containsBeanDefinition("org.springframework.data.jpa.domain.support.AuditingEntityListener");
        assertThat(isEntityListenerPresent).isTrue();
    }

}
