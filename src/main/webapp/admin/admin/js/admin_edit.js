var admin_id;
$(document).ready(function() {
	admin_id = getQueryString("id");
		load_data(admin_id);
	});

function load_data(aid){
	console.log(aid);
	$.ajax({
		sync : false,
		cache : false,
		type : 'POST',
		crossDomain : true,
		url : "./admin_load",
		data : {
			id : aid
		},
		dataType : "json",
		contentType : "application/x-www-form-urlencoded; charset=utf-8",
		error : function(e) {
			alert("网络错误，请重试");
		},
		success : function(data) {
			$("#id").val(data.admin_id);
			$("#username").val(data.admin_name);
			$("#admindesc").val(data.admin_desc);
			$("#validstate").val(data.valid_state);
			$("#password").val(data.admin_pwd);
			$("#repassword").val(data.admin_pwd);
		}
	});
}

function edit() {
	if ($("#password").val() != $("#repassword").val()) {
		alert("两次密码不一致，请检查！");
		return;
	}
	
	if ($("#password").val() == '' || $("#username").val() == '') {
		alert("密码为空或用户名为空！");
		return;
	}
	$.ajax({
		sync : false,
		cache : false,
		type : 'POST',
		crossDomain : true,
		url : "./admin_edit",
		data : {
			admin_id : $("#id").val(),
			admin_name : $("#username").val(),
			admin_desc : $("#admindesc").val(),
			valid_state : $("#validstate").val(),
			admin_pwd : $("#password").val(),
		},
		dataType : "json",
		contentType : "application/x-www-form-urlencoded;charset=utf-8",
		error : function(e) {
			alert("提交失败，请重试！");
		},
		success : function(data) {
			if (data.admin_id == -1) {
				alert("管理员名已存在！");
			}else if (data == null) {
				alert("提交失败，请重试！");
			}else{
				alert("编辑成功！");
				window.location.href = "./admin_list.html";
			}
		}
	});
}

