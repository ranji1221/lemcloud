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
		var params = [];	
		var permissionList=document.getElementsByClassName("add_permission");
		var operationList=document.getElementsByClassName("add_operations");
		$(".add_operationRName option:selected").each(function(i,v){
			var operations = {};
			operations.operation=operationList[i].value;
			operations.permission=permissionList[i].value;
			operations.relyName=$(v).val();
			params.push(operations);
		})
		//var index_i = 0
	$.post("resources/save",
		{
			resourceName:$("#add_resourceName").val().trim(),
			resourceType:$("#add_resourceType option:selected").val().trim(),
			resourcePId:$("#add_resourcePId option:selected").val().trim(),
			params : JSON.stringify(params)
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

var arr = []
//判断加减号
function ifcheck(option){
		var opera_length = $(".operation_permission").length-1
		$(".operation_permission:lt("+opera_length+")").find("input,select").prop("disabled","true")
		$(".operation_permission").find(".phone .input_add_span,.input_remove_span").hide()
		var last_oper = $('.operation_permission:last')
		last_oper.find(".phone .input_add_span,.input_remove_span").show()
		if(option){
			if($(".operation_permission").length == 1){
				$(".operation_permission").find(".phone .input_add_span,.input_remove_span").show()
				arr.push(option)
				$.each(arr,function(i,v){
					$("<option value="+v+">"+v+"</option>").appendTo($(".operation_permission select"))
				})
			}else{
				arr.push(option)
				$.each(arr,function(i,v){
					$("<option value="+v+">"+v+"</option>").appendTo($(last_oper).find("select"))
				})
			}
		}
		
	}
	ifcheck("选择资源")
//	添加方法
	$(".form_content").on("click",".click_span_add_operation",function(){
		if(!($(this).siblings(".inputwrappermax").find("input").val()&&$(this).closest(".phone").siblings(".add_operation").find("input").val())){
			
		}else{
			var option = $(this).siblings(".inputwrappermax").find("input").val()
		$('<div class="operation_permission"><div class="form-group phone input">'+
		    	'<label for="" >相关操作：</label>'+
		    	'<div class="inputwrapper" id="add_operation">'+
		    		'<div class="inputwrappermax">'+
				    	'<input type="text" class="form-control rolenameinput add_operations" placeholder="请输入相关操作" maxlength="20"/>'+
				    	'<span class="limitlength">20</span>'+
				    	'<span class="errormessage errormessage-source-edit-name">'+
			    		'</span>'+
		    		'</div>'+
		    		'<span class="btn btn-default input_add_span click_span_add_operation">+</span>'+
		    		'<span class="btn btn-default input_remove_span click_span_remove_operation">-</span>'+
		    	'</div>'+
		  '	</div>'+
		  	'<div class="form-group input add_operation">'+
		    	'<label for="" >权限许可：</label>'+
		    	'<div class="inputwrapper">'+
		    		'<div class="inputwrappermax in_input_num">'+
				    	'<input type="text" class="form-control rolenameinput add_permission" placeholder="请输入权限许可" maxlength="20"/>'+
				    	'<span class="limitlength">20</span>'+
				    	'<span class="errormessage errormessage-source-edit-name">'+
			    		'</span>'+
		    		'</div>'+
		    	'</div>'+
		  	'</div>'+
		  	'<div class="form-group select">'+
		    	'<label for="" >选择依赖操作：</label>'+
		    	'<div class="inputwrapper">'+
		    		'<div class="inputwrappermax">'+
				    	'<select class="form-control select select-primary select-block mbl add_operationRName" data-toggle="select" name="fath">'+
						'</select>'+
				    	'<span class="errormessage errormessage-role-edit-fath">'+
			    		'</span>'+
		    		'</div>'+
		    	'</div>'+
		  	'</div>'+
	  	'</div>').insertBefore(".resourcebtnbox")
	  	ifcheck(option)}
	})
//	减方法
	$(".form_content").on("click",".click_span_remove_operation",function(){
		if($(".operation_permission").length == 1){
			
		}else{
			$(this).closest(".operation_permission").prev().find("input,select").prop("disabled",false)
			$(this).closest(".operation_permission").detach()
		}
		ifcheck()
	})
