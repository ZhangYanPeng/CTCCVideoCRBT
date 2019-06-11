var page_num = 1;
var total_num = 0;
$(document).ready(function() {
	list(1);
});

function find(){
	list(1);
}

function list(p) {
	$.ajax({
		sync : false,
		cache : false,
		type : 'POST',
		crossDomain : true,
		url : "./video_type_list",
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
			+ "<td width='35%' class='tdColor'>视频类型</td>"
			+ "<td width='35%' class='tdColor'>相关视频总数</td>"
			+ "<td width='20%' class='tdColor'>操作</td>"
			+ "	</tr>";
			$("#video_type_list").html(tab_title);

			$.each(
				data.results,
				function(index, value) {
					var td_id = $('<td></td>').append(
						value.video_type_id);
					var td_un = $('<td></td>').append(
						value.type_name);
					var td_num = $('<td></td>').append(
						value.related_video_num);
					var a_edit = $('<a></a>').attr(
						'href',"video_type_edit.html?id="
						+ value.video_type_id)
						.append("<img class='operation' src='../img/update.png'></a>");
					var td_op = $('<td></td>')
						.append(a_edit)
						.append(
							"<img class='operation delban' src='../img/delete.png' onclick='javascript:removeVideType("
							+ value.video_type_id
							+ ");' /></td>");
					var tr = $('<tr></tr>').attr(
						'height', "40px");
					tr.append(td_id).append(td_un).append(td_num).append(td_op)
					$("#video_type_list").append(tr);
				});
		}
	});
}

function save() {
	$.ajax({
		sync : false,
		cache : false,
		type : 'POST',
		crossDomain : true,
		url : "./video_type_add",
		data : {
			type : $("#newTypeName").val()
		},
		dataType : "json",
		contentType : "application/x-www-form-urlencoded;charset=utf-8",
		error : function(e) {
			alert("网络错误，请重试");
		},
		success : function(data) {
			if (data == 1) {
				alert("添加成功！");
				list(1);
			}else if (data == 1) {
				alert("该类型已经存在！");
			}else{
				alert("网络错误，请重试!");
			}
		}
	});
}

function removeVideType(vtid) {
	var r=confirm("是否确认删除？")
	if (r!=true)
	{
		return;
	}
	$.ajax({
		sync : false,
		cache : false,
		type : 'POST',
		crossDomain : true,
		url : "./delete_video_type",
		data : {
			id : vtid
		},
		dataType : "json",
		contentType : "application/x-www-form-urlencoded;charset=utf-8",
		error : function(e) {
			alert("网络错误，请重试");
		},
		success : function(data) {
			if (data == true) {
				list(1);
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