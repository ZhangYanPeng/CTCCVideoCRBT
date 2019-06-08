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
			url : "../admin_list",
			data : {
				pageNo : p
			},
			dataType : "json",
			contentType : "application/x-www-form-urlencoded; charset=utf-8",
			error : function(e) {
				alert("网络错误，请重试");
			},
			success : function(data) {
				console.log(data);
				total_num = data.pageCount;
				page_num = p;
				$("#page_num").val(p);

				var tab_title = "<tr>"
				+ "<td width='66px' class='tdColor tdC'>序号</td>"
				+ "<td width='730px' class='tdColor'>用户名</td>"
				+ "<td width='730px' class='tdColor'>密码</td>"
				+ "<td width='130px' class='tdColor'>操作</td>"
				+ "	</tr>";
				$("#adminlist").html(tab_title);

				$.each(
					data.results,
					function(index, value) {
						var td_id = $('<td></td>').append(
							value.admin_id);
						var td_un = $('<td></td>').append(
							value.admin_name);
						var td_pd = $('<td></td>').append(
							value.admin_pwd);
						var a_edit = $('<a></a>').attr(
							'href',
							"useradd.html?id="
							+ value.admin_id);
						a_edit
						.append("<img class='operation' src='../img/update.png'></a>");
						var td_op = $('<td></td>')
						.append(a_edit)
						.append(
							"<img class='operation delban' src='../img/delete.png' onclick='javascript:removeAdmin("
							+ value.admin_id
							+ ");' /></td>");
						var tr = $('<tr></tr>').attr(
							'height', "40px");
						tr.append(td_id).append(td_un)
						.append(td_pd)
						.append(td_op);
						$("#adminlist").append(tr);
					});
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