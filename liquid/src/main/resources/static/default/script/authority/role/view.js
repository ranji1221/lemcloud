/**
 * 添加角色查看事件
 */
$(".table").on("click",".viewRole", function(e) {
	e.preventDefault();
	////获取到本地的某条数据
	var storage_name = $(this).closest('tr').attr('storage_name');
	var storage_id = $(this).closest('tr').attr('storage_id');
	var data = getDataByStorage(storage_name,storage_id);
	//添加页面和加样式
	var class_name =  $("#viewModal").find(".modal-contentbox").attr("maxClassName")
	$("#viewModal").find(".modal-contentbox").appendTo(".ajax_dom").addClass(class_name)
	viewRole(data);
});

function viewRole(data) {
	//获取到本地的某条数据 示例代码
	$("#view_roleName").html(data.displayName);
	$("#view_roleMaxNum").html(data.roleMaxNum);
	data.rolePName?$("#view_rolePName").html(data.rolePName):$("#view_rolePName").html("无");
	data.roleRelyName?$("#view_roleRelyName").html(data.roleRelyName):$("#view_roleRelyName").html("无");
	$("#view_remarks").html(data.remarks);
}