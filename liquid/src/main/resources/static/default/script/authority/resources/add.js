$('#add_resourcePId').LemonGetList({
		requestListUrl:'resources/listAll',
		beforeFun:function(data){
			return getListByTree(data);
		},
		generateItemFun:function(index,value){
			console.log(value)
			var itemHtml = '';
			if(index == 0 ){ itemHtml += '<option value="-1">选择资源</option>';}
			
			var kongge_str = '';
			for(var i=0;i<value.level;i++){
				kongge_str += '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;';
			}
			kongge_str += '|-';
			
			itemHtml += '<option  value="'+value.id+'" ';
			itemHtml += ' >'+kongge_str+value.resourceName+'</option>';
			return itemHtml;
		},
		emptyDataFun : function(){
			return '<option value="-1">没有数据</option>';
		}
	})
	$("[type='checkbox']").iCheck({
		checkboxClass: 'icheckbox_flat-blue',
		increaseArea: '20%' // optional
	});
	$(".sliderInput").css("width","0");
	var minlimitNum=5;
	$(".minlimitNum").html(minlimitNum);
//	var docu_w = parseInt($(".sliderInput").css("width"));
	$(".error_box").slider({
		orientation: "horizontal",
		range: "min",
		value: 70,
		max:70,
		slide: function(event, ui) {
	var ui_value = ui.value
			$(".sliderInput").css("width",ui_value+"%");
			$(".minlimitNum").html(minlimitNum+parseInt(ui_value/10)-$(".sliderInput").find("input").val().length);
			$(".sliderInput").find("input").val($(".sliderInput").find("input").val().slice(0,minlimitNum+parseInt(ui_value/10)))
			$(".sliderInput").find("input").prop("maxlength",minlimitNum+parseInt(ui_value/10))
			limitChangeLength($(".form_input input"),minlimitNum+parseInt(ui_value/10) );
		}
	})
	$(".sliderInput").css("width", $(".error_box").slider("value")+"%");
	$(".minlimitNum").html(minlimitNum + parseInt($(".error_box").slider("value") / 10))
	

	function limitChangeLength(elm, limitLength) {
		$(elm).attr("maxLength", limitLength);
		$(elm).keyup(function() {
			var length = $(elm).val().length;
			$(elm).siblings("span").html(limitLength - length);
		});
	}
	limitChangeLength($(".form_input .resources_name"),parseInt($(".minlimitNum").html()))
	$(".resourcebtnbox").on("click.submit_add","#submit_addResource",function(){
	/*	var tem_str = '';
		$("#add_operation input:checked").each(function(){
			if(!tem_str) {
				tem_str += $(this).val();
			}else{
				tem_str +=',' + $(this).val();			
			}
		})*/
		var params = [];	
		var permissionList=document.getElementsByClassName("add_permission");
		var operationList=document.getElementsByClassName("add_operations");
		//var index_i = 0
		for(var i=0;i<permissionList.length;i++){
			var operations = {};
			operations.operation=operationList[i].value;
			operations.permission=permissionList[i].value;
			params.push(operations);
			}
	 $.post("resources/save",
		{
			resourceName:$("#add_resourceName").val().trim(),
			/*permission:$(".add_permission").val().trim(),
			operation:$(".add_operation").val().trim(),*/
			resourceType:$("#add_resourceType option:selected").val().trim(),
			resourcePId:$("#add_resourcePId option:selected").val().trim(),
			params
		},function(data){
			if(data.success){
				removeStorage();
				
				$.ajax({
					url:"resources/list"
				}).done(function(data){
					$(".ajax_dom").empty()
					$(data).appendTo($(".ajax_dom"))
				})
				$('.alertArea').showAlert({content:'添加成功'});
			}
			else{

				$.ajax({
					url:"resources/list"
				}).done(function(data){
					$(".ajax_dom").empty()
					$(data).appendTo($(".ajax_dom"))
				})
				$('.alertArea').showAlert({content:'添加失败'});
			}
		}
	,"json")
})
$(function(){
	function ifcheck(){
		if($(".operation_permission").length == 1){
			$(".operation_permission").find(".phone .input_add_span,.input_remove_span").show()
		}else{
			$(".operation_permission").find(".phone .input_add_span,.input_remove_span").hide()
			var last_oper = $(".operation_permission").get()[$(".operation_permission").length-1]
			$(last_oper).find(".phone .input_add_span,.input_remove_span").show()
		}
		}
	ifcheck()
	$(".form_content").on("click",".click_span_add_operation",function(){
		$(this).closest(".operation_permission").clone().insertBefore(".resourcebtnbox")
		ifcheck()
	})
	$(".form_content").on("click",".click_span_remove_operation",function(){
		if($(".operation_permission").length == 1){
			
		}else{
			$(this).closest(".operation_permission").detach()
		}
		ifcheck()
	})
})