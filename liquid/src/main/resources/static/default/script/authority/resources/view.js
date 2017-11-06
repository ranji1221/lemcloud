//添加查看事件
$("table").on("click",".viewResource", function(e) {
	e.preventDefault();
	////获取到本地的某条数据
	var storage_name = $(this).closest('tr').attr('storage_name');
	var storage_id = $(this).closest('tr').attr('storage_id');
	var data = getDataByStorage(storage_name,storage_id);
	var class_name =  $("#viewModal").find(".modal-contentbox").attr("maxClassName")
	$("#viewModal").find(".modal-contentbox").appendTo(".ajax_dom").addClass(class_name)
	viewSource(data);
	
});

function viewSource(data) {
	$("#view_resourceName").html(data.resourceName);
	if(data.resourceType == 1) {
		$("#view_resourceType").html("菜单");
	}
	else if(data.resourceType == 2) {
		$("#view_resourceType").html("元素");
	}
	else if(data.resourceType == 3) {
		$("#view_resourceType").html("文件");
	}
	else if(data.resourceType == 4) {
		$("#view_resourceType").html("操作");
	}
	$('#view_resourcePName').html(data.resourcePName);
	var operationName = '';
	$.each(data.operationList, function(i,v){
		operationName += v.displayName + ",";
	})
	operationName = operationName.substr(0,operationName.length-1);
	if(operationName.length){
		$("#view_operationName").html(operationName);
	}
	else {
		$("#view_operationName").html("无");
	}

}