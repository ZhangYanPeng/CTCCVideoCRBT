var page_num = 1;
var total_num = 0;
$(document).ready(function() {
	list(1);
});

function list(p) {
	var fStr = $("#findTarStr").val();
	$.ajax({
		sync : false,
		cache : false,
		type : 'POST',
		crossDomain : true,
		url : "./contentProvider_list",
		data : {
			pageNo : p,
			findStr : fStr
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
				+ "<td width='30px' class='tdColor tdC'>序号</td>"
				+ "<td width='200px' class='tdColor'>账号名</td>"
				+ "<td width='200px' class='tdColor'>账号密码</td>"
				+ "<td width='200px' class='tdColor'>公司标识</td>"
				+ "<td width='200px' class='tdColor'>操作</td>"
				+ "	</tr>";
			$("#contentProviderlist").html(tab_title);

			$.each(data.results,function(index, value) {
				var td_id = $('<td></td>').append(
					value.cp_id);
				var td_un = $('<td></td>').append(
					value.cp_name);
				var td_pw = $('<td></td>').append(
					value.cp_pwd);
				var td_comp = $('<td></td>').append(
					value.company);
				var a_edit = $('<a></a>').attr('href',"contentProvider_edit.html?id="+ value.cp_id);
				a_edit.append("<img class='operation' src='../img/update.png'></a>");
				var td_op = $('<td></td>').append(a_edit)
				.append("<img class='operation delban' src='../img/delete.png' onclick='javascript:removecontentProvider("+ value.cp_id+ ");' /></td>");
				var tr = $('<tr></tr>').attr('height',"40px");
				tr.append(td_id).append(td_un).append(td_pw).append(td_comp).append(td_op);
				$("#contentProviderlist").append(tr);
			});
		}
	});
}

function find(){
	list(1);
}

function removecontentProvider(aid) {
	var r = confirm("是否确认删除？")
	if (r != true) {
		return;
	}
	$.ajax({
		sync : false,
		cache : false,
		type : 'POST',
		crossDomain : true,
		url : "./delete_contentProvider",	
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
				alert("删除成功！");
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