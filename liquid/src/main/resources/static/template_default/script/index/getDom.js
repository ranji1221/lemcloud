(function($){
	$(function(){
		// 记录请求前后的请求地址
		var sendurl;
		var endurl;
		$(document).on("click",".rolelist .tfoot .newPro,.slidenav li",function(){
			var url = $(this).attr("url");
			if(url){
				// 重新填装加载图片
				var load_img=$('.right-container >.loading_ajax_dom >img').attr('src')
				$('.right-container >.loading_ajax_dom >img').attr('src',load_img+'?'+Math.random());
				$('.right-container >.loading_ajax_dom').show(0);
				// 清除之前点击请求
				$.ajax({
					url:url,
					dataType:"html",
					beforeSend:function(){
						// 记录请求前的地址
						sendurl=url;
					}
				}).done(function(data){
						//获取请求结束后的新地址
						endurl=url;
						// 多次高频点击链接请求产生队列排除掉与最后一次点击链接不符的返回结果
						if(sendurl==endurl){
							$(".right-container .ajax_dom").empty()
							$(".right-container .ajax_dom").show(0);
							$(data).appendTo($(".right-container .ajax_dom"))
							$('.right-container >.loading_ajax_dom').hide(0);
						}else{
							
						}
				})
			}
		})
	})
})(jQuery)
