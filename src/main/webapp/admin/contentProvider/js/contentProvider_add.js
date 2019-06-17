function save() {
	if ($("#password").val() != $("#repassword").val()) {
		alert("两次密码不一致，请检查！");
		return;
	}
	
	if ($("#password").val() == '' || $("#cpname").val() == '') {
		alert("密码为空或用户名为空！");
		return;
	}
	$.ajax({
		sync : false,
		cache : false,
		type : 'POST',
		crossDomain : true,
		url : "./contentProvider_add",
		data : {
			company : $("#company").val(),
			cp_name : $("#cpname").val(),
			cp_pwd : $("#password").val(),
		},
		dataType : "json",
		contentType : "application/x-www-form-urlencoded;charset=utf-8",
		error : function(e) {
			alert("网络错误，请重试");
		},
		success : function(data) {
			if (data == -1) {
				alert("用户已存在！");
			}else if (data <= 0) {
				alert("提交失败，请重试！");
			}else if (data == 1) {
				alert("添加成功！");
				//list(1);
				window.location.href = "./contentProvider_list.html";
			}
		}
	});
}