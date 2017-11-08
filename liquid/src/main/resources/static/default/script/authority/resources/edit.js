//添加编辑事件
$("table").on("click",".editResource", function(e) {
	e.preventDefault();
	////获取到本地的某条数据
	var storage_name = $(this).closest('tr').attr('storage_name');
	var storage_id = $(this).closest('tr').attr('storage_id');
	var data = getDataByStorage(storage_name,storage_id);
	var class_name =  $("#editModal").find(".modal-contentbox").attr("maxClassName")
	$("#editModal").find(".modal-contentbox").appendTo(".ajax_dom").addClass(class_name)
	dealDataToModal(data);
});

function dealDataToModal(data){
	//console.info(data)
	$("#edit_resourceId").val(data.id)
	$("#edit_resourceName").val(data.resourceName)
	$("#edit_resourceType").val(data.resourceType)
	$.each(data.operationList, function(i,v){
		addOperation();
		$(".operation_permission:last").find($(".edit_operations")).val(v.displayName);
		$(".operation_permission:last").find($(".edit_operationId")).val(v.id);
		$(".operation_permission:last").find($(".edit_permission")).val(v.permission);
		$(".operation_permission:last").find($(".edit_operationRName")).val(v.operationRId);
		
	})
	
	$('.select_resourceList').LemonGetList({
		requestListUrl:'resources/listAll',
		beforeFun:function(data){
			return getListByTree(data);
		},
		generateItemFun:function(index,value){
			var itemHtml = '';
			if(index == 0 ){ itemHtml += '<option value="0">选择资源</option>';}
			
			var kongge_str = '';
			for(var i=0;i<value.level;i++){
				kongge_str += '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;';
			}
			kongge_str += '|-';
			
			itemHtml += '<option  value="'+value.id+'" ';
			itemHtml += ' >'+kongge_str+value.resourceName+'</option>';
			return itemHtml;
		},
		afterFun:function(){
			if(data.resourcePId >= 1){
				$('.select_resourceList option').each(function(val){
					if($(this).attr('value') == data.resourcePId){
						$(this).attr('selected','selected');
					}
				})
			}
		},
		emptyDataFun : function(){
			return '<option value="0" '+'>没有数据</option>';
		}
	})
}

$("#submit_editResource").on("click",function(){
	var params = [];	
	var permissionList=document.getElementsByClassName("edit_permission");
	var operationList=document.getElementsByClassName("edit_operations");
	$(".edit_operationRName option:selected").each(function(i,v){
		var operations = {};
		operations.operation=operationList[i].value;
		operations.permission=permissionList[i].value;
		operations.relyName=$(v).text();
		params.push(operations);
	})
	$.post("resources/edit",
		{
			id:$("#edit_resourceId").val(),
			resourceName:$("#edit_resourceName").val(),
			resourceType:$("#edit_resourceType option:selected").val(),
			resourcePId:$("#edit_resourcePId option:selected").val(),
			params : JSON.stringify(params)
		},function(data){
			if(data.success){
				removeStorage();
				$(".ajax_dom").empty()
				$.ajax({
					url: "resources/list"
				}).done(function(data) {
					$('body #bodyModalArea').empty();
					$(data).appendTo($(".ajax_dom"))
					$('.alertArea').showAlert({
						content: '修改成功'
					});
				})
			}
			else{
				$(".ajax_dom").empty()
				$.ajax({
					url: "resources/list"
				}).done(function(data) {
					$('body #bodyModalArea').empty();
					$(data).appendTo($(".ajax_dom"))
					$('.alertArea').showAlert({
						content: '修改成功'
					});
				})
			}
		}
	,"json")
}) 
var arr = []
//判断加减号
function ifcheck(option,oid){
		var opera_length = $(".operation_permission").length-1
		$(".operation_permission:lt("+opera_length+")").find("input,select").prop("disabled","true")
		$(".operation_permission").find(".phone .input_add_span,.input_remove_span").hide()
		var last_oper = $('.operation_permission:last')
		last_oper.find(".phone .input_add_span,.input_remove_span").show()
		var operation ={}
		if(option){
			if($(".operation_permission").length == 1){
				$(".operation_permission").find(".phone .input_add_span,.input_remove_span").show()
				operation.option=option;
				operation.id = oid;
				arr.push(operation);
				//console.info(arr);
				$.each(arr,function(i,v){
					$("<option value="+v.id+">"+v.option+"</option>").appendTo($(".operation_permission select"))
				})
			}else{
				operation.option=option;
				operation.id = oid;
				arr.push(operation);
				$.each(arr,function(i,v){
					$("<option value="+v.id+">"+v.option+"</option>").appendTo($(last_oper).find("select"))
				})
			}
		}
		
	}
	ifcheck("选择资源",-1)
	//自动添加
	function addOperation(){
		if(!($(".operation_permission:last").find(".edit_operations").val()&&$(".operation_permission:last").find(".edit_permission").val())){
		
		}else{
			var option = $(".operation_permission:last").find(".edit_operations").val()
			var oid = $(".operation_permission:last").find(".edit_operationId").val()
		$('<div class="operation_permission"><div class="form-group phone input">'+
		    	'<label for="" >相关操作：</label>'+
		    	'<div class="inputwrapper" id="add_operation">'+
		    		'<div class="inputwrappermax">'+
				    	'<input type="text" class="form-control rolenameinput edit_operations" placeholder="请输入相关操作" maxlength="20"/>'+
				    	'<input type = "hidden" class ="edit_operationId"/>'+
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
				    	'<input type="text" class="form-control rolenameinput edit_permission" placeholder="请输入权限许可" maxlength="20"/>'+
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
				    	'<select class="form-control select select-primary select-block mbl edit_operationRName" data-toggle="select" name="fath">'+
						'</select>'+
				    	'<span class="errormessage errormessage-role-edit-fath">'+
			    		'</span>'+
		    		'</div>'+
		    	'</div>'+
		  	'</div>'+
	  	'</div>').insertBefore(".resourcebtnbox")}
	  	ifcheck(option,oid)
	}
//	添加方法
	$(".form_content").on("click",".click_span_add_operation",function(){
		addOperation();
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