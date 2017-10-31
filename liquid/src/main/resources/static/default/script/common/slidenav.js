$(function(){
	// 菜单展开时的过渡效果暂不使用
	// $('.left-menu').css('transition','width 0.4s');//防止rem造成的屏幕闪动
	// $('.left-menu').on('transitionend',function(){
	// 	if($(this).attr('class')=='left-menu'){
	// 		$(this).addClass('show');
	// 		// console.log($(this).find('li.active').next('ul'));
	// 		$(this).find('li.active').next('ul').stop(true).slideDown(200);
	// 	}
	// })
	// 侧边菜单的展开/收回按钮
	$('.btn-fold').on('click',function(e){
		e.stopPropagation();
		$('.left-menu').toggleClass('min');
		// 改变后的菜单宽
		if($('.left-menu').width()>210){
			$('.left-menu').addClass('show');
			$('.left-menu').find('li.active').next('ul').stop(true).slideDown(200);
		}else{
			$('.left-menu').removeClass('show');
			$('.left-menu').find('li.active').removeClass('active').next('ul').stop(true).slideUp(200);
		}
	})
	// 任务内容的展示按钮
	$('.btn-mission').on('click',function(e){
		e.stopPropagation();
		$('.mission').toggle();
	})

	$('.mission').on('mouseleave.mission',function(e){
		// console.log(e.target)
		// e.target
		$('.mission').hide();
	})
	// 列表的折叠
	$('.left-menu .slidenav').delegate('li','click',function(){
		// 当点击折叠后的选中目标
		// if($('.left-menu').prop('class')!=='left-menu'){
		// 	if($(this).prop('class')){
		// 		$('.left-menu').addClass('show');
		// 		$('.left-menu').find('li.active').next('ul').stop(true).slideDown(200);
		// 	}
		// }else{
		// 展开菜单
			$('.left-menu').removeClass('min');
			$(this).next('ul').stop(true).slideToggle(200,function(){
				$(this).css('height','auto');
			});
			// if($(this).attr('level')=='show'){
			if($(this).attr('url')){
				$(this).addClass('active').siblings('li').removeClass('active');
			}else{
				$(this).toggleClass('active').siblings('li').removeClass('active').next('ul').stop(true).slideUp(200).find('li').removeClass('active').next('ul').stop(true).slideUp(200).find('li').removeClass('active');
			}
		// }
	})
	// 小屏时关闭侧滑菜单 菜单初始化为关闭状态
	if($(window).width()<755){
		if($('.left-menu').width()>100){
			$('.left-menu').addClass('min').removeClass('show');
			$('.left-menu').find('li.active').removeClass('active').next('ul').stop(true).slideUp(200);
		}
	}
	$(window).on('resize.slidenav',function(){
		if($(window).width()<755){
			if($('.left-menu').width()>100){
				$('.left-menu').addClass('min').removeClass('show');
				$('.left-menu').find('li.active').removeClass('active').next('ul').stop(true).slideUp(200);
			}
		}
	})
	$(document).on('click.maxheight',".left-menu li",function(){
			// var height_right=$('.right-container').outerHeight(true)>$('.ajax_dom').outerHeight(true)?$('.right-container').outerHeight(true):$('.ajax_dom').outerHeight(true);
			var height_left=$('.left-menu').outerHeight(true);
			// var height_main=height_right>height_left?height_right:height_left;
			// console.log(height_right,height_left,height_main)
			// $('.main-container').height(height_main);
			// $('.main-container').height(height_right);
			// $('.ajax_dom').height(height_right);
			$('.content-wrap').css('min-height',height_left)
		
	})
	// 任务菜单id标识符点击赋值
	// console.log($('.mission'))
	$('.mission ol').delegate('li','click',function(e){
		// console.log(e.target)
		// e.target
		// console.log($(this));
		var role_aut_id=$(this).find('p').attr('role_aut_id');
		var user_aut_id=$(this).find('p').attr('user_aut_id');
		if(role_aut_id){
			$('#role-authorization .modal-content').attr('aut_id',role_aut_id);

		}
		if(user_aut_id){
			$('#user-authorization .modal-content').attr('aut_id',user_aut_id);

		}
	})
})