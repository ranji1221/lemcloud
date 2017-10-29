//添加编辑事件
$(document).on("click", ".editResource", function(e) {
	e.preventDefault();
	////获取到本地的某条数据
	var storage_name = $(this).closest('tr').attr('storage_name');
	var storage_id = $(this).closest('tr').attr('storage_id');
	var data = getDataByStorage(storage_name,storage_id);
	var class_name =  $("#editModal").find(".modal-contentbox").attr("maxClassName")
	$("#editModal").find(".modal-contentbox").appendTo(".ajax_dom").addClass(class_name)
	editSource(data);
});
//添加查看事件
$(document).on("click", ".viewResource", function(e) {
	e.preventDefault();
	////获取到本地的某条数据
	var storage_name = $(this).closest('tr').attr('storage_name');
	var storage_id = $(this).closest('tr').attr('storage_id');
	var data = getDataByStorage(storage_name,storage_id);
	var class_name =  $("#viewModal").find(".modal-contentbox").attr("maxClassName")
	$("#viewModal").find(".modal-contentbox").appendTo(".ajax_dom").addClass(class_name)
	
	viewSource(data);
});