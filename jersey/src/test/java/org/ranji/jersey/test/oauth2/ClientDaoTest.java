package org.ranji.jersey.test.oauth2;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.ranji.lemon.core.util.DateUtil;
import org.ranji.lemon.jersey.JerseyApplication;
import org.ranji.lemon.jersey.model.oauth2.Client;
import org.ranji.lemon.jersey.persist.oauth2.prototype.IClientDao;
import org.ranji.lemon.jersey.util.GuidUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=JerseyApplication.class)  //-- 指定Spring-Boot的启动类
public class ClientDaoTest {
	
	@Autowired
	IClientDao clientDao;
	
	@Test
	public void testAddClient(){
		Client c = new Client();
		c.setClientName("aaaapp");
		c.setClientId(GuidUtil.generateClientId());
		c.setClientSecret(GuidUtil.generateClientSecret());
		
		System.out.println(c);
		clientDao.save(c);
	}
	
	@Test
	public void testFindClient(){
		Client c = clientDao.find(1);
		System.out.println(c);
		System.out.println(DateUtil.toDateText(c.getCreateTime(), DateUtil.DEFAULT_DATE_TIME_FORMAT));
		System.out.println(DateUtil.toDateText(c.getUpdateTime(), DateUtil.DEFAULT_DATE_TIME_FORMAT));
		
		c.setClientName("estore");
		c.setUpdateTime(DateUtil.now());
		c.setScope("read,write");
		clientDao.update(c);
		System.out.println(c);
		System.out.println(c.getGuid());
		System.out.println(DateUtil.toDateText(c.getCreateTime(), DateUtil.DEFAULT_DATE_TIME_FORMAT));
		System.out.println(DateUtil.toDateText(c.getUpdateTime(), DateUtil.DEFAULT_DATE_TIME_FORMAT));
	}
}
