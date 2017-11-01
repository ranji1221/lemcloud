$(function(){
	var percentage=0;
	var leading;
	// 排序
	$('div.tablewrap table tr th span.sort').on('click',function(){
		var zlsortup=$(this).find('.glyphicon-triangle-top');
		var zlsortdown=$(this).find('.glyphicon-triangle-bottom');
		var zlsort=zlsortup.css('opacity');

		$(this).closest('th').siblings('th').find('span i').css('opacity','1');
		if(zlsort=='1'){
			zlsortup.css('opacity','0');
			zlsortdown.css('opacity','1');
		}else{
			zlsortup.css('opacity','1');
			zlsortdown.css('opacity','0');
		}		
	})
	function doProgress(){
		leading = setInterval(function(){
		$.post("backup/progressBar",{},function(data){
				if(data.success){
					percentage = parseInt((data.info[0].progress / data.info[0].size) * 100);
					progress_bar.width(percentage+'%');
					pro_text.html(percentage+'%')
				}
			},'json')
		},200)
	}		
	$('div.tablewrap').on('click','.leading',function(){
		// 进度模态框展示时启动动画
		$('#leading-progress').on('shown.bs.modal', function (e) {
			//启动动画
			doProgress();
		})
		var gid = $(this).closest("tr").attr("recover_id");
		setTimeout(function(){
			$.post("backup/recover",{
				id:gid,
			},function(data){
				if(data.success){
					 //上传完成，清除循环事件
	                clearInterval(leading);
	                //将进度更新至100%
	                percentage = 100;
	                progress_bar.width(percentage+'%');
					pro_text.html(percentage+'%')
					progress_box.fadeOut(function(){
						leading_res_success.fadeIn()
					})
				}else{
					 clearInterval(leading);
					 progress_box.fadeOut(function(){
						 leading_res_error.fadeIn()
					})
				}
			},'json')
		},500)
		
		$('#leading-progress').modal({backdrop: 'static', keyboard: false}); ;
		$('#leading-progress').modal('show');
	})
	/*// 展示备注
	$('div.tablewrap').on('click','.remarks',function(){
		$('#database-remarks .database-remark').html("然则我获得的┞封个S变量却老是少一部分，有人说AsString只能限制在64K以下，但我的S实际上只有33K阁下，并且不是说Delphi3以上的String的理论值已经是4G了吗？我在写一个数据库法度榜样时，须要大大一个表中获得备注字段的内容然后写入另一个表中，我是用：var s :string; s:=Query.FieldByName('MemoField').AsString;如不雅确切有如许的限制的话，我该若何实现上述功能？还请各位大大侠多多指导！！");
		$('#database-remarks').modal('show');
	})*/
	// 进度条容器
	var progress_box=$('#leading-progress .progressbox')
	// 进度条
	var progress_bar=$('#leading-progress .progress-bar')
	// 进度提示文字
	var pro_text=$('#leading-progress .progress-bar .pro-text')
	// 成功提示
	var leading_res_success=$('.leading-res-success')
	// 失败提示
	var leading_res_error=$('.leading-res-error')
	// 临时动画
	/*var leading=setInterval(function(){
		if(percentage==100){
			clearInterval(leading);
			progress_box.fadeOut(function(){
				leading_res_success.fadeIn()
			})
		}else{
			percentage++;
			progress_bar.width(percentage+'%');
			pro_text.html(percentage+'%')
		}
	},100)*/
	// 进度模态框展示前初始化
	$('#leading-progress').on('show.bs.modal', function (e) {
	    progress_box.css('display','block');
	    leading_res_success.css('display','none');
	    leading_res_error.css('display','none');
	    progress_bar.width('0%');
		pro_text.html('0%')
	})
})
