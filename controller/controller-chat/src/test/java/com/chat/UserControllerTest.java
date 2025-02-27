package com.chat;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import com.chat.config.WebSocketConfig;
import com.chat.ifs.ChatUserService;
import com.chat.modeldto.SignupDto;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest(classes = {ChatMessageServiceImpl.class, SimpMessagingTemplate.class})
//@Import({UserController.class, ChatUserServiceImpl.class})
//@ExtendWith(SpringExtension.class)
//@SpringBootTest
@Import(WebSocketConfig.class)
@AutoConfigureMockMvc
class UserControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private WebApplicationContext wac;
	
	@Autowired
	private ObjectMapper obj;
	
//		
	@BeforeEach
	public void setUp() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(wac)
				.addFilter(new CharacterEncodingFilter("UTF-8", true))
				//	.apply(SecurityMockConfigures.springSecurity())
				.build();
	}
	
	@Test
	void test() throws Exception{
		
		SignupDto request = SignupDto.builder()
				.userId("userId").password("password").userName(null).role("ROLE_USER")
				.build();
		
		
		mockMvc.perform(MockMvcRequestBuilders.post("/createUser")
												.contentType(MediaType.APPLICATION_JSON) 
												.content(obj.writeValueAsString(request))	)
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().is4xxClientError());
				
	}
		
//	@Autowired
//    private ApplicationContext applicationContext;
//
//    @Test
//    public void printAllBeans() {
//        String[] beanNames = applicationContext.getBeanDefinitionNames();
//        Arrays.sort(beanNames); // 정렬된 형태로 출력
//        for (String beanName : beanNames) {
//            System.out.println(beanName);
//        }
//    }

}
