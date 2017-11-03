
$(function(){
	//将编辑角色模态框加入到body中id为bodyModalArea的div中
	$('.right-container .modalToBody').appendTo('body #bodyModalArea');
})
/**
 * 编辑模态框
 * @returns
 */

//滑块 
function limitChangeLength(elm, limitLength) {
	$(elm).attr("maxLength", limitLength);
	$(elm).siblings(".limitlength").html($(elm).attr("maxlength") - $(elm).val().length);
	$(elm).keyup(function() {
		var length = $(elm).val().length;
		
		$(elm).siblings(".limitlength").html($(elm).attr("maxlength") - length);
	});
}


//角色编辑框 
function editRoleModal(data) {

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
function editSource(data) {
	dealDataToModal(data);
}
//用户编辑框
function editUser(data) {
	$("#edit_userId").val(data.id);
	$("#edit_userPass").val(data.userPass);
	$("#edit_userName").val(data.userName);
	$("#edit_phone").val(data.phone);
	$("#edit_email").val(data.email);

}
/* 弹出查看框 */
function viewRole(data) {
	console.log(data)
	//获取到本地的某条数据 示例代码
	$("#view_roleName").html(data.displayName);
	$("#view_roleMaxNum").html(data.roleMaxNum);
	data.rolePName?$("#view_rolePName").html(data.rolePName):$("#view_rolePName").html("无");
	data.roleRelyName?$("#view_roleRelyName").html(data.roleRelyName):$("#view_roleRelyName").html("无");
	$("#view_remarks").html(data.remarks);

}

function viewUser(data) {
	$("#view_userName").html(data.userName);
	$("#view_roleName").html(data.roleName);
	$("#view_phone").html(data.phone);
	$("#view_email").html(data.email);

}

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
/**
* 查看用户授权模态框
*/
function userAuth(data) {
	$("#auth_userName").val(data.userName);
}
//关闭
$(document).on("click.close",'.closeAction', function(e) {
	$(this).closest('.modal-contentbox').remove();
	var class_name = $(this).closest('.modal-contentbox').attr('narrowClassName')
	$(this).closest('.modal-contentbox').appendTo(class_name);
	$(".relateCtl [type='checkbox']").each(function(i,v){
		$(v).prependTo($(this).closest(".relateCtl"))
	})
	$(".relateCtl .icheckbox_flat-blue").detach()
	$(".relateCtl [type='checkbox']").prop("checked",false)
	if(!$('.ajax_dom').html()){
		$('.ajax_dom').hide()
	}
})
//限制输入字符
function limitChangeLength(elm, limitLength) {
	$(elm).attr("maxLength", limitLength);
	$(elm).keydown(function() {
		var length = $(elm).val().length;
		$(elm).siblings(".wordNum").html(limitLength - length);
	});
}

