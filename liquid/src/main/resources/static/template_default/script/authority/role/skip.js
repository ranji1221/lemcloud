//添加编辑事件
$(document).on("click", ".editRole", function(e) {
	e.preventDefault();
	//获取到本地的某条数据
	var storage_name = $(this).closest('tr').attr('storage_name');
	var storage_id = $(this).closest('tr').attr('storage_id');
	var data = getDataByStorage(storage_name,storage_id);
	if($(this).attr('mintype')==1){
		return
	}else{
		//var editRole_id=$(this).closest('tr').attr('listid');
		//editRole(editRole_id);
	}
	var class_name =  $("#editModal").find(".modal-contentbox").attr("maxClassName")
	$("#editModal").find(".modal-contentbox").appendTo(".ajax_dom").addClass(class_name)
	editRole(data);
});
//添加查看事件
$(document).on("click", ".viewRole", function(e) {
	e.preventDefault();
	//获取到本地的某条数据
	var storage_name = $(this).closest('tr').attr('storage_name');
	var storage_id = $(this).closest('tr').attr('storage_id');
	var data = getDataByStorage(storage_name,storage_id);
	//添加页面和加样式
	var class_name =  $("#viewModal").find(".modal-contentbox").attr("maxClassName")
	$("#viewModal").find(".modal-contentbox").appendTo(".ajax_dom").addClass(class_name)
	viewRole(data);
});
//添加查看角色授权事件
$(document).on("click", ".roleAuth", function(e) {
	e.preventDefault();
	//获取到本地的某条数据
	var storage_name = $(this).closest('tr').attr('storage_name');
	var storage_id = $(this).closest('tr').attr('storage_id');
	var data = getDataByStorage(storage_name,storage_id);
	var class_name =  $("#role-authorization").find(".modal-contentbox").attr("maxClassName")
	$("#role-authorization").find(".modal-contentbox").appendTo(".ajax_dom").addClass(class_name)
	roleAuthInit(data);
});

