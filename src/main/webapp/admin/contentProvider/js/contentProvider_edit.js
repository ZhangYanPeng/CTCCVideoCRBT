var cp_id;
$(document).ready(function() {
	cp_id = getQueryString("id");
	load_data(cp_id);
});

function load_data(aid) {
	$.ajax({
		sync : false,
		cache : false,
		type : 'POST',
		crossDomain : true,
		url : "./contentProvider_load",
		data : {
			id : aid
		},
		dataType : "json",
		contentType : "application/x-www-form-urlencoded; charset=utf-8",
		error : function(e) {
			alert("网络错误，请重试");
		},
		success : function(data) {
			$("#id").val(data.cp_id);
			$("#cpname").val(data.cp_name);
			$("#company").val(data.company);
			$("#password").val(data.cp_pwd);
			$("#repassword").val(data.cp_pwd);
		}
	});
}

function edit() {
	if ($("#password").val() != $("#repassword").val()) {
		alert("两次密码不一致，请检查！");
		return;
	}
	if ($("#password").val() == '' || $("#cpname").val() == ''
			|| $("#company").val() == '') {
		alert("公司名、账号、密码均不为空！");
		return;
	}
	$.ajax({
		sync : false,
		cache : false,
		type : 'POST',
		crossDomain : true,
		url : "./contentProvider_edit",
		data : {
			cp_id : $("#id").val(),
			cp_name : $("#cpname").val(),
			company : $("#company").val(),
			cp_pwd : $("#password").val(),
		},
		dataType : "json",
		contentType : "application/x-www-form-urlencoded;charset=utf-8",
		error : function(e) {
			alert("提交失败，请重试！");
		},
		success : function(data) {
			if (data.cp_id == -1) {
				alert("该内容提供商已存在！");
			} else if (data == null) {
				alert("提交失败，请重试！");
			} else {
				alert("编辑成功！");
				load_data(cp_id);
			}
		}
	});
}
