
$(function(){
	//确认信息框
	$.fn.bindDialogs = function(params){
		$(document).off("click.remove_click","**")
		var _this = this;
		var lastClickElement = '';
		var defaults = {
			modalTypeImg: document.getElementById("img").src,
			content:'您确定执行本操作吗？',
			prompt:'*此项操作不可逆！<br/>如果确认则点击“确定”，否则点“取消”',
			name_successBtn : 'successBtn',
			name_cancelBtn : 'cancelBtn',
			success:function(){},
			cancel:function(){},
			beforeFun:function(){
				return true;
			}
			
		};
		var this_obj = $.extend(defaults,params);
		$('#removeModal').off("click."+this_obj.name_successBtn,'.'+this_obj.name_successBtn)
		$('#removeModal').off("click."+this_obj.name_cancelBtn,'.'+this_obj.name_successBtn)
		$(document).on("click",".viewRole,.role_editRole,.removeBtn,.roleAuth",function(){
			$(".remove_alert").detach()
})
		//去 后台 请求数据
		$(document).on("click."+_this.selector,_this.selector, function(e) {
			lastClickElement = this;
			e.stopPropagation();
			e.preventDefault();	
			if(this_obj.beforeFun()){
				$('#removeModal .modal').modal('show');
				$("#removeModal .modal-body").html(
					'<div class="row">'+
					   ' <div class="col-xs-4">'+
				       ' <div class="trashImg text-right">'+
				           ' <img src="'+this_obj.modalTypeImg+'" alt="">'+
				        '</div>'+
				   ' </div>'+
				    '<div class="col-xs-8 info">'+
				        '<h3>'+this_obj.content+'</h3>'+
				        '<p>'+this_obj.prompt+'</p>'+
				    '</div>'+
				'</div>'+
				'<div class="row">'+
				    '<div class="col-xs-8 col-xs-offset-4">'+
				    	'<button type="button" class="btn modalBtn delBtn '+this_obj.name_successBtn+'" >确定</button>'+
				    	'<button type="button" class="btn modalBtn closeBtn '+this_obj.name_cancelBtn+'" >取消</button>'+
				    '</div>'+
				'</div>'
				);	
			}
			
			
		});
		$('#removeModal').on("click."+this_obj.name_successBtn,'.'+this_obj.name_successBtn, function(e) {
			this_obj.success(lastClickElement);
			hiddenModal();
		});
		$('#removeModal').on("click."+this_obj.name_cancelBtn,'.'+this_obj.name_cancelBtn, function(e) {
			this_obj.cancel(lastClickElement);
			hiddenModal();
		});
		function hiddenModal(){
			$('#removeModal .modal').modal('hide');
		}
	}
	
	//创建公共的警告框	
	$.fn.showAlert = function(params){
		var _this = this;
		var lastClickElement = '';
		var defaults = {
			content:'',
			time:'5',
			type:'success', //有4中状态，1,success(绿色) 2,info(蓝色) 3,warning(黄色) 4,danger(红色)
		};
		var this_obj = $.extend(defaults,params);
		var uid = 'tem_alert_'+parseInt(Math.random()*9999);
		var div = '<div class="remove_alert '+uid+' alert alert-'+this_obj.type+' role="alert"><i class="glyphicon glyphicon-hand-right"></i>'+this_obj.content+'</div>';

		$(div).appendTo(_this.selector);
		setTimeout(function(){
			$(_this.selector+' .'+uid).fadeOut(2000,function(){
				$(_this.selector+' .'+uid).remove();
			})
		},this_obj.time*1000)
	}
});	


