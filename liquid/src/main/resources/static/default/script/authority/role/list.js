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