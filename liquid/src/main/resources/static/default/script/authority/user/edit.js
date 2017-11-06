/**
 * 添加编辑事件
 */
$("table").on("click",".editUser", function(e) {
	e.preventDefault();
	////获取到本地的某条数据
	var storage_name = $(this).closest('tr').attr('storage_name');
	var storage_id = $(this).closest('tr').attr('storage_id');
	var data = getDataByStorage(storage_name,storage_id);
	var class_name =  $("#editModal").find(".modal-contentbox").attr("maxClassName")
	$("#editModal").find(".modal-contentbox").appendTo(".ajax_dom").addClass("editrolelg modalCon active")
	editUser(data);
});

function editUser(data) {
	$("#edit_userId").val(data.id);
	$("#edit_userPass").val(data.userPass);
	$("#edit_userName").val(data.userName);
	$("#edit_phone").val(data.phone);
	$("#edit_email").val(data.email);
}
$("#submit_editUser").on("click",function(){
	$.post("user/edit",
		{
			id:$("#edit_userId").val(),
			userName:$("#edit_userName").val(),
			userPass:$("#edit_userPass").val(),
			phone:$("#edit_phone").val(),
			email:$("#edit_email").val()
		},function(data){
			if(data.success){
				removeStorage();
				$(".ajax_dom").empty()
				$.ajax({
					url: "user/list"
				}).done(function(data) {
					$('body #bodyModalArea').empty();
					$(data).appendTo($(".ajax_dom"))
					$('.alertArea').showAlert({
						content: '修改成功'
					});
				})
			}
			else{
				removeStorage();
				$(".ajax_dom").empty()
				$.ajax({
					url: "user/list"
				}).done(function(data) {
					$('body #bodyModalArea').empty();
					$(data).appendTo($(".ajax_dom"))
					$('.alertArea').showAlert({
						content: '修改失败'
					});
				})
			}
		}
	,"json")
}) 
/**
 * 重置密码
 */

$('.inputwrappermax').on("click","#reset",function(){
	$.post("user/reset",
			{
				id:$("#edit_userId").val(),
			},function(data){
				if(data.success){
					$.ajax({
						url: "user/list"
					}).done(function(data) {
						removeStorage();
						$(".ajax_dom").empty()
						$('body #bodyModalArea').empty();
						$(data).appendTo($(".ajax_dom"))
						$('.alertArea').showAlert({
							content: '重置密码成功'
						});
					})
				}
				else{
					$.ajax({
						url: "user/list"
					}).done(function(data) {
						$(".ajax_dom").empty()
						$('body #bodyModalArea').empty();
						$(data).appendTo($(".ajax_dom"))
						$('.alertArea').showAlert({
							content: '重置密码失败'
						});
					})
				}
			}
		,"json")
})