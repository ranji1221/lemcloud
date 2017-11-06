function roleListInit(){
	$("#rolesList").LemonGetList({
		useLocalStorage : true,
	    requestListUrl : 'role/data',
	    className_Page : "#page",
	    generateItemFun : function(index,value,data,extend){
			var Pname = value.rolePName == null ? '无':value.rolePName ;
			var tr_data = '<tr listid='+ value.id+extend +' role_id='+ value.id+extend +'>'+
				'<td class="checkboxtd">'+
					'<label>'+
						'<input  type="checkbox" name="layout"/>'+
					'</label>'+
				'</td>'+
				'<td>'+
					(index+1) +
				'</td>'+
				'<td title='+ value.displayName +'>'+
					value.displayName +//${role.roleName}
				'</td>'+
				'<td title='+ Pname +'>'+
				     Pname +
				'</td>'+
				'<td>'+
					'<span class="icon-eye-open iconact viewRole"></span> '+
				'</td>'+
				'<td>'+
					'<span class="icon-pencil iconact role_editRole"></span>'+
				'</td>'+
				'<td>'+
					'<span class="icon-trash iconact removeBtn"></span>'+
				'</td>'+
				'<td>'+
					'<span class="icon-key iconact roleAuth"></span>'+
				'</td>'+
			'</tr>';
	       	return tr_data; 
   		},
   		afterFun : function(){
			$('.tablewrap input').iCheck({
			    checkboxClass: 'icheckbox_flat-blue',
			    radioClass: 'iradio_flat-blue',
			    labelHover : true, 
			  	cursor : false,
			 });
			$('#checkall').iCheck('uncheck')
   		},
   		initFun : function(){
   			_this = this;
   			$('.nav-search').on('click',"#list_search_btn",function(){
   				//刷新当前对象
   				_this.getData({
   					'params':JSON.stringify({
   						'dNameLike' : $('.nav-search #list_search_str').val()
   					})
   				},true);
   			})
   		}
	})
}
roleListInit();
/**
* 删除弹框
*/
$('.role_removeAllBtn').bindDialogs({
		content : '你确定删除这些角色吗？',
		name_successBtn : 'deleteAllBtn',
		name_cancelBtn : 'cancelAllBtn',
		beforeFun:function(){
			if($(".tablewrap input:checked").length){
				return true;
			}else{
				return false;
			}
		},
		success:function(){
			var str="";
		  	$(".tablewrap input:checked").each(function(i,v){
		  		if($(this).closest('tr').attr("role_id")){
			  	str+=$(this).closest('tr').attr("role_id")+","
		  		}
		  	})
		  	str = str.substring(0,str.length-1)
			$.post("role/deleteAll",{
				role_ids:str,
			},function(data){
				if(data.success == true) {
					$('.alertArea').showAlert({content:'删除成功'});
					removeStorage();
					roleListInit();
				}else{
					$('.alertArea').showAlert({content:'删除失败',type:'danger'});
				}
			},'json');
		}
	})

$('.removeBtn').bindDialogs({
	content : '你确定删除这个角色吗？',
	success:function(handle){
		var roleId = $(handle).closest('tr').attr('role_id');
		$.post("role/delete",{
			id:roleId,
		},function(data){
			if(data.success == true) {
				$('.alertArea').showAlert({content:'删除成功'});
				removeStorage();
				roleListInit();
			}else{
				$('.alertArea').showAlert({content:'删除失败',type:'danger'});
			}
		},'json');
	}
});
//刷新页面
$(document).on("click",".renovate",function(){
	removeStorage();
	roleListInit();
})