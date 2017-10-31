$(function(){
	//给jquery添加表格插件
	$.fn.LemonGetList = function(params){
		var _this = this;
		var plug_first = true;  //第一次载入插件

		//扩展业务字段
		var saveStorageName = '';
		
		var defaults = {
			useLocalStorage : false,
			
			data:{},
			pageSize:"10",
			requestListUrl:" ",
			
			className_Page:false,
			
			//请求事件方法
			initFun : function(_this){
				return false;
			},
			//生成一个的方法
			generateItemFun : function(){
				return '';
			},
			//渲染页面之前的操作
			beforeFun : function(data){
				console.log('发送数据之前的操作 : beforeFun(data), 需要返回待处理数组');
				return data.rows;
			},
			//渲染页面之后的操作
			afterFun : function(){ 
				console.log('生成页面之后的操作 : afterFun()');
			},
			emptyDataFun : function(){
				return "<tr><td style='width:100%' colspan='8'><span class='center text-center' style='display:inline-block;width:100%;'>没有查找到数据!</span></td></tr>";
			},
			getData:function(data,is_first){
				getData(data,is_first);
			}
		};
		var TableObj = $.extend(defaults,params);
		
		//去 后台 请求数据
		var getData = function(request_data,is_first){
			if(is_first){
				plug_first = true;
				if(TableObj.data.page && TableObj.data.page != 1) TableObj.data.page = 1;
			}
			TableObj.data = $.extend(TableObj.data,request_data);
			//如果使用分页,执行下面添加page和rows代码
			if(TableObj.className_Page){
				TableObj.data.rows = TableObj.pageSize;
			}
			//设置本地存储名称
			var local_data = '';
			if(TableObj.useLocalStorage){
				saveStorageName = '';
				for (var Key in TableObj.data){
					var str_tem = TableObj.data[Key];
					if(typeof str_tem == 'object') str_tem = JSON.stringify(str_tem);
					saveStorageName += '_'+str_tem;
				}
				saveStorageName = TableObj.requestListUrl+saveStorageName; 
				var local_data = getStorage(saveStorageName);
			}
			if(local_data){
				dealData(local_data,request_data);
			}else{
				
				$.post(TableObj.requestListUrl,TableObj.data, function(data){  //get 请求数据 需要获取当前 总数 和 本次分页数据
					if(TableObj.useLocalStorage){ setStorage(saveStorageName,data); }
					dealData(data,request_data);
				},"json");
			}
		};
		//插件初始化工作
		if(plug_first) TableObj.initFun();
		getData({page:1})
		
		//处理来自服务器端的数据
		function dealData(data,request_data){
			var dataList = TableObj.beforeFun(data);
			if(!dataList){
				console.log('beforeFun : return data 为空');
				return ;
			}
			if(plug_first && TableObj.className_Page){ 
				//如果页面是第一次加载,进入本流程
				createPage(data.total);
			}
			if(dataList.length > 0){
				initHtml(dataList);
				TableObj.afterFun();
			}else{
				_this.html(TableObj.emptyDataFun());
			}
			plug_first = false;
		}
		function initHtml(data){
			var html = '';
			$.each(data,function(index,value,data){
				if(TableObj.useLocalStorage){ 
					var storage_name = ' storage_name="'+saveStorageName+'" ';
					var storage_id = ' storage_id="'+index+'" ';
					var extend = storage_name + storage_id;
				}else{
					var extend = '';
				}
				
				html += TableObj.generateItemFun(index,value,data,extend);
			})
			_this.html(html);
		}
		
		//分页相关---
		// 需要 当前分页 和 总数
		function createPage(all_num){
			var now_page = TableObj.data.page;
			var pageSize = TableObj.data.rows;
			// 计算总页数
			total_pages = all_num % pageSize == 0 ? (all_num / pageSize):(all_num / pageSize) +1 ;	
			total_pages = parseInt(total_pages);
			if(total_pages >= 2){
				pagePlug(total_pages);
			}else{
				$(TableObj.className_Page).html('');
			}
		}
		function pagePlug(total_pages){
			//生成分页
			$(TableObj.className_Page).pagination({
				items 		: total_pages,
		        itemOnPage 	: TableObj.pageSize,
		        currentPage : TableObj.data.page,
		        cssStyle 	: '',
		        prevText 	: ' ',
		        nextText 	: ' ',
		        onInit: function () {
		            // fire first page loading
		        },
		        onPageClick: function (page, evt) {
		        	TableObj.data.page = page;
		        	getData();
		        }
				
			})
		}
	}
})

