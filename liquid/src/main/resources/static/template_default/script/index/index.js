$(window).ready(function() {
	var funcItems = $("#funcNav .func-item");
	var funcNav = $("funcNav");
	$("#funcNav").sortable({
		items: funcItems,
		cursor: "move",
		opacity: 0.6,
		cursorAt: {
			left: 190,
			top: 85
		}
	});
	$("#funcNav").disableSelection();
});

//限制显示字数
$(function() {
	$("#showList .humanInfo .manageName").each(function() {
		var maxwidth = 4;
		if($(this).text().length > maxwidth) {
			var b = $(this).children().is("a");
			if(b) {
				$(this).children().text($(this).children().text().substring(0, maxwidth) + "...");
			} else {
				$(this).text($(this).text().substring(0, maxwidth));
				$(this).text($(this).text() + "...");
			}

		}
	});
	$("#showList .gameBox .row-content .col-text>p").each(function() {
		var maxwidth = 80;
		if($(this).text().length > maxwidth) {
			var b = $(this).children().is("a");
			if(b) {
				$(this).children().text($(this).children().text().substring(0, maxwidth) + "...");
			} else {
				$(this).text($(this).text().substring(0, maxwidth));
				$(this).text($(this).text() + "...");
			}

		}
	});
});
// ctrTool

$(function() {
	$(".navbar_header_logo_home").on("click", function() {
		window.location.reload()
	})
	// 拖拽
	var handle = $("#showList .ctrTool .icon-move");
	var mcons = $("#showList .row");
	var items = $("#showList .m-con");
	$(mcons).sortable({
		items: items,
		connectWith: mcons,
		handle: handle,
		helper: "clone"
		//      forcePlaceholderSize: true
		//      activate:function(event,ui){
		//      	$(ui.item).siblings().addClass("moveAnimation");
		//      }
		//      placeholder:'ui-nav-placeholder',
		//介绍后的回掉函数
		//      beforeStop: function( event, ui ) {
		//      }
	});
	//刷新
	$("#showList").on("click", ".m-con .panel-heading .icon-refresh", function(e) {
		e.preventDefault();
		e.stopPropagation();
		var elm = $(this).closest(".panel");
		var elmwidth = $(elm).css("width");
		var elmheight = $(elm).css("height");
		$(elm).append('<div class="model"><div class="loader"><i class="icon-spinner icon-spin"></i></div></div>');
		$(elm).children('.model').css("width", elmwidth);
		$(elm).children('.model').css("height", elmheight);
		window.setTimeout(function() {
			$(elm).children('.model').remove();
		}, 3000);
	});

	//上下拉
	$("#showList").on("click", ".m-con .panel-heading .icon-chevron-up", function(e) {
		e.preventDefault();
		e.stopPropagation();
		$(this).toggleClass("icon-chevron-down");
		$(this).closest(".panel").toggleClass("up-down-btn");
	});

	//关闭按钮
	$("#showList .m-con .panel-heading .icon-remove").click(function() {
		$(this).closest(".panel").remove();
	});
});

function pieChart() {
	var pieWidth = parseInt($('.pie').css("width"));
	var pieHeight = parseInt($('.pie').css("height"));
	if(pieWidth > pieHeight) {
		pieWidth = pieHeight;
	};
	$(".pieChart").easyPieChart({
		barColor: "#ee3787",
		size: pieWidth
	});
}
function pieChart() {
	var pieWidth = parseInt($('.pie').width()) < parseInt($('.pie').height()) ? parseInt($('.pie').width()) - 20 : parseInt($('.pie').height() - 20);
	var lineHeight = parseInt($('.pie').height());
	if(pieWidth > 123) {
		pieWidth = 123;
	}
	$('.pieChart').easyPieChart({
		barColor: function(percent) {
			var ctx = this.renderer.getCtx();
			var canvas = this.renderer.getCanvas();
			var gradient = ctx.createLinearGradient(canvas.width, 0, 0, canvas.height);
			gradient.addColorStop(0, "rgba(238, 55, 135, 1)");
			gradient.addColorStop(1, "rgba(143, 48, 160, 1)");
			return gradient;
		},
		trackColor: 'rgb(221,230,228)',
		scaleColor: false,
		lineWidth: '3',
		size: pieWidth
	});
	$('.pieChart canvas').css({
		marginLeft: -pieWidth / 2,
		marginTop: -pieWidth / 2,
	});
	$('.pie').css({
		lineHeight: lineHeight + 'px',
		opacity: 1
	});
}
pieChart();
$(window).resize(function() {
	// changePie();
	var pieWidth = parseInt($('.pie').width()) < parseInt($('.pie').height()) ? parseInt($('.pie').width()) - 20 : parseInt($('.pie').height()) - 20;
	var lineHeight = parseInt($('.pie').height());
	if(pieWidth > 123) {
		pieWidth = 123;
	}
	$('.pieChart canvas').css({
		width: pieWidth,
		height: pieWidth,
		marginLeft: -pieWidth / 2,
		marginTop: -pieWidth / 2,
	});
	$('.pie').css({
		lineHeight: lineHeight + 'px',
	});
});

