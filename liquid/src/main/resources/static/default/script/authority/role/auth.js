
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
	console.log(data)
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
