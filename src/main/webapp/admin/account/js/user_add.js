function save() {
	if ($("#usertel").val() == '') {
		alert("用户手机号为空！");
		return;
	}
	$.ajax({
		sync : false,
		cache : false,
		type : 'POST',
		crossDomain : true,
		url : "./user_add",
		data : {
			usr_tel : $("#usertel").val(),
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
				list(1);
				//window.location.href = "./user_list.html";
			}
		}
	});
}