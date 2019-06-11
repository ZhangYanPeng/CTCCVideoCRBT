var page_num = 1;
var total_num = 0;
$(document).ready(function() {
	list(1);
});

function list(p) {
	$.ajax({
		sync : false,
		cache : false,
		type : 'POST',
		crossDomain : true,
		url : "../video_typ_list",
		data : {
			pageNo : p,
			findStr : $("#findstr").val()
		},
		dataType : "json",
		contentType : "application/x-www-form-urlencoded; charset=utf-8",
		error : function(e) {
			alert("网络错误，请重试");
		},
		success : function(data) {
			total_num = data.pageCount;
			page_num = p;
			$("#page_num").val(p);

			var tab_title = "<tr>"
			+ "<td width='10%' class='tdColor tdC'>序号</td>"
			+ "<td width='50%' class='tdColor'>视频类型</td>"
			+ "<td width='40%' class='tdColor'>操作</td>"
			+ "	</tr>";
			$("#video_type_list").html(tab_title);

			$.each(
				data.results,
				function(index, value) {
					var td_id = $('<td></td>').append(
						value.video_type_id);
					var td_un = $('<td></td>').append(
						value.type_name);
					var a_edit = $('<a></a>').attr(
						'href',
						"useradd.html?id="
						+ value.video_type_id)
						.append("<img class='operation' src='../img/update.png'></a>");
					var td_op = $('<td></td>')
						.append(a_edit)
						.append(
							"<img class='operation delban' src='../img/delete.png' onclick='javascript:removeAdmin("
							+ value.video_type_id
							+ ");' /></td>");
					var tr = $('<tr></tr>').attr(
						'height', "40px");
					tr.append(td_id).append(td_un)
					.append(td_op)
					$("#adminlist").append(tr);
				});
		}
	});
}

function valid(admin_id, valstate) {
	$.ajax({
		sync : false,
		cache : false,
		type : 'POST',
		crossDomain : true,
		url : "../valid_admin",
		data : {
			id : admin_id,
			state : valstate,
		},
		dataType : "json",
		contentType : "application/x-www-form-urlencoded;charset=utf-8",
		error : function(e) {
			alert("网络错误，请重试");
		},
		success : function(data) {
			if (data == 1) {
				list(page_num);
			}
		}
	});
}

function save() {
	if ($("#password").val() != $("#repassword").val()) {
		alert("两次密码不一致，请检查！");
		return;
	}
	$.ajax({
		sync : false,
		cache : false,
		type : 'POST',
		crossDomain : true,
		url : "add_admin",
		data : {
			username : $("#nun").val(),
			password : $("#npd").val(),
		},
		dataType : "json",
		contentType : "application/x-www-form-urlencoded;charset=utf-8",
		error : function(e) {
			alert("网络错误，请重试");
		},
		success : function(data) {
			if (data == 1) {
				alert("添加成功！");
				jump("/admin/user");
				page_num = 1;
				list(1);
			}
		}
	});
}

function removeAdmin(aid) {
	var r=confirm("是否确认删除？")
	if (r!=true)
	{
		return;
	}
	if (aid == '1') {
		alert("超级管理员无法删除！");
		return;
	}
	$.ajax({
		sync : false,
		cache : false,
		type : 'POST',
		crossDomain : true,
		url : "delete_admin",
		data : {
			id : aid
		},
		dataType : "json",
		contentType : "application/x-www-form-urlencoded;charset=utf-8",
		error : function(e) {
			alert("网络错误，请重试");
		},
		success : function(data) {
			if (data == true) {
				list(1);
				page_num = 1;
			}
		}
	});
}

function first() {
	if (page_num == 1)
		return;
	page_num = 1;
	list(1);
}

function previous() {
	if (page_num == 1)
		return;
	page_num = page_num - 1;
	list(page_num);
}

function next() {
	if (page_num == total_num)
		return;
	page_num = page_num + 1;
	list(page_num);
}

function last() {
	if (page_num == total_num)
		return;
	page_num = total_num;
	list(page_num);
}