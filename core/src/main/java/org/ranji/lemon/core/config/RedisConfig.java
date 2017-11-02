package org.ranji.lemon.core.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the"License"); 
 * you may not use this file except in compliance with the License.  
 * You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  
 * See the License for the specific language governing permissions and limitations under the License.
 * Copyright [2017] [RanJi] [Email-jiran1221@163.com]
 * 
 * Redis缓存配置，其实不配置也行，这里之所以要配置是因为想缓存的对象不想是二进制的，而是json类型的，所以自己添加了处理方法
 * 不加这里的配置，也完全可以使用RedisTemplate和StringRedisTemplate
 * @author RanJi
 * @date 2017-11-2
 * @since JDK1.8
 * @version 1.0
 */

@Configuration
@EnableCaching
public class RedisConfig extends CachingConfigurerSupport{
	
	 //自定义缓存key生成策略
	 @Bean
	 public KeyGenerator keyGenerator() {
	      return new KeyGenerator(){
	         @Override
	          public Object generate(Object target, java.lang.reflect.Method method, Object... params) {
	              StringBuffer sb = new StringBuffer();
	              sb.append(target.getClass().getName());
	              sb.append(method.getName());
	              for(Object obj:params){
	                  sb.append(obj.toString());
	              }
	              return sb.toString();
	          }
	      };
	 }
	
	@Bean 
    public CacheManager cacheManager(RedisTemplate<String,Object> redisTemplate) {
        RedisCacheManager cacheManager = new RedisCacheManager(redisTemplate);
        //设置缓存过期时间 
        cacheManager.setDefaultExpiration(43200);		//-- 12小时
        return cacheManager;
    }
	
	@Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<String, Object>();
        redisTemplate.setConnectionFactory(factory);
        setSerializer(redisTemplate);
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
    private void setSerializer(RedisTemplate<String, Object> template) {
		Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(jackson2JsonRedisSerializer);
    }
}
