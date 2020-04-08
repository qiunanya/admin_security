package com.example.security.config;

import com.example.security.entitys.UserEntity;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.net.UnknownHostException;

/**
  * <p>
  *  MyRedisConfig redis 配置
  * </p>
  *
  * @author qiu
  * @since 2020-04-96
  */
@Configuration
public class MyRedisConfig {
    /**
     * 全局使用方法
     *
     * @param factory
     * @return
     */
    @Bean
    public CacheManager cacheManager(RedisConnectionFactory factory) {
        RedisSerializer<String> redisSerializer = new StringRedisSerializer();
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        //解决查询缓存转换异常的问题
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);
        // 配置序列化（解决乱码的问题）
        RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig()
                // .entryTtl(314455)
                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(redisSerializer))
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(jackson2JsonRedisSerializer))
                .disableCachingNullValues();
        RedisCacheManager cacheManager = RedisCacheManager.builder(factory)
                .cacheDefaults(config)
                .build();
        return cacheManager;
    }
    /**
      * <p>
      *  改变默认的序列化器
      * </p>
      *RedisTemplate<Object, UserEntity>  UserEntity改变json方式存储redis
      * @author qiu
      * @since 2020-04-97
      */
    @Bean
    public RedisTemplate<Object, UserEntity> userEntityRedisTemplate(RedisConnectionFactory factory) throws UnknownHostException {
        RedisTemplate<Object, UserEntity> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(factory);
        Jackson2JsonRedisSerializer<UserEntity> jsonRedisSerializer = new Jackson2JsonRedisSerializer<>(UserEntity.class);
        redisTemplate.setDefaultSerializer(jsonRedisSerializer);

        return redisTemplate;
    }

}
