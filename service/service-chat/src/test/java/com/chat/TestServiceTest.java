package com.chat;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;


//@SpringBootTest(classes = ChatApp.class)
//@ContextConfiguration(classes = ChatApp.class)
class TestServiceTest {

//	@Autowired
//	private TestService testService;
	
	@Test
	void test() {
		TestService testService = new TestService();
		int result = testService.plus(1, 2);
		
		assertEquals(result, 3);
	}

}
