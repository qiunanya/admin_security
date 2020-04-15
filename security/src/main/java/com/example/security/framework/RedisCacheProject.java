package com.example.security.framework;

import com.example.security.entitys.AuthorityEntity;
import com.example.security.entitys.UserEntity;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
  * <p>
  *  springboot redis 工具
  * </p>
  *
  * @author qiu
  * @since 2020-04-97
  */
@Slf4j
@Component
public class RedisCacheProject implements Serializable {
    private final static Logger logger = LoggerFactory.getLogger(RedisCacheProject.class);
    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    RedisTemplate<Object, AuthorityEntity> authorityRedisTemplate;

    @Autowired
    RedisTemplate<Object , UserEntity> userEntityRedisTemplate;

    /**
     * 自定义user缓存对象
     * @param key
     * @param userEntity
     * @return
     */
    public boolean saveUserInfoCache(String key , UserEntity userEntity){
        boolean flag = false;
        ValueOperations<Object, UserEntity> operations = userEntityRedisTemplate.opsForValue();
        try {
            operations.set(key,userEntity);
            flag = true;
        }catch (Exception e){
            logger.info(e.toString());
            flag = false;
        }
        return flag;
    }

    /**
     * 获取缓存
     */
    public UserEntity getUserInfoCache(String key){
        ValueOperations<Object, UserEntity> operations = userEntityRedisTemplate.opsForValue();
        UserEntity userEntity = operations.get(key);
        return userEntity;
    }

    /**
     *  缓存权限集合到redis
     * @param key
     * @param obj
     * @return
     */
    public Boolean setCacheAuthority(String key,AuthorityEntity obj){
        boolean flag = false;
        ValueOperations<Object, AuthorityEntity> operations = authorityRedisTemplate.opsForValue();
        try {
            operations.set(key,obj);
            flag = true;
        }catch (Exception e){
            logger.error("缓存失败 setCacheObject："+e.toString());
            flag = false;
        }
         return flag;
    }

    /**
     * 通过用户名获取权限集合
     */
    public AuthorityEntity getCacheAuthority(String key){
        ValueOperations<Object, AuthorityEntity> operations = authorityRedisTemplate.opsForValue();
        return operations.get(key);
    }
    /**
     * 登录失败清除权限信息
     */
    public Boolean clearCacheAuthority(String key){
         boolean flag ;
        try {
            authorityRedisTemplate.delete(key);
            flag = true;
        }catch (Exception e){
            logger.error("删除权限失败 clearCacheAuthority"+e.toString());
            flag = false;
        }
        return flag;
    }

}
