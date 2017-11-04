var add = $(".numCtr .add");
	var sub = $(".numCtr .reduce ");
	var grey = '#bdc3c7';
	var blue = '#378ef8';
	function judge(limitNum) {
		if(limitNum>9){
			$(sub).css("background", blue);
			$(add).css("background", grey);
		}else if(limitNum < 1) {
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
		var numVal = parseInt($("#add_roleMaxNum").val());
		if(numVal > 0) {
			numVal--;
			$("#add_roleMaxNum").val(numVal);
		}
		var inputlimitNum = parseInt($(".numCtr input").val());
		judge(inputlimitNum);
	});
	$(add).click(function(e) {
		e.preventDefault();
		var numVal = parseInt($("#add_roleMaxNum").val());
		if(numVal < 10) {
			numVal++;
			$("#add_roleMaxNum").val(numVal);
		}
		var inputlimitNum = parseInt($(".numCtr input").val());
		judge(inputlimitNum);
	});

$('.select_roleList').LemonGetList({
	requestListUrl:'role/listAll',
	beforeFun:function(data){
		return getListByTree(data);
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
	emptyDataFun:function(){
		return '<option value="-1">暂无</option>';
	}
	
})
$(".addbtnbox").on("click","#submit_addRole",function(){
	$.post("role/save",
		{
		displayName:$("#add_displayName").val(),
		roleMaxNum:$("#add_roleMaxNum").val(),
		remarks:$("#add_remarks").val(),
		roleExtendPId:$("#add_roleExtendPId option:selected").val(),
		roleRelyId:$("#add_roleRelyId option:selected").val()
		},function(data){
			if(data.success){
				removeStorage();
				$(".ajax_dom").empty()
				$.ajax({
					url:"role/list"
				}).done(function(data){
					$(data).appendTo($(".ajax_dom"))
					$('.alertArea').showAlert({
						content: '添加成功'
					});
				})
			
			}
			else{
				removeStorage();
				$(".ajax_dom").empty()
				$.ajax({
					url:"role/list"
				}).done(function(data){
					$(data).appendTo($(".ajax_dom"))
					$('.alertArea').showAlert({
						content: '添加失败'
					});
				})
			}
		}
	,"json")
}) 
$(".btnbox").on("click", "#closeAdd", function() {
		$.ajax({
			url:"user/list"
		}).done(function(data) {
			$(".ajax_dom").empty()
			$(data).appendTo($(".ajax_dom"))
		})
	})