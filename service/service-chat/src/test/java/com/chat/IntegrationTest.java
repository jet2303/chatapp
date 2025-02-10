package com.chat;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = TestApplication.class)
//@DataJpaTest
public class IntegrationTest {

	@Autowired
	private TestService testService;
	
	@Test
	public void serviceTest1() {
		int result = testService.plus(2, 3);
		
		assertEquals(result, 5);
	}
	
	@Test
	public void serviceTest2() {
		int result = testService.plus(4, 2);
		
		assertEquals(result, 6);
	}
}
