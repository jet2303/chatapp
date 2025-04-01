package com.chat.redis;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;

import com.chat.redis.config.RedisConfig;

@SpringBootTest
@ContextConfiguration(classes = RedisConfig.class)
public class RedisTest {

	@Autowired
	private RedisTemplate<String, String> redisTemplate;
	
	@Test
	@DisplayName("redis 기본 저장, 조회")
	public void redisTest1() {
        redisTemplate.opsForValue().set("testKey1", "Hello Redis");

        String value = redisTemplate.opsForValue().get("testKey1");

        assertEquals("Hello Redis", value);
	}
	

    @Test
    @DisplayName("key+TTL 저장 / 조회 / TTL 이후 조회")
    public void testRedisExpiration() throws InterruptedException {
        // 키 설정 + TTL 3초
        redisTemplate.opsForValue().set("expireKey", "I will disappear", 3, TimeUnit.SECONDS);

        //  즉시 조회 → 값 존재해야 함
        assertEquals("I will disappear", redisTemplate.opsForValue().get("expireKey"));

        //  5초 대기 → 값 만료
        Thread.sleep(5000);

        //  값이 삭제되었는지 확인
        assertNull(redisTemplate.opsForValue().get("expireKey"));
    }

}
