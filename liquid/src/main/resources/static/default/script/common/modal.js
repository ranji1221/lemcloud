
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
/**
 * 刷新页面
 */	
$('.tfoot').on("click",".renovate",function(){
	removeStorage();
	roleListInit();
});

/**
 * 复选框
 */
$('.tablewrap input').iCheck({
    checkboxClass: 'icheckbox_flat-blue',
    radioClass: 'iradio_flat-blue',
    labelHover : true, 
  	cursor : false,
 });
$(document).on('ifChecked','#checkall', function(event){
  	$('.tablewrap input').iCheck('check')
});
	$(document).on('ifUnchecked', '#checkall',function(event){
  	$('.tablewrap input').iCheck('uncheck')
});

function editSource(data) {
	dealDataToModal(data);
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

