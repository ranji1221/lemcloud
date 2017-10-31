// 表单验证部分
$(function(){
	// 角色部分-----------------------------
	// 角色编辑
	$('.form-role-editlg').bootstrapValidator({
        message: '验证未通过！',
        feedbackIcons: {
            valid: 'icon-ok-sign',
            invalid: 'icon-exclamation-sign',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
        	// 此处认真核对验证表单的name值
            username: {//input对应的name值
                container: '.errormessage-role-edit-name',//显示错误提示的容器选择器
                validators: {
                    notEmpty: {
                        message: '用户名必须填写！'
                    },
                    regexp: {
				        // regexp: /^[A-Z\s]+$/i,
				        regexp:/^[^'"#$%&\^*》>,."<《？，。！@#￥%……’”：/；]+$/,
				        message: '您输入了特殊符号！'
                    }
                }
            },
        },
        submitButtons: 'button.submitbtn-role-edit',//设置提交按钮选择器
    });
	// 角色授权
	$('.form-role-autlg').bootstrapValidator({
        message: '验证未通过',
        feedbackIcons: {
            valid: 'icon-ok-sign',
            invalid: 'icon-exclamation-sign',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
        	// 此处认真核对验证表单的name值
            username: {//input对应的name值
                container: '.errormessage-role-aut-name',
                validators: {
                    notEmpty: {
                        message: '用户名必须填写！'
                    },
                    regexp: {
				        regexp:/^[^'"#$%&\^*》>,."<《？，。！@#￥%……’”：/；]+$/,
				        message: '您输入了特殊符号！'
                    }
                }
            },
   			//remote: {
			// 	url: 'remote.php',
			// 	message: '用户名已存在'
            //	data : '',这里默认会传递该验证字段的值到后端
            //	delay:2000 
			// },
        },
        submitButtons: 'button.submitbtn-role-aut',//设置提交按钮选择器
    });
	// 角色添加
	$('.addrole .form-role-add').bootstrapValidator({
        message: '验证未通过！',
        feedbackIcons: {
            valid: 'icon-ok-sign',
            invalid: 'icon-exclamation-sign',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
        	// 此处认真核对验证表单的name值
            username: {//input对应的name值
                container: '.errormessage-role-add-name',//显示错误提示的容器选择器
                validators: {
                    notEmpty: {
                        message: '用户名必须填写！'
                    },
                    regexp: {
				        regexp:/^[^'"#$%&\^*》>,."<《？，。！@#￥%……’”：/；]+$/,
				        message: '您输入了特殊符号！'
                    }
                }
            },
        },
        submitButtons: 'button.submitbtn-role-add',//设置提交按钮选择器
    });

	// 用户部分-----------------------------
	// 用户编辑
	$('.form-user-editlg').bootstrapValidator({
        message: '验证未通过！',
        feedbackIcons: {
            valid: 'icon-ok-sign',
            invalid: 'icon-exclamation-sign',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
        	// 此处认真核对验证表单的name值
            username: {//input对应的name值
                container: '.errormessage-user-edit-name',//显示错误提示的容器选择器
                validators: {
                    notEmpty: {
                        message: '用户名必须填写！'
                    },
                    regexp: {
				        regexp:/^[^'"#$%&\^*》>,."<《？，。！@#￥%……’”：/；]+$/,
				        message: '您输入了特殊符号！'
                    }
                }
            },
            phone: {//input对应的name值
                container: '.errormessage-user-edit-phone',//显示错误提示的容器选择器
                validators: {
                    notEmpty: {
                        message: '电话号码必须填写！'
                    },
                    stringLength: {
                            max: 11,
                            message: '用户名长度必须11位'
                        },
                    regexp:{
                    	regexp:/^1[34578]\d{9}$/,
                    	mwssage:"请输入正确的电话号"
                    },
                    digits: {
				        message: '电话号码必须为数字！'
                    }
                }
            },
            email: {//input对应的name值
                container: '.errormessage-user-edit-email',//显示错误提示的容器选择器
                validators: {
                	regexp:{
                		regexp:/^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+/ ,
                		message:'您输入了特殊符'
                	},
                    notEmpty: {
                        message: '邮箱地址必须填写！'
                    },
                    emailAddress: {
				        message: '请输入合法地址！'
                    }
                }
            },
        },
        submitButtons: 'button.submitbtn-user-edit',//设置提交按钮选择器
    });
	// 用户授权
	$('.form-user-autlg').bootstrapValidator({
        message: '验证未通过！',
        feedbackIcons: {
            valid: 'icon-ok-sign',
            invalid: 'icon-exclamation-sign',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
        	// 此处认真核对验证表单的name值
            username: {//input对应的name值
                container: '.errormessage-user-aut-name',
                validators: {
                    notEmpty: {
                        message: '用户名必须填写！'
                    },
                    regexp: {
				        regexp:/^[^'"#$%&\^*》>,."<《？，。！@#￥%……’”：/；]+$/,
				        message: '您输入了特殊符号！'
                    }
                }
            },
        },
        submitButtons: 'button.submitbtn-user-aut',//设置提交按钮选择器
    });
	// 用户添加
	$('.adduser .form-user-add').bootstrapValidator({
        message: '验证未通过！',
        feedbackIcons: {
            valid: 'icon-ok-sign',
            invalid: 'icon-exclamation-sign',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
        	// 此处认真核对验证表单的name值
            username: {//input对应的name值
                container: '.errormessage-user-add-name',//显示错误提示的容器选择器
                validators: {
                    notEmpty: {
                        message: '用户名必须填写！'
                    },
                    regexp: {
				        regexp:/^[^'"#$%&\^*》>,."<《？，。！@#￥%……’”：/；]+$/,
				        message: '您输入了特殊符号！'
                    }
                }
            },
            phone: {//input对应的name值
                container: '.errormessage-user-add-phone',//显示错误提示的容器选择器
                validators: {
                    notEmpty: {
                        message: '电话号码必须填写！'
                    },
                    digits: {
				        message: '电话号码必须为数字！'
                    }
	                // notEmpty: {
                 //        message: '手机号码不能为空!'
                 //    },
                 //    stringLength: {
                 //        min: 11,
                 //        max: 11,
                 //        message: '请输入11位手机号码!'
                 //    },
                 //    regexp: {
                 //        regexp: /^1[3|5|8]{1}[0-9]{9}$/,
                 //        message: '请输入正确的手机号码!'
                 //    }
                }
            },
            email: {//input对应的name值
                container: '.errormessage-user-add-email',//显示错误提示的容器选择器
                validators: {
                    notEmpty: {
                        message: '邮箱地址必须填写！'
                    },
                    emailAddress: {
				        message: '请输入合法地址！'
                    }
                }
            },
        },
        submitButtons: 'button.submitbtn-user-add',//设置提交按钮选择器
    });

	// 资源部分-----------------------------
	// 资源编辑
	$('.form-source-editlg').bootstrapValidator({
        message: '验证未通过！',
        feedbackIcons: {
            valid: 'icon-ok-sign',
            invalid: 'icon-exclamation-sign',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
        	// 此处认真核对验证表单的name值
        	username: {//input对应的name值
                container: '.errormessage-source-edit-name',//显示错误提示的容器选择器
                validators: {
                    notEmpty: {
                        message: '资源名必须填写！'
                    },
                    regexp: {
				        regexp:/^[^'"#$%&\^*》>,."<《？，。！@#￥%……’”：/；]+$/,
				        message: '您输入了特殊符号！'
                    }
                }
            },
        },
        submitButtons: 'button.submitbtn-source-edit',//设置提交按钮选择器
    });
	// 资源添加
	$('.addsource .form-source-add').bootstrapValidator({
        message: '验证未通过',
        feedbackIcons: {
            valid: 'icon-ok-sign',
            invalid: 'icon-exclamation-sign',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
        	// 此处认真核对验证表单的name值
            resourcesname: {//input对应的name值
                container: '.errormessage-source-add-name',//显示错误提示的容器选择器
                validators: {
                    notEmpty: {
                        message: '资源名必须填写！'
                    },
                    regexp: {
				        regexp:/^[^'"#$%&\^*》>,."<《？，。！@#￥%……’”：/；]+$/,
				        message: '您输入了特殊符号！'
                    }
                }
            },
        },
        submitButtons: 'button.submitbtn-source-add',//设置提交按钮选择器
    });
    // 数据库管理部分-----------------------------
	// 备份数据库简单版
	$('.form-database-backup').bootstrapValidator({
        message: '验证未通过！',
        feedbackIcons: {
            valid: 'icon-ok-sign',
            invalid: 'icon-exclamation-sign',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
        	// 此处认真核对验证表单的name值
            backupname: {//input对应的name值
                container: '.errormessage-database-backup-name',//显示错误提示的容器选择器
                validators: {
                    notEmpty: {
                        message: '备份文件名必须填写！'
                    },
                    regexp: {
				        regexp:/^[^'"#$%&\^*》>,."<《？，。！@#￥%……’”：/；]+$/,
				        message: '您输入了特殊符号！'
                    }
                }
            },
        },
        submitButtons: 'button.submitbtn-database-backup',//设置提交按钮选择器
    });
	// // 重置表单验证规则方法貌似将来一定会用到
    // $(formName).data("bootstrapValidator").resetForm(); 
})