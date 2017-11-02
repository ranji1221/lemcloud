package org.ranji.liquid.test.redis;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.ranji.lemon.core.util.JsonUtil;
import org.ranji.lemon.liquid.LiquidApplication;
import org.ranji.lemon.liquid.model.authority.Role;
import org.ranji.lemon.liquid.model.authority.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=LiquidApplication.class)  //-- 指定Spring-Boot的启动类
public class RedisTest {
	
	//-- 1. 若缓存的实体类比较简单，即是里面的属性没有关联其他的实体类或实体类集合
	@Autowired
	private StringRedisTemplate strRedisTemplate;
	
	//-- 备注： 一般建议用该模板处理，不过具体情况具体分析
	//-- 2. 若缓存的实体类比较复杂，里边包含的别的实体类或集合，则使用该操作模板，当然简单的实体类型也能使用该模板，而且更加的简单
	@Autowired
	private RedisTemplate<String,Object> redisTemplate;
	
	@Test
	public void testAddBystrRedis(){
		User u = new User();
		u.setUserName("zhangsan");
		u.setUserPass("123456");
		
		strRedisTemplate.opsForValue().set("strObj4", JsonUtil.objectToJson(u));

	}
	
	@Test
	public void testQueryBystrRedis(){
		String userJson = strRedisTemplate.opsForValue().get("strObj4");
		User u = JsonUtil.jsonToPojo(userJson, User.class);
		System.out.println(u);
	}
	
	
	@Test
	public void testAddByRedis(){
		User u = new User();
		u.setUserName("zhangsan");
		u.setUserPass("123456");
		
		List<Role> roles = new ArrayList<Role>();
		Role r1 = new Role();
		r1.setRoleName("haha");
		Role r2 = new Role();
		r2.setRoleName("hehe");
		roles.add(r1);
		roles.add(r2);
		
		u.setRoleList(roles);
		
		redisTemplate.opsForValue().set("Obj6", u);
		
	}
	
	@Test
	public void testQueryByRedis(){
		
		User u = (User)redisTemplate.opsForValue().get("Obj5");
		
		System.out.println(u);
		
		u = (User)redisTemplate.opsForValue().get("Obj6");
		
		for(Role r : u.getRoleList())
			System.out.println(r);
	}
}
