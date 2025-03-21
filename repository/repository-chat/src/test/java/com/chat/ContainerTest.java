package com.chat;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import com.chat.model.ChatUser;
import com.chat.model.userrole.UserRole;
import com.chat.repository.ChatUserRepository;

@DataJpaTest
@Testcontainers
@ActiveProfiles(profiles = "prod")
class ContainerTest {

	@Container
    private static final MySQLContainer<?> MYSQL_CONTAINER = 
	            new MySQLContainer<>("mysql:8.0")
	                    .withDatabaseName("chat")
	                    .withUsername("root")
	                    .withPassword("password1");

    @Autowired
    private ChatUserRepository userRepository;

    @AfterAll
    public static void close() {
    	MYSQL_CONTAINER.close();
    }
    
    @Test
    void testUserSaveAndRetrieve() {
    	ChatUser user = ChatUser.builder()
    			.userId("Testuserid").userName("TestuserName")
    			.userStatus(true).role(UserRole.ROLE_USER)
    			.updatedAt(LocalDateTime.now().plusHours(1L))
    			.password("passwordTest")
    			.build();
        
        userRepository.save(user);

        ChatUser found = userRepository.findByUserIdAndUserStatus("Testuserid", true).get();
        assertThat(found).isNotNull();
        assertThat(found.getUserName()).isEqualTo("TestuserName");
    }
	

}
