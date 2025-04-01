package com.chat.redis.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
@EnableRedisRepositories
public class RedisConfig {
    @Value("${spring.data.redis.host}")
    private String host;

    @Value("${spring.data.redis.port}")
    private int port;
    
    @Value("${spring.data.redis.password}")
    private String password;

    // redis 설정
    @Bean
    public RedisConnectionFactory redisConnectionFactory(){
    	RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
    	redisStandaloneConfiguration.setPort(port);
    	redisStandaloneConfiguration.setHostName(host);
//        redisStandaloneConfiguration.setUsername(userName);
        redisStandaloneConfiguration.setPassword(password);
    	
        return new LettuceConnectionFactory(redisStandaloneConfiguration);
    }

    
    // redis connection, 직렬, 역직렬 설정
    @Bean
    public RedisTemplate<String, String> redisTemplate() {
        RedisTemplate<String, String> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory());
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new StringRedisSerializer());
        return redisTemplate;
    }
    
    
    // redis cache 설정
    @Bean
    public RedisCacheManager redisCacheManager() {
    	RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig()
    							.serializeKeysWith(
    								RedisSerializationContext.SerializationPair.fromSerializer(
    									new StringRedisSerializer()))
    							.serializeValuesWith(
    								RedisSerializationContext.SerializationPair.fromSerializer(
    										new GenericJackson2JsonRedisSerializer()));
    		
    	return RedisCacheManager.RedisCacheManagerBuilder
    				.fromConnectionFactory(redisConnectionFactory())
    				.cacheDefaults(redisCacheConfiguration)
    				.build();
    		
    }
}
