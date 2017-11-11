//滑块限制字符
	$(".sliderInput").css("width", "0");
	var minlimitNum = 5;
	$(".minlimitNum").html(minlimitNum);
	var docu_w = parseInt($(".sliderInput").css("width"));
	$(".error_box").slider({
		orientation: "horizontal",
		range: "min",
		max: 67,
		value: 67,
		slide: function(event, ui) {
			var ui_value = ui.value
			$(".sliderInput").css("width", ui_value + "%");
			$(".minlimitNum").html(minlimitNum + parseInt(ui_value / 10) - $(".sliderInput").find("input[type='text']").val().length);
			$(".sliderInput").find("input").val($(".sliderInput").find("input").val().slice(0, minlimitNum + parseInt(ui_value / 10)))
			var max_length = minlimitNum + parseInt(ui_value / 10)
			$(".sliderInput").find("input[type='text']").attr("maxlength", max_length)
			limitChangeLength($(".sliderInput").find("input[type='text']"), max_length);
		}
	})
	$(".sliderInput").css("width", $(".error_box").slider("value") + "%");
	$(".minlimitNum").html(minlimitNum + parseInt($(".error_box").slider("value") / 10))

	function limitChangeLength(elm, limitLength) {
		$(elm).attr("maxLength", limitLength);
		$(elm).keyup(function() {
			var length = $(elm).val().length;
			$(elm).siblings("span").html(limitLength - length);
		});
	}
	limitChangeLength($(".form_input .role"), parseInt($(".minlimitNum").html()));
	limitChangeLength($(".form_input .phone"), 11)
	limitChangeLength($(".form_input .emlia"), 15)
	$(".btnbox").on("click", "#closeAdd", function() {
		$.ajax({
			url:"user/list"
		}).done(function(data) {
			if(data.access == "unauthorized") {
				$("#authText").text("您没有访问用户列表权限")
				$("#powerModal").modal('show');
			} else {
				$(".ajax_dom").empty()
				$(data).appendTo($(".ajax_dom"))
			}
		})
	})
	$(".userbtnbox").on("click", "#submit_addUser", function() {
		$.post("user/save", {
			userName: $("#add_userName").val().trim(),
			userPass:$("#add_userPass").val().trim(),
			phone: $("#add_phone").val().trim(),
			email: $("#add_email").val().trim()
		}, function(data) {
			if(data.access == "unauthorized") {
				$("#authText").text("您没有添加用户权限")
				$("#powerModal").modal('show');
			} else {
				if(data.success) {
					removeStorage();
					$(".ajax_dom").empty()
					$.ajax({
						url: "user/list"
					}).done(function(data) {
						$(data).appendTo($(".ajax_dom"))
						$('.alertArea').showAlert({
							content: '添加成功'
						});
					})
					
				} else {
					removeStorage();
					$(".ajax_dom").empty()
					$.ajax({
						url: "user/list"
					}).done(function(data) {
						$(data).appendTo($(".ajax_dom"))
						$('.alertArea').showAlert({
							content: '添加失败'
						});
					})
				}
			}
		}, "json")
	})