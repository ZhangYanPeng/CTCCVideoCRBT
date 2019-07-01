var user_id;
$(document).ready(function() {
	user_id = getQueryString("id");
		load_data(user_id);
	});

function load_data(aid){
	console.log(aid);
	$.ajax({
		sync : false,
		cache : false,
		type : 'POST',
		crossDomain : true,
		url : "./user_load",
		data : {
			id : aid
		},
		dataType : "json",
		contentType : "application/x-www-form-urlencoded; charset=utf-8",
		error : function(e) {
			alert("网络错误，请重试");
		},
		success : function(data) {
			$("#id").val(data.usr_id);
			$("#usertel").val(data.usr_tel);
		}
	});
}

function edit() {
	if ($("#usertel").val() == '') {
		alert("用户手机号为空！");
		return;
	}
	$.ajax({
		sync : false,
		cache : false,
		type : 'POST',
		crossDomain : true,
		url : "./user_edit",
		data : {
			usr_id : $("#id").val(),
			usr_tel : $("#usertel").val(),
		},
		dataType : "json",
		contentType : "application/x-www-form-urlencoded;charset=utf-8",
		error : function(e) {
			alert("提交失败，请重试！");
		},
		success : function(data) {
			if (data.usr_id == -1) {
				alert("该用户已存在！");
			}else if (data == null) {
				alert("提交失败，请重试！");
			}else{
				alert("编辑成功！");
				load_data(usr_id);
			}
		}
	});
}

