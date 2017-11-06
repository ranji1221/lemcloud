function resourceListInit(){
	$("#resourceList").LemonGetList({
		useLocalStorage: true,
		className_Page:"#page",
	    requestListUrl : 'resources/data',
	    generateItemFun : function(index,value,data,extend){
	   		var thisType = '';
	   		switch(value.resourceType){
	   			case 1:
	   				thisType = '菜单';
	   				break;
	   			case 2:
	   				thisType = '文件';
	   				break;
	   			default:
	   				thisType = '未知';
	   				break;
	   		}
	   		if(!value.resourcePName) value.resourcePName = '无';
	   		var tr_data = '<tr resource_id='+ value.id +extend+'>'+
	   			'<td class="checkboxtd">'+
	   				'<label>'+
	   					'<input  type="checkbox" name="layout"/>'+
	   				'</label>'+
	   			'</td>'+
	   			'<td>'+
	   				(index+1) +
	   			'</td>'+
	   			'<td title='+ thisType +'>'+
	   				thisType +
	   			'</td>'+
	   			'<td title='+ value.resourceName +'>'+
	   				value.resourceName +
	   			'</td>'+
	   			'<td title='+ value.resourcePName +'>'+
	   				value.resourcePName +
	   			'</td>'+
	   			'<td>'+
	   				'<span class="icon-eye-open iconact viewResource"></span>'+
	   			'</td>'+
	   			'<td>'+
	   				'<span class="icon-pencil iconact editResource"></span>'+
	   			'</td>'+
	   			'<td>'+
	   				'<span class="icon-trash iconact removeBtn"></span>'+
	   			'</td>'+
	   		'</tr>';
	       	return tr_data; 
   		},
   		afterFun : function(){
   		}
	})
}
resourceListInit();
$('.removeBtn').bindDialogs({
	content : '你确定删除这个资源吗？',
	success:function(handle){
		var resourceId = $(handle).closest('tr').attr('resource_id');
		$.post("resources/delete",{
			id:resourceId,
		},function(data){
			if(data.success == true) {
				$('.alertArea').showAlert({content:'删除成功'});
				removeStorage();
				resourceListInit();
			}else{
				$('.alertArea').showAlert({content:'删除失败',type:'danger'});
			}
		},'json');
	}
});

//刷新页面
$(".renovate").on("click",function(){
	removeStorage();
	resourceListInit();
})