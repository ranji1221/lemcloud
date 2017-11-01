package org.ranji.liquid.test.redis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.ranji.lemon.core.util.JsonUtil;
import org.ranji.lemon.liquid.LiquidApplication;
import org.ranji.lemon.liquid.model.authority.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=LiquidApplication.class)  //-- 指定Spring-Boot的启动类
public class RedisTest {
	
	@Autowired
	private StringRedisTemplate strRedisTemplate;
	
	@Autowired
	private RedisTemplate<String,String> redisTemplate;
	
	@Test
	public void testObject(){
		//User u = new User();
		//u.setUserName("zhangsan");
		//u.setUserPass("123456");
		
		//redisTemplate.opsForValue().set("stringObj2",JsonUtil.objectToJson(u));
		//strRedisTemplate.opsForValue().set("stringObj3", JsonUtil.objectToJson(u));
		
		String userJson = strRedisTemplate.opsForValue().get("stringObj3");
		User u = JsonUtil.jsonToPojo(userJson, User.class);
		System.out.println(u);
	}
}
