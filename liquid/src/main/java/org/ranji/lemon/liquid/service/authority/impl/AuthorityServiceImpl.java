package org.ranji.lemon.liquid.service.authority.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.util.CollectionUtils;
import org.ranji.lemon.core.pagination.PagerModel;
import org.ranji.lemon.core.util.JsonUtil;
import org.ranji.lemon.liquid.dto.OperationDTO;
import org.ranji.lemon.liquid.model.authority.Operation;
import org.ranji.lemon.liquid.model.authority.Resource;
import org.ranji.lemon.liquid.model.authority.Role;
import org.ranji.lemon.liquid.model.authority.User;
import org.ranji.lemon.liquid.service.authority.prototype.IAuthorityService;
import org.ranji.lemon.liquid.service.authority.prototype.IOperationService;
import org.ranji.lemon.liquid.service.authority.prototype.IResourceService;
import org.ranji.lemon.liquid.service.authority.prototype.IRoleService;
import org.ranji.lemon.liquid.service.authority.prototype.IUserService;
import org.ranji.lemon.liquid.util.PinyinUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class AuthorityServiceImpl implements IAuthorityService{
	
	@Autowired
	private IUserService userService;
	@Autowired
	private IRoleService roleService;
	@Autowired
	private IResourceService resourceService;
	@Autowired
	private IOperationService operationService;

	
	private List<Role> roles ;    //存储角色
	private List<Role> recRole;  //存储递归角色
	private List<Operation> operations; //存储用户操操作集合
	private List<Operation> roleOperation ; //存储角色操作集合
	
	//查询用户的所有角色及父级角色
	@Override
	public  List<Role> findRolesByUserId(int userId){
		recRole = new ArrayList<Role>() ;
		roles = new ArrayList<Role>();
		List<Role> list = userService.findRoleByUserId(userId);
			for(Role r:list){
				roles.add(r);
				int pid = r.getRoleExtendPId();
				if(pid>=0){
				 List<Role> r1=findRolesByRoleId(pid);
				}
			}
			//集合去重
			Set<Role> set = new  HashSet<Role>(); 
	        List<Role> newList = new ArrayList<Role>(); 
	         for (Role r:roles) {
	            if(set.add(r)){
	                newList.add(r);
	            }
	        }
		return newList;
	}
	// 递归查询角色
	private List<Role> findRolesByRoleId(int roleId){
		Role r = roleService.find(roleId);
		recRole.add(r);
		if(r.getRoleExtendPId()>=0){
			findRolesByRoleId(r.getRoleExtendPId());
		}
		return recRole;
	}
	// 查询用户对应操作
	@Override
	public List<Operation> findOperationsByUserId(int userId) {
		roleOperation = new ArrayList<Operation>();
		operations= new ArrayList<Operation>();
		List <Role> roleIds = findRolesByUserId(userId);
		List <Operation> opera = new ArrayList<Operation>();//临时存储操作集合（单一角色）
		for (Role role:roleIds){
			 opera = roleService.findOperationByRoleId(role.getId());
			 opera.removeAll(operations); // 移除所有和operations中一致的操作
			 operations.addAll(opera); //将剩余操作加入集合中
		}
		return operations;
	}
	// 查询角色对应操作
	@Override
	public List<Operation> findOperationsByRoleId(int roleId){
		recRole = new ArrayList<Role>() ;
		roleOperation = new ArrayList<Operation>();
		roleOperation = roleService.findOperationByRoleId(roleId);
		for(Operation o :roleOperation){
			o.setState(true);
		}
		Role r = roleService.find(roleId);
		if(r.getRoleExtendPId()>=0){
			findRolesByRoleId(r.getRoleExtendPId());
		}
		for(Role r1 :recRole){
			List<Operation> opera = roleService.findOperationByRoleId(r1.getId());
			opera.removeAll(roleOperation); // 移除所有和operations中一致的操作
			roleOperation.addAll(opera); //将剩余操作加入集合中
		}
		return roleOperation;
	}
	// 角色授权
	@Override
	public void authRole(int roleId, List <Integer> array){
		roleService.deleteRoleAndOperationsRelationByRoleId(roleId); //删除角色与操作关系集
		for(Integer i : array){
			roleService.saveRoleAndOperationRelation(roleId, i); //保存角色操作关系
		}
	}
	// 用户授权
	@Override
	public void authUser(int userId, List <Integer> array){
		userService.deleteUserAndRolesByUserId(userId); //删除角色与用户关系集
		for(Integer i : array){
			userService.saveUserAndRoleRelation(userId, i);//保存用户角色关系
		}
	}
	
	//保存资源操作集
	@Override
	public void saveResourceAndOperation(Resource resource,String params){
		resourceService.save(resource);
		int resourceId = resource.getId();
		List<OperationDTO> operationList=conversionParams(params);
		for(OperationDTO s : operationList){
			saveOperation(resourceId,-1,s);		
		}
	}
	//更新资源操作集
	@Override
	public void updateResourceAndOperation(Resource resource,String params){
		resourceService.update(resource);
		int resourceId = resource.getId();
		operationService.deleteAllByResourceId(resourceId);
		List<OperationDTO> operationList=conversionParams(params);
		for(OperationDTO s : operationList){
			saveOperation(resourceId,-1,s);		
		}
	}
	
	//整理前台params 参数
	private List<OperationDTO> conversionParams(String params){
		List<OperationDTO> odList = new ArrayList<OperationDTO>();
		List<OperationDTO> operationList=JsonUtil.jsonToList(params, OperationDTO.class);
		for (OperationDTO o:operationList){
			if("".equals(o.getOperation())||"".equals(o.getPermission())){
				continue;
			}else{
				if("".equals(o.getRelyName())||"选择资源".equals(o.getRelyName())){
					odList.add(o);
					}
				for(OperationDTO od:operationList){
					if(o.getOperation().equals(od.getRelyName())){
						o.getList().add(od);
					}else{
						continue;
					}
				}
			}
		}
		return odList;
	}
	private void saveOperation(int resourceId,int rid,OperationDTO od){
		Operation operation =new Operation();
		operation.setResourceId(resourceId);
		operation.setDisplayName(od.getOperation());
		operation.setPermission(od.getPermission());
		operation.setOperationName(PinyinUtil.converterToSpell(operation.getDisplayName()).split(",")[0]);
		operation.setOperationRId(rid);
		operationService.save(operation);
		int relyId = operation.getId();
		if(!CollectionUtils.isEmpty(od.getList())){
			for(OperationDTO o: od.getList()){
				saveOperation(resourceId,relyId,o);
			}
		}
	}
	//查询所有资源（包含操作信息）
	@Override
	public String findAllResourceInduleOperation(String params) {
		try {
			ObjectMapper om = new ObjectMapper();
			Map<String, Object> map = new HashMap<String, Object>();
			// 当前只查询管理员
			if (!StringUtils.isEmpty(params)) {
				// 参数处理
				map = om.readValue(params, new TypeReference<Map<String, Object>>() {});
			}
			PagerModel<Resource	> pg = resourceService.findPaginated(map);
			List<Resource> resourceList = pg.getData();
			for(Resource r: resourceList){
				Map<String,Object> ma = new HashMap<String,Object>();
				ma.put("resourceId", r.getId());
				List<Operation> oper = operationService.findAll(ma);
				r.setOperationList(oper);
			}
			// 序列化查询结果为JSON
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("total", pg.getTotal());
			result.put("rows", resourceList);
			return om.writeValueAsString(result);
		} catch (Exception e) {
			e.printStackTrace();
			return "{ \"total\" : 0, \"rows\" : [] }";
		}
	}
	//查询所有用户（包含角色信息）
	@Override
	public String findAllUserInduleRoles(String params) {
		try {
			ObjectMapper om = new ObjectMapper();
			Map<String, Object> map = new HashMap<String, Object>();
			if (!StringUtils.isEmpty(params)) {
				// 参数处理
				map = om.readValue(params, new TypeReference<Map<String, Object>>() {});
			}
			PagerModel<User> pg = userService.findPaginated(map);
			List<User> userList = pg.getData();
			for(User u: userList){
				List<Role> roles = userService.findRoleByUserId(u.getId());
				u.setRoleList(roles);
			}
			//序列化查询结果为JSON
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("total", pg.getTotal());
			result.put("rows", userList);
			return om.writeValueAsString(result);
		} catch (Exception e) {
			e.printStackTrace();
			return "{ \"total\" : 0, \"rows\" : [] }";
		}
	}

	@Override
	public void deleteResource(int id) {
		resourceService.delete(id);
		operationService.deleteAllByResourceId(id);
	}
	
}


