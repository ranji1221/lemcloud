package org.ranji.jersey.test.auth;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.ranji.lemon.jersey.JerseyApplication;
import org.ranji.lemon.jersey.model.auth.Role;
import org.ranji.lemon.jersey.model.auth.User;
import org.ranji.lemon.jersey.persist.auth.prototype.IUserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=JerseyApplication.class)  //-- 指定Spring-Boot的启动类
public class UserDaoTest {
	
	@Autowired
	IUserDao userDao;
	
	@Test
	public void testAddUser(){
		User u = new User();
		u.setUsername("jianbo");
		u.setPassword("123456");
		System.out.println(u.getCreateTime());
		System.out.println(u);
		userDao.save(u);
	}
	
	@Test
	public void testFindUser(){
		User u = userDao.find(1);
		System.out.println(u);
		
	}
	@Test
	public void testAddUserAndRole(){
		userDao.saveUserAndRoleRelation(1, 2);
	}
	@Test
	public void testDeleteUserAndRoleRelation(){
		userDao.deleteUserAndRoleRelation(1, 2);
	}
	@Test
	public void testDeleteUserAndRolesRelationByUserId(){
		userDao.deleteUserAndRolesRelationByUserId(1);
	}
	@Test
	public void testFindUserRolesRelationByUserId(){
		List<Integer> list = userDao.findUserRolesRelationByUserId(1);
		for(Integer i:list){
			System.out.println(i);
		}
	}
	@Test
	public void testFindRoleByUserId(){
		List<Role> role = userDao.findRoleByUserId(1);
		for(Role r: role){
			System.out.println(r);
		}
	}
}
