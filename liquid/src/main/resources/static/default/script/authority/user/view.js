/**
 * 添加用户查看事件
 */
$("table").on("click",".viewUser", function(e) {
	e.preventDefault();
	////获取到本地的某条数据
	var storage_name = $(this).closest('tr').attr('storage_name');
	var storage_id = $(this).closest('tr').attr('storage_id');
	var data = getDataByStorage(storage_name,storage_id);
	var class_name =  $("#viewModal").find(".modal-contentbox").attr("maxClassName")
	$("#viewModal").find(".modal-contentbox").appendTo(".ajax_dom").addClass(class_name)
	viewUser(data);
});

function viewUser(data) {
	$("#view_userName").html(data.userName);
	var roleName = '';
	$.each(data.roleList, function(i,v) {
		roleName += v.displayName + ',';
	})
	roleName = roleName.substr(0,roleName.length-1);
	
	if(roleName.length){
		$("#view_roleName").html(roleName);
	}
	else {
		$("#view_roleName").html("无");
	}
	$("#view_roleName").html(data.roleName);
	$("#view_phone").html(data.phone);
	$("#view_email").html(data.email);
}
