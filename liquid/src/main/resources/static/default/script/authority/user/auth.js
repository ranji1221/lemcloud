/**
 * 添加查看用户授权事件
 */
$("table").on("click",".userAuth", function(e) {
	e.preventDefault();
	////获取到本地的某条数据
	var storage_name = $(this).closest('tr').attr('storage_name');
	var storage_id = $(this).closest('tr').attr('storage_id');
	var data = getDataByStorage(storage_name,storage_id);
	var class_name =  $("#user-authorization").find(".modal-contentbox").attr("maxClassName")
	$("#user-authorization").find(".modal-contentbox").appendTo(".ajax_dom").addClass(class_name).attr("id",class_name)
	userAuth(data);
});
function userAuth(data) {
	$("#auth_userName").val(data.userName).attr('user_id',data.id);
	createTreePlug('.user-authorizationtree',data);
	$('.tree input').iCheck({
	    checkboxClass: 'icheckbox_flat-blue',
	    radioClass: 'iradio_flat-blue',
	    labelHover : true, 
	  	cursor : false,
	 });
	$('.checkallRoleaut,.slidedownallRoleaut').iCheck('uncheck')
}
$("#resUserSubmit").on('click',function(){
	var request_data = {};
	request_data.userId = $("#auth_userName").attr('user_id');
	request_data.roleIds = jsTree_getSelectedNodes('.user-authorizationtree');
	var url = 'user/auth';
	$.post(url,request_data,function(data){
		if(data.success == true) {
			$.ajax({
				url: "user/list"
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
				url: "user/list"
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
	//将角色列表初始化到本地存储中
	getRoleData();
	var url = 'user/getRole';
	var request_data = {"id":data.id};
	
	if(!is_first_into_page) {$(className).jstree("destroy");}
	is_first_into_page = false;
	$(className).jstree({
		'core' : {
			'themes':{'icons':false,"responsive":false},
	        'data' : function (obj, callback) {
	        	var jstree_roleData = jsTree_dealRequestByRoleData(getRoleData());
	        	console.log('jstree_roleData',jstree_roleData);
		        $.ajax({
		            type: "POST",
		            url:url,
		            data:request_data,
		            dataType:"json",
		            async: false,
		            success:function(data) {
		            	role_data = jsTree_selectedOperation(jstree_roleData,data);
		            },
		            error:function(){
		            	console.log('获取本人所有的操作请求地址错误!:'+url);
		            }
		        });
	            callback.call(this, role_data);
	        }
	    },
		"plugins": ["checkbox"],
		"checkbox": {
			"keep_selected_style": false,
			'cascade_to_disabled':false,
			'three_state':false,
		},
		"check_callback" : true,
	});
	$(className).on("changed.jstree", function (e, data) {
		allRole = getListByTree(getRoleData());
		if(! (data.action == 'select_node' || data.action == 'deselect_node')) return false;
		var is_find = false;
		for(var i=0;i<allRole.length;i++){
			//找到选中的节点
			if(data.node.id == allRole[i].id){
				//如果是选中节点,判断是否有依赖节点
				if(data.action == 'select_node'){
					//找到它的依赖角色
					var roleRelyId = allRole[i].roleRelyId;
					if(roleRelyId > 0){
						var roleRelyName = '';
						for(var a=0;a<allRole.length;a++){
							if(roleRelyId == allRole[a].id) {
								roleRelyName = allRole[a].displayName;
							}
						}
						for(var j=0;j<data.selected.length;j++){
							if(roleRelyId == data.selected[j]) {
								is_find = true;
								break;
							}
						}
						if(!is_find){
							//如果没有找到,执行这个操作
							$('.authAlertArea').showAlert({
								type: 'warning',
								content: '本角色的依赖角色没有选择 ,请先选择依赖角色:'+roleRelyName
							});
							$(className).jstree(true).uncheck_node ([data.node.id]);
							return false;
						} 
					}
					break;
				}else if(data.action == 'deselect_node'){
					//找到谁关联着他,如果他作为依赖角色,那么他的子角色将取消选中
					// 1 获取到他的id
					thieRoleId = allRole[i].id;
					// 2 获取依赖他的ids
					var arr_relyedIds = [];
					for(var k=0;k<allRole.length;k++){
						if(allRole[k].roleRelyId > 0) {
							arr_relyedIds.push(allRole[k]);
						}
					}
					// 3 判断他的依赖角色是否没有被选中  如果选择上,那么提示用户,需要先将依赖他的角色取消点击
					for(var k=0;k<data.selected.length;k++){
						for(var j=0;j<arr_relyedIds.length;j++){
							if(data.selected[k] == arr_relyedIds[j].id){
								$('.authAlertArea').showAlert({
									type: 'warning',
									content: arr_relyedIds[j].displayName+' 依赖于本角色,请先将其删除'
								});
								$(className).jstree(true).check_node ([thieRoleId]);
								return false;
							}
						}
					}
				}

			}
		}
	  
	});  
}

//与树插件有关的方法
//获取角色列表
function getRoleData(){
	var resourceAndOperationData = getStorage('roleListData');
	if(resourceAndOperationData){
		return resourceAndOperationData;
	}else{
        $.ajax({
            type: "POST",
            url:"role/listAll",
            dataType:"json",
            async: false,
            success:function(data) {
            	setStorage('roleListData',data);
            	return data;
            },
            error:function(){
            	console.log('获取角色的请求地址错误!:'+url);
            }
        });
	}
}
function jsTree_dealRequestByRoleData(data){
    var	role_list = getListByTree(data);
    var role_form = [];
	for(var i=0;i<role_list.length;i++){
		role_form.push(createJsTreeItem(role_list[i].id,role_list[i].roleExtendPId,role_list[i].displayName));
	}
	return role_form;
}
function jsTree_selectedOperation(jstree_roleData,data){
	var tem_user_role = [];
	for(var i=0;i<data.length;i++){
		tem_user_role.push(createJsTreeItem(data[i].id,'','',true));
	}
	return selectJsTree(jstree_roleData,tem_user_role);
}
