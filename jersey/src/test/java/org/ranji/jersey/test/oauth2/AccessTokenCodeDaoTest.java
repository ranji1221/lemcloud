package org.ranji.jersey.test.oauth2;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.ranji.lemon.jersey.JerseyApplication;
import org.ranji.lemon.jersey.model.oauth2.AccessTokenCode;
import org.ranji.lemon.jersey.persist.oauth2.prototype.IAccessTokenCodeDao;
import org.ranji.lemon.jersey.util.GuidUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=JerseyApplication.class)  //-- 指定Spring-Boot的启动类
public class AccessTokenCodeDaoTest {
	
	@Autowired
	IAccessTokenCodeDao accessTokenCode;
	
	@Test
	public void testAddAccessTokenCode(){
		AccessTokenCode atc = new AccessTokenCode();
		atc.setUsername("asdqqfg");
		atc.setClientId(GuidUtil.generateClientId());
		atc.setCode(GuidUtil.generateClientId());
		System.out.println(atc);
		
		accessTokenCode.save(atc);
	}
	
	@Test
	public void testFindAccessTokenCode(){
		AccessTokenCode atc = accessTokenCode.find(1);
		System.out.println(atc);
	}
	
	@Test
	public void testUpdateAccessTokenCode(){
		AccessTokenCode atc = new AccessTokenCode();
		atc.setUsername("werrtt");
		atc.setClientId(GuidUtil.generateClientId());
		atc.setCode(GuidUtil.generateClientId());
		atc.setId(1);
		System.out.println(atc);
		accessTokenCode.update(atc);
	}
}
