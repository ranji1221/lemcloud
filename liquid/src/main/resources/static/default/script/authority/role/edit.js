/**
 * 添加角色编辑事件
 */
$(".table").on("click",".role_editRole", function(e) {
	e.preventDefault();
	////获取到本地的某条数据
	var storage_name = $(this).closest('tr').attr('storage_name');
	var storage_id = $(this).closest('tr').attr('storage_id');
	var data = getDataByStorage(storage_name,storage_id);
	if($(this).attr('mintype')==1){
		return
	}else{
		var editRole_id=$(this).closest('tr').attr('listid');
		editRoleModal(editRole_id);
	}
	var class_name =  $("#editModal").find(".modal-contentbox").attr("maxClassName")
	$("#editModal").find(".modal-contentbox").appendTo(".ajax_dom").addClass(class_name)
	editRoleModal(data);
});

function editRoleModal(data) {
	console.log(data)
	limitChangeLength($(".sliderInput input[type='text']"),12)
	var maxNum = data.roleMaxNum;
	var maxLimitNum = 10;
	var add = $(".numCtr .add");
	var sub = $(".numCtr .reduce ");
	var grey = '#bdc3c7';
	var blue = '#378ef8';
	judge(maxNum)
	//加减按钮
	var limitNum;
	if(parseInt(maxNum) == 0) {
		limitNum = 0;
	} else if(parseInt(maxNum)) {
		limitNum = parseInt(maxNum);
	} else {
		limitNum = parseInt($("#limitNum").val()) || 0;
	}
	function judge(limitNum) {
		if(maxLimitNum <= limitNum) {
			$(add).css("background", grey);
			$(sub).css("background", blue);
		} else if(limitNum < 1) {
			$(sub).css("background", grey);
			$(add).css("background", blue);
		} else {
			$(add).css("background", blue);
			$(sub).css("background", blue);
		}
	}
	
	$(sub).off("click")
	$(add).off("click")
	$(sub).click(function(e) {
		e.preventDefault();
		var numVal = parseInt($("#edit_roleMaxNum").val());
		if(numVal > 0) {
			numVal--;
			$("#edit_roleMaxNum").val(numVal);
		}
		var inputlimitNum = parseInt($(".numCtr input").val());
		judge(inputlimitNum);
	});
	$(add).click(function(e) {
		e.preventDefault();
		var numVal = parseInt($("#edit_roleMaxNum").val());
		if(numVal < 10) {
			numVal++;
			$("#edit_roleMaxNum").val(numVal);
		}
		var inputlimitNum = parseInt($(".numCtr input").val());
		judge(inputlimitNum);
	});
	dealDataToModal(data); 
	limitChangeLength($(".sliderInput input[type='text']"),12)
}
var userData;
window.dealDataToModal = function(data){
	userData = data;
	//获取到本地的某条数据 示例代码
	$("#edit_roleId").val(data.id);
	$("#edit_displayName").val(data.displayName);
	$("#edit_roleMaxNum").val(data.roleMaxNum);
	$("#edit_remarks").val(data.remarks);
	$('.select_roleList').LemonGetList({
		requestListUrl:'role/listAll',
		beforeFun:function(data){
			return getListByTree(data)
		},
		generateItemFun:function(index,value){
			var itemHtml = '';
			if(index == 0 ){ itemHtml += '<option value="-1">选择角色</option>';}
			
			var kongge_str = '';
			for(var i=0;i<value.level;i++){
				kongge_str += '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;';
			}
			kongge_str += '|-';
			
			itemHtml += '<option  value="'+value.id+'">'+kongge_str+value.displayName+'</option>';
			
			return itemHtml;
		},
		afterFun:function(){
			//.roleExtendPId
			if(userData.roleExtendPId > 0){
				$('#edit_roleExtendPId option').each(function(index,val){
					if($(val).attr('value') == userData.roleExtendPId){
						$(val).attr('selected','selected');
					}
				})
			}
			
			if(userData.roleRelyId > 0){
				$('#edit_roleRelyId option').each(function(index,val){
					if($(val).attr('value') == userData.roleRelyId){
						$(val).attr('selected','selected');
					}
				})
			}
		}
	})
}
$("#submit_editRole").on("click",function(){
	$.post("role/edit",
		{
			id:$("#edit_roleId").val(),
			displayName:$("#edit_displayName").val(),
			roleMaxNum:$("#edit_roleMaxNum").val(),
			remarks:$("#edit_remarks").val(),
			roleExtendPId:$("#edit_roleExtendPId option:selected").val(),
			roleRelyId:$("#edit_roleRelyId option:selected").val()
		},function(data){
			if(data.success){
				$.ajax({
					url: "role/list"
				}).done(function(data) {
					removeStorage();
					$(".ajax_dom").empty()
					$('body #bodyModalArea').empty();
					$(data).appendTo($(".ajax_dom"))
					$('.alertArea').showAlert({
						content: '修改成功'
					});
				})
			}
			else{
				$.ajax({
					url: "role/list"
				}).done(function(data) {
					$(".ajax_dom").empty()
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