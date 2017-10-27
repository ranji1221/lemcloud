package org.ranji.lemon.core.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.ranji.lemon.core.CoreApplication;
import org.ranji.lemon.core.service.prototype.IHelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=CoreApplication.class)  //-- 指定Spring-Boot的启动类
@WebAppConfiguration
public class HelloTest {
	
	@Autowired
	private IHelloService hService;
	
	@Autowired
	private WebApplicationContext context;
	private MockMvc mockMvc;
	
	@Before
	public void setMockMvc() throws Exception{
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
	}
	
	@Test
	public void testHello(){
		hService.sayHello();
	}
	
	@Test
	public void testController()throws Exception{
		//-- 1. http get request test
		String result = mockMvc.perform(MockMvcRequestBuilders.get("/hello")).andReturn().getResponse().getContentAsString();
		//-- 2. assert
		Assert.assertEquals(result, "hello world");
	}
}
