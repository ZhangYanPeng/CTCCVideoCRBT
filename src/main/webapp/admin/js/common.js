function jump(t_url) {
	window.location.href = "./topage?url=" + t_url;
}

function getQueryString(name) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
	var r = window.location.search.substr(1).match(reg);
	if (r != null)
		return unescape(r[2]);
	return null;
}

function login() {
	$.ajax({
		async : false,
		cache : false,
		type : 'POST',
		crossDomain : true,
		url : "./admin/login",
		data : {
			username : $('#username').val(),
			password : $('#password').val()
		},
		dataType : "json",
		contentType : "application/x-www-form-urlencoded; charset=utf-8",
		error : function(e) {
			alert("登陆失败，请重试！");
		},
		success : function(data) {
			if(data.valid_state == 0){
				alert("该账户已被冻结！");
			}else{
				var storage = window.localStorage;
				storage["id"] = data.id;
				storage["admin"] = data;
				if (data.id != -1) {
					jump("backend");
				}
			}
		}
	});
}