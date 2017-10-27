package org.ranji.lemon.jersey.persist.auth.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.ranji.lemon.core.persist.impl.GenericDaoImpl;
import org.ranji.lemon.jersey.model.auth.Role;
import org.ranji.lemon.jersey.persist.auth.prototype.IRoleDao;
import org.springframework.stereotype.Repository;

@Repository("JerseyRoleDaoImpl")	//-- 为解决其他项目中也有项目的类名，则利用@Autowired自动注入冲突的问题
public class RoleDaoImpl extends GenericDaoImpl<Role, Integer> implements IRoleDao {

	@Override
	public void saveRoleAndPermissionRelation(int roleId, String permission) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("roleId", roleId);
		params.put("permission", permission);
		sqlSessionTemplate.insert(typeNameSpace + ".saveRoleAndPermissionRelation", params);
		
	}

	@Override
	public void deleteRoleAndPermissionRelation(int roleId, String permission) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("roleId", roleId);
		params.put("permission", permission);
		sqlSessionTemplate.delete(typeNameSpace + ".deleteRoleAndPermissionRelation", params);
		
	}

	@Override
	public void deleteRoleAndPermissionRelationByRoleId(int roleId) {
		sqlSessionTemplate.delete(typeNameSpace + ".deleteRoleAndPermissionRelationByRoleId", roleId);
		
	}

	@Override
	public List<String> findRoleAndPermissionRelationByRoleId(int roleId) {
		return sqlSessionTemplate.selectList(typeNameSpace + ".findRoleAndPermissionRelationByRoleId", roleId);
	}

}
