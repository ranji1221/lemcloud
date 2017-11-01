package org.ranji.lemon.core.test;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.ranji.lemon.core.CoreApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=CoreApplication.class) 
public class RedisTest {
	
	@Autowired
	private StringRedisTemplate strRedisTemplate;
	
	@Test
	public void testStr() throws Exception {
		System.out.println(strRedisTemplate);
		strRedisTemplate.opsForValue().set("bbb", "111");
		Assert.assertEquals("111", strRedisTemplate.opsForValue().get("aaa"));
	}
}
