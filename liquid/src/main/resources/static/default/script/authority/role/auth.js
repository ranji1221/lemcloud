
/**
 * 添加角色查看授权事件
 */
$(".table").on("click",".roleAuth", function(e) {
	e.preventDefault();
	////获取到本地的某条数据
	var storage_name = $(this).closest('tr').attr('storage_name');
	var storage_id = $(this).closest('tr').attr('storage_id');
	var data = getDataByStorage(storage_name,storage_id);
	var class_name =  $("#role-authorization").find(".modal-contentbox").attr("maxClassName")
	$("#role-authorization").find(".modal-contentbox").appendTo(".ajax_dom").addClass(class_name)
	roleAuth(data);
});
function roleAuth(data){
	$("#auth_roleName").val(data.displayName).attr('role_id',data.id);
	createTreePlug('.role-authorization',data);
	$('.tree input').iCheck({
	    checkboxClass: 'icheckbox_flat-blue',
	    radioClass: 'iradio_flat-blue',
	    labelHover : true, 
	  	cursor : false,
	 });
	$('.checkallRoleaut,.slidedownallRoleaut').iCheck('uncheck')
}
$("#resRoleSubmit").on('click',function(){
	var request_data = {};
	request_data.roleId = $("#auth_roleName").attr('role_id');
	request_data.operationIds = jsTree_getSelectedOperationIds('.role-authorization');
	var url = 'role/auth';
	$.post(url,request_data,function(data){
		if(data.access == "unauthorized") {
			$("#authText").text("您没有角色授权权限")
			$("#powerModal").modal('show');
		} else {
			if(data.success == true) {
				$.ajax({
					url: "role/list"
				}).done(function(data) {
					removeStorage();
					$(".ajax_dom").empty()
					$('body #bodyModalArea').empty();
					$(data).appendTo($(".ajax_dom"))
					$('.alertArea').showAlert({
						content: '授权成功'
					});
				})
			}else{
				$.ajax({
					url: "role/list"
				}).done(function(data) {
					$(".ajax_dom").empty()
					$('body #bodyModalArea').empty();
					$(data).appendTo($(".ajax_dom"))
					$('.alertArea').showAlert({
						content: '授权失败',type:'danger'
					});
				})
			}
		}
	},'json');
})
//渲染树插件
var is_first_into_page = true;
function createTreePlug(className,data){
	//获取到所有操作并且放入缓存
	jsTree_getResourceAndOperationData();
	var url = 'role/getOperation';
	var request_data = {"roleId":data.id};
	if(!is_first_into_page){ $(className).jstree("destroy"); }
	is_first_into_page = false;
	$(className).jstree({
		'core' : {
			'themes':{'icons':false,"responsive":false},
	        'data' : function (obj, callback) {
	        	var resourceAndOperationData = jsTree_getResourceAndOperationData();
		        $.ajax({
		            type: "POST",
		            url:url,
		            data:request_data,
		            dataType:"json",
		            async: false,
		            success:function(data) {
		            	resourceAndOperationData = jsTree_selectedOperation(resourceAndOperationData,data);
		            	console.log(resourceAndOperationData,'做拼接操作！！！！！')
		            },
		            error:function(){
		            	console.log('获取本人所有的操作请求地址错误!:'+url);
		            }
		        });
	            callback.call(this, resourceAndOperationData);
	        }
	    },
		"plugins": ["checkbox"],
		"checkbox": {
			"keep_selected_style": false,
			'cascade_to_disabled':false,
			// 'tie_selection':false,
		},
		"check_callback" : true,
	});
	$(className).on("changed.jstree", function (e, data) {
		allResource = jsTree_DealRequest(getResourceAndOperationData());
		if(! (data.action == 'select_node' || data.action == 'deselect_node')) return false;
		var is_find = false;
		for(var i=0;i<allResource.length;i++){
			//找到选中的节点
			if(data.node.id == allResource[i].id){
				//如果是选中节点,判断是否有依赖节点
				if(data.action == 'select_node'){
					//找到它的依赖操作
					var operationRelyId = allResource[i].operationRId;
					if(operationRelyId > 0){
						operationRelyId = 'o_'+ operationRelyId;
						var operationRelyName = '';
						for(var a=0;a<allResource.length;a++){
							if(operationRelyId == allResource[a].id) {
								operationRelyName = allResource[a].resourceName;
							}
						}
						for(var j=0;j<data.selected.length;j++){
							if(operationRelyId == data.selected[j]) {
								is_find = true;
								break;
							}
						}
						if(!is_find){
							//如果没有找到,执行这个操作
							alert('本操作的依赖操作没有选择 ,请先选择依赖操作:'+operationRelyName);
							$(className).jstree(true).uncheck_node ([data.node.id]);
							return false;
						} 
					}
					break;
				}else if(data.action == 'deselect_node'){
					//找到谁关联着他,如果他作为依赖角色,那么他的子角色将取消选中
					// 1 获取到他的id
					thisOperationNode = allResource[i]
					thisOperationId = allResource[i].id;
					// 2 获取依赖他的ids    jsTree_getRelyOperation(){}
					var arr_tem = jsTree_getRelyOperation(thisOperationNode,allResource)
					// 3 判断他的依赖角色是否没有被选中  如果选择上,那么提示用户,需要先将依赖他的角色取消点击
					for(var k=0;k<data.selected.length;k++){
						for(var j=0;j<arr_tem.length;j++){
							if(data.selected[k] == arr_tem[j].id){
								alert(arr_tem[j].resourceName+' 依赖于本角色,请先将其删除')
								$(className).jstree(true).check_node ([thisOperationId]);
								return false;
							}
						}
					}
				}

			}
		}
	  
	}); 
}
//与树插件有关的操作
//获取资源和操作的树结构
function getResourceAndOperationData(){
	var resourceAndOperationData = getStorage('resourceAndOperationData');
	if(resourceAndOperationData){
		return resourceAndOperationData;
	}else{
        $.ajax({
            type: "POST",
            url:"resources/get/resourceAndOperation",
            dataType:"json",
            async: false,
            success:function(data) {
            	setStorage('resourceAndOperationData',data);
            	return data;
            },
            error:function(){
            	console.log('获取资源和操作的请求地址错误!:'+url);
            }
        });
	}
}
function jsTree_getResourceAndOperationData(){
   	data = jsTree_DealRequest(getResourceAndOperationData());
   	jsonarray = jsTree_DealTreeData(data);
   	return jsonarray;
}
function jsTree_DealRequest(data){
	if(! (data && data.operation)) return [];
	var operation = data.operation;
	var resource = data.resource;
	for(var i=0;i<resource.length; i++){
		resource[i].id = 'r_'+resource[i].id;
		resource[i].type='resource';
		if(resource[i].resourcePId > 0) resource[i].resourcePId = 'r_'+resource[i].resourcePId;
	}
	for(var i=0;i<operation.length;i++){
		var tem_data = {};
		tem_data.type='operation';
		tem_data.id = 'o_'+operation[i].id;
		tem_data.resourceName = operation[i].displayName;
		tem_data.resourcePId = 'r_'+operation[i].resourceId;
		tem_data.operationRId = operation[i].operationRId
		resource.push(tem_data);
	}
	return resource;
}
function jsTree_DealTreeData(data){
	var tree_data = [];
	for(var i=0;i<data.length;i++){
		tree_data.push(createJsTreeItem(data[i].id,data[i].resourcePId,data[i].resourceName));
	}
	return tree_data;
}
function jsTree_selectedOperation(resourceAndOperationData,selectedOperationData){
	for(var i=0;i<selectedOperationData.length;i++){
		for(var j=0;j<resourceAndOperationData.length;j++){
			if( 'o_'+selectedOperationData[i].id == resourceAndOperationData[j].id){
				resourceAndOperationData[j].state.selected = true;
				if(!selectedOperationData[i].state) resourceAndOperationData[j].state.disabled = true;
				continue;
			}
		}
	}
	return resourceAndOperationData;
}
//获取到 某操作的 所有依赖者
function jsTree_getRelyOperation(node,list){
	var arr_temp = [];
	for(var i = 0; i < list.length;i++){
		if(list[i].operationRId == jsTree_getOperationId(node.id)){
			arr_temp.push(list[i]);
			arr_temp.concat(jsTree_getRelyOperation(list[i],list));
		}
	}
	return arr_temp;
}
//根据节点id获取到当前实际id
function jsTree_getOperationId(id){
	return id.substr(2)
}
//全部选中操作
$('.maxcontainer .checkallRoleaut').on('ifChecked', function(event){
  	// $(".role-authorization-jstree").jstree(true).select_all()
  	$(".maxcontainer .role-authorization").jstree(true).check_all ()
  	console.log($('.role-authorization').jstree().select_node())
});
$('.maxcontainer .checkallRoleaut').on('ifUnchecked', function(event){
  	$(".maxcontainer .role-authorization").jstree(true).uncheck_all ()
  	$('.maxcontainer .role-authorization .jstree-disabled').each(function(){
		$(".maxcontainer .role-authorization").jstree(true).check_node($(this))
	})
  	// $(".maxcontainer .role-authorization-jstree").jstree(true).check_node('.jstree-disabled')
  	// $(".role-authorization-jstree").jstree(true).deselect_all()
});
//展开全部操作
$('.maxcontainer .slidedownallaut').on('ifChecked', function(event){
  	$(".maxcontainer .role-authorization").jstree(true).open_all()
});
$('.maxcontainer .slidedownallaut').on('ifUnchecked', function(event){
  	$(".maxcontainer .role-authorization").jstree(true).close_all()
});