package org.ranji.jersey.test.oauth2;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.ranji.lemon.jersey.JerseyApplication;
import org.ranji.lemon.jersey.model.oauth2.Client;
import org.ranji.lemon.jersey.service.oauth2.prototype.IClientService;
import org.ranji.lemon.jersey.service.oauth2.prototype.IOauthService;
import org.ranji.lemon.jersey.util.GuidUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=JerseyApplication.class)  //-- 指定Spring-Boot的启动类
public class ClientServiceTest {
	
	@Autowired
	IClientService clientService;
	
	@Autowired
	IOauthService oauthService;
	
	@Test
	public void testAddClient(){
		Client c = new Client();
		c.setClientName("nnnapp");
		c.setClientId(GuidUtil.generateClientId());
		c.setClientSecret(GuidUtil.generateClientSecret());
		
		clientService.save(c);
	}
	
	@Test
	public void testCheckClient(){
		System.out.println(oauthService.checkClientId("4CONO77qhHeYe6dbk70H"));
	}
}
