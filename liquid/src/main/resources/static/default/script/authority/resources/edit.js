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
	
	$("#edit_resourceId").val(data.id)
	$("#edit_resourceName").val(data.resourceName)
	$("#edit_resourceType").val(data.resourceType)
	
	$.each(data.operationList, function(i,v){	
			
		$("input[displayname = "+v.displayName+"]").prop("checked",true)
	})
	beforeMaxEditResourceModal()
	//$("#edit_operation").val(operationName)
	
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

//编辑js
function beforeMaxEditResourceModal(){	
			
			$(".relateCtl [type='checkbox']").iCheck({
				checkboxClass: 'icheckbox_flat-blue',
				increaseArea: '20%' // optional
			});
			$(".in_input_num .limitlength").html(15-$(".in_input_num input").val().length)
			$(".in_input_num input").on("keyup",function(){
				var val = $(this).val().length
				$(".in_input_num .limitlength").html(15-val)
			})	
}

$("#submit_editResource").on("click",function(){
	var tem_str = '';
	$("#edit_operation input:checked").each(function(){
		
		if(!tem_str) {
			tem_str += $(this).val();
		}else{
			tem_str +=',' + $(this).val();
		}
	})
	$.post("resources/edit",
		{
			id:$("#edit_resourceId").val(),
			resourceName:$("#edit_resourceName").val(),
			resourceType:$("#edit_resourceType option:selected").val(),
			resourcePId:$("#edit_resourcePId option:selected").val(),
			operation:tem_str
		},function(data){
			if(data.success){
				removeStorage();
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