var vt_id;
$(document).ready(function() {
	vt_id = getQueryString("id");
	load_data(vt_id);
});

function load_data(vt_id){
	$.ajax({
		sync : false,
		cache : false,
		type : 'POST',
		crossDomain : true,
		url : "./video_type_load",
		data : {
			id : vt_id
		},
		dataType : "json",
		contentType : "application/x-www-form-urlencoded; charset=utf-8",
		error : function(e) {
			alert("网络错误，请重试");
		},
		success : function(data) {
			$("#id").val(data.video_type_id);
			$("#vname").val(data.type_name);
			$("#vsum").val(data.related_video_num);
		}
	});
}

function edit() {
	if ($("#vname").val() == '' ) {
		alert("视频类型名称不能为空！");
		return;
	}
	$.ajax({
		sync : false,
		cache : false,
		type : 'POST',
		crossDomain : true,
		url : "./video_type_edit",
		data : {
			id : $("#id").val(),
			name : $("#vname").val(),
		},
		dataType : "json",
		contentType : "application/x-www-form-urlencoded;charset=utf-8",
		error : function(e) {
			alert("提交失败，请重试！");
		},
		success : function(data) {
			if (data == -1) {
				alert("视频类型名已存在！");
			}else if (data == 0) {
				alert("提交失败，请重试！");
			}else{
				alert("编辑成功！");
				load_data(vt_id);
			}
		}
	});
}

