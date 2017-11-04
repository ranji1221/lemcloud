/**
 * 删除弹框
 */
$('.user_removeAllBtn').bindDialogs({
	content : '你确定删除这些用户吗？',
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
	  		if($(this).closest('tr').attr("user_id")){
		  	str+=$(this).closest('tr').attr("user_id")+","
	  		}
	  		
	  	})
	  	str = str.substring(0,str.length-1)
		$.post("user/deleteAll",{
			user_ids:str,
		},function(data){
			if(data.success == true) {
				$('.alertArea').showAlert({content:'删除成功'});
				removeStorage();
				userListInit();
			}else{
				$('.alertArea').showAlert({content:'删除失败',type:'danger'});
			}
		},'json');
	}
})

$('.removeBtn').bindDialogs({
	content : '你确定删除这个用户吗？',
	success:function(handle){
		var userId = $(handle).closest('tr').attr('user_id');
		$.post("user/delete",{
			id:userId,
		},function(data){
			if(data.success == true) {
				$('.alertArea').showAlert({content:'删除成功'});
				removeStorage();
				userListInit();
			}else{
				$('.alertArea').showAlert({content:'删除失败',type:'danger'});
			}
		},'json');
	}
});