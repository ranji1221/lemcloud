package org.ranji.lemon.jersey.persist.auth.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.ranji.lemon.core.persist.impl.GenericDaoImpl;
import org.ranji.lemon.jersey.model.auth.Role;
import org.ranji.lemon.jersey.model.auth.User;
import org.ranji.lemon.jersey.persist.auth.prototype.IUserDao;
import org.springframework.stereotype.Repository;

@Repository("JerseyUserDaoImpl") //-- 为解决其他项目中也有项目的类名，则利用@Autowired自动注入冲突的问题
public class UserDaoImpl extends GenericDaoImpl<User, Integer> implements IUserDao {

	@Override
	public void saveUserAndRoleRelation(int userId, int roleId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userId", userId);
		params.put("roleId", roleId);
		sqlSessionTemplate.insert(typeNameSpace + ".saveUserAndRoleRelation", params);	
	}

	@Override
	public void deleteUserAndRoleRelation(int userId, int roleId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userId", userId);
		params.put("roleId", roleId);
		sqlSessionTemplate.delete(typeNameSpace + ".deleteUserAndRoleRelation", params);	
	}

	@Override
	public void deleteUserAndRolesRelationByUserId(int userId) {
		sqlSessionTemplate.delete(typeNameSpace + ".deleteUserAndRolesRelationByUserId", userId);
		
	}

	@Override
	public List<Integer> findUserRolesRelationByUserId(int userId) {
		return sqlSessionTemplate.selectList(typeNameSpace + ".findUserRolesRelationByUserId", userId);
	}
	
	@Override
	public List<Role> findRoleByUserId(int userId){
		return sqlSessionTemplate.selectList(typeNameSpace + ".findRoleByUserId", userId);
	}
}