//天气部分
$(function() {
	var skycons = new Skycons({
		"color": "666"
	});
	skycons.add("skyCan", Skycons.PARTLY_CLOUDY_DAY);
});
//日期部分
$(function() {
	function show() {
		var mydate = new Date();
		var str = "" + mydate.getFullYear() + "/";
		str += (mydate.getMonth() + 1) + "/";
		str += mydate.getDate();
		return str;
	}
	var html = show();
	$("#calendar").html(html);
});

//经济报表折线图
function lineChart() {

}
$('#line').ready(function() {
	var lineChart = Morris.Area({
		element: 'line',
		data: [{
				period: '2000',
				received: 4691
			},
			{
				period: '2001',
				received: 8403
			},
			{
				period: '2002',
				received: 15574
			},
			{
				period: '2003',
				received: 18211
			},
			{
				period: '2004',
				received: 19427
			},
			{
				period: '2005',
				received: 16486
			},
			{
				period: '2006',
				received: 14737
			},
			{
				period: '2007',
				received: 5838
			},
			{
				period: '2008',
				received: 5542
			},
			{
				period: '2009',
				received: 15560
			},
			{
				period: '2010',
				received: 18940
			},
			{
				period: '2011',
				received: 11970
			},
			{
				period: '2012',
				received: 17580
			},
			{
				period: '2013',
				received: 17511
			},
			{
				period: '2014',
				received: 12601
			},
			{
				period: '2015',
				received: 13158
			}
		],
		xkey: 'period',
		ykeys: ['received'],
		labels: ['received'],
		hideHover: 'auto',
		fillOpacity: 0.9,
		pointSize: 1.5,
		lineColors: ['#bb04be'],
		resize: true,
		lineWidth: '2',
		xLabels: 'year',
		yLabelFormat: function(y) {
			return parseInt(y / 1000) + 'k';
		}
	});

	function redrawLine() {
		setTimeout(function() {
			lineChart.redraw();
		}, 50);
	};
	$("#line .morris-hover-point").ready(function() {
		$("#line .morris-hover-point").attr("style", "color:#fff");
	})
});
$(".reportBox").resize(function() {
	lineChart();
})
//面包屑的所有
	$(document).on("click", ".breadcrumb", function(e) {
		var el = e.target || window.event
		e.preventDefault()
		var url = $(el).attr("url")
		var ajax_dom = $(".ajax_dom")
		if($(el).attr('data')) {
			window.location.reload()
		} else {
			if(url){
				$.ajax({
					url: url + ".html",
					dataType: "html"
				}).done(function(data) {
					ajax_dom.empty()
					ajax_dom.html(data)
				})
			}
		}

	})

//添加内容的
function setinput(inut_set, ustr) {
	inut_set.each(function(i, v) {
		if($(v).attr("name")) {
			var str = $(v).prop("name")
			var v_str = $(v)
			$(ustr).each(function(i, v) {
				if(str == v.name) {
					if(v.name == "fath" || v.name == "yilai" || v.name == "user" || v.name == "fileSize") {
						v_str.closest(".fath_select").find(".select2-chosen").text(v_str.find("option[value=" + v.value + "]").html())
						v_str.find("option[value=" + v.value + "]").attr("selected", true)
					} else if(v.name == "look" || v.name == "add" || v.name == "del" || v.name == "change") {
						v_str.closest(".icheckbox_square-blue").addClass("checked")
						v_str.closest(".icheckbox_flat-blue").addClass("checked")
						v_str.attr("checked", "checked")
					} else {
						v_str.val(v.value)

					}

				}
			})
		}
	})
}