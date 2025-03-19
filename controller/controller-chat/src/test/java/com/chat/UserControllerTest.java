package com.chat;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import com.chat.domain.modeldto.SignupDto;
import com.chat.websocket.config.WebSocketConfig;
import com.fasterxml.jackson.databind.ObjectMapper;

//@ExtendWith(SpringExtension.class)
//@Import(WebSocketConfig.class)
//@AutoConfigureMockMvc

//@SpringBootTest(classes = {UserController.class, ChatUserServiceImpl.class, ChatUserRepository.class}, properties = "spring.config.name=application-test")
@SpringBootTest(properties = "spring.config.name=application-test")
@AutoConfigureMockMvc
//@TestPropertySource(locations = "classpath:application-dev.yml")
@ActiveProfiles("dev")
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
	@DisplayName("userid 가 null 일때")
	void test1() throws Exception{
		
		SignupDto request = SignupDto.builder()
				.userId(null).password("password").userName("name").role("ROLE_USER")
				.build();
		
		
		mockMvc.perform(MockMvcRequestBuilders.post("/createUser")
												.contentType(MediaType.APPLICATION_JSON) 
												.content(obj.writeValueAsString(request))	)
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().is4xxClientError());
				
	}
	
	@Test
	@DisplayName("user 이름이 영어가 아님")
	void test2() throws Exception{
		
		SignupDto request = SignupDto.builder()
				.userId("userId").password("password").userName("123123").role("ROLE_USER")
				
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
