package org.ranji.jersey.test.auth;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.ranji.lemon.jersey.JerseyApplication;
import org.ranji.lemon.jersey.model.auth.Role;
import org.ranji.lemon.jersey.persist.auth.prototype.IRoleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=JerseyApplication.class)  //-- 指定Spring-Boot的启动类
public class RoleDaoTest {
	
	@Autowired
	IRoleDao roleDao;
	
	@Test
	public void testAddRole(){
		Role r = new Role();
		r.setRoleName("wwwwwww");
		r.setDescription("234");
		System.out.println(r);
		roleDao.save(r);
	}
	
	@Test
	public void testFindRole(){
		Role r = roleDao.find(1);
		System.out.println(r);
		
	}
	@Test
	public void testUpdateRole(){
		Role r = new Role();
		r.setRoleName("wrerr");
		r.setId(1);
		roleDao.update(r);
	}
	@Test
	public void testAddRolePermission(){
		roleDao.saveRoleAndPermissionRelation(1, "wwwww");
		roleDao.saveRoleAndPermissionRelation(1, "aaaaa");
	}
	@Test
	public void testDeleteRoleAndPermissionRelation(){
		roleDao.deleteRoleAndPermissionRelation(1, "aaaaa");
	}
	@Test
	public void testDeleteRoleAndPermissionRelationByRoleId(){
		roleDao.deleteRoleAndPermissionRelationByRoleId(1);
	}
	@Test
	public void testFindRoleAndPermissionRelationByRoleId(){
		List<String> list = roleDao.findRoleAndPermissionRelationByRoleId(1);
		for(String s:list){
			System.out.println(s);
		}
	}
}
