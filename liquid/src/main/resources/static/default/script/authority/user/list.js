function userListInit(){
	$("#userList").LemonGetList({
	    requestListUrl : 'user/data',
		useLocalStorage: true,
		className_Page:"#page",
		generateItemFun : function(index,value,data,extend){
			var phone = value.phone == null ? '无':value.phone ;
			var email = value.email == null ? '无':value.email ;
			var createTime = value.createTime == null ? '无':(''+value.createTime).substr(0,10);
			tr_data = '<tr user_id='+ value.id +extend+'>'+
				'<td class="checkboxtd">'+
					'<label>'+
						'<input  type="checkbox" name="layout"/>'+
					'</label>'+
				'</td>'+
				'<td>'+
					(index+1) +
				'</td>'+
				'<td title='+ value.userName +'>'+
					value.userName +
				'</td>'+
				'<td title='+ phone +'>'+
					phone +
				'</td>'+
				'<td title='+ email +'>'+
					email +
				'</td>'+
				'<td title='+ getDateByTimestamp(createTime*1000) +'>'+
					getDateByTimestamp(createTime*1000) +
				'</td>'+
				'<td>'+
					'<span class="icon-eye-open iconact viewUser"></span>'+
				'</td>'+
				'<td>'+
					'<span class="icon-pencil iconact editUser"></span>'+
				'</td>'+
				'<td>'+
					'<span class="icon-trash iconact removeBtn"></span>'+
				'</td>'+
				'<td>'+
					'<span class="icon-key iconact userAuth"></span>'+
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
	})
}
userListInit();
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
//刷新页面
$(".renovate").on("click",function(){
	removeStorage();
	userListInit();
})