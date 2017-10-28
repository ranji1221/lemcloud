package org.ranji.lemon.liquid.service.authority.impl;



import java.util.ArrayList;
import java.util.List;

import org.ranji.lemon.core.service.impl.GenericServiceImpl;
import org.ranji.lemon.liquid.model.authority.Operation;
import org.ranji.lemon.liquid.model.authority.Resource;
import org.ranji.lemon.liquid.persist.authority.prototype.IOperationDao;
import org.ranji.lemon.liquid.service.authority.prototype.IOperationService;
import org.ranji.lemon.liquid.service.authority.prototype.IResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OperationServiceImpl extends GenericServiceImpl<Operation, Integer> implements IOperationService {
	
	@Autowired
	private IResourceService resourceService;
	
	private List<Operation> operation = new ArrayList<Operation>(); //保存操作集合
	private List<Resource> resource = new ArrayList<Resource>(); //保存资源集合

	@Override
	public List<Resource> findResourceTree(){
		List<Resource> resourceList = resourceService.findAll();
		List<Operation> opera = findAll();
		for(Operation o : opera){
			if(o.getOperationPId()==-1){
				operation.add(o);
				for(Resource r : resourceList){
					if(r.getId()==o.getResourceId()){
						r.getOperationList().add(o);
					}
				}	
			}
			for(Operation o1 : opera){
				if(o1.getOperationPId()==o.getId()){
					o.getOperationList().add(o1);
				}
			}
		}
		return resourceList;
	}

	@Override
	public void deleteAllByResourceId(int resourceId) {
		((IOperationDao)dao).deleteAllByResourceId(resourceId);
	}
}
