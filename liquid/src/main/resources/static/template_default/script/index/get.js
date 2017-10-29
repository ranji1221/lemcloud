(function($){
	$(function(){
					var arr = [
						"public/common/icheck/skins/all.css",
						"public/common/icheck/icheck.js",
						"static/template_blue/script/index/getDom.js",
						"css/editModal/editModal.css",
						"public/common/vakata-jstree/dist/themes/default/style.min.css",
						"public/common/vakata-jstree/dist/jstree.min.js",
						"public/common/pagination/simplePagination.css",
						"public/common/pagination/jquery.simplePagination.js",
						"static/template_blue/style/role/listREM.css",
						"static/template_blue/style/role/authmodal.css",
						"css/user/auth.css",
						"static/template_blue/style/role/addsREM.css",
						"static/template_blue/style/role/role.css",
						"https://cdn.bootcss.com/jqueryui/1.12.1/jquery-ui.min.css",
						"css/user/edit.css",
						"css/resources/add.css",
						"public/common/icheck/custom.min.js",
						"static/template_blue/style/role/view.css",
						"css/database/progress.css",
						"js/database/progress.js",
						"css/database/backup.css",
						"static/template_blue/style/role/editnew.css",
						"public/common/bootstrapvalidator/js/bootstrapValidator.js",
						"public/common/bootstrapvalidator/css/bootstrapValidator.css",
				
						]
					var tharr = [
						"@{/common/icheck/skins/all.css}",
						"@{/common/icheck/icheck.js}",
						"@{/template_blue/script/index/getDom.js}",
						"@{/common/vakata-jstree/dist/themes/default/style.min.css}",
						"@{/common/vakata-jstree/dist/jstree.min.js}",
						"@{/common/pagination/simplePagination.css}",
						"@{/common/pagination/jquery.simplePagination.js}",
						"@{/template_blue/style/role/listREM.css}",
						"@{/template_blue/style/role/authmodal.css}",
						"@{/template_blue/style/role/addsREM.css}",
						"@{/template_blue/style/role/role.css}",
						"@{/common/icheck/custom.min.js}",
						"@{/template_blue/style/role/view.css}",
						"@{/template_blue/style/role/editnew.css}",
						"@{/common/bootstrapvalidator/js/bootstrapValidator.js}",
						"@{/common/bootstrapvalidator/css/bootstrapValidator.css}",
				
						]
		$.each(arr,function(i,v){
			if(v.substring(v.length-3) == "css"){
				$("<link>").attr({rel:"stylesheet",href:v}).appendTo("head")
			}else{
				$("<script>").attr({src:v}).appendTo("head")
			}
		})
		$.each(tharr,function(i,v){
			if(v.substring(v.length-3) == "css"){
				$("<link>").attr({rel:"stylesheet","th:href":v}).appendTo("head")
			}else{
				$("<script>").attr({"th:src":v}).appendTo("head")
			}
		})
	})
})(jQuery)
