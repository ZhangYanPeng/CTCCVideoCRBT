var page_num = 1;
var total_num = 0;
var i = 0;
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
		url : "./video_list",
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

			$("#videolist").html("");
			for (i = 0; i < data.totalCount / 4; i++) {
				var tr = $('<tr></tr>').attr('height', "40px");
				for (j = 0; j < 4 && (4 * i + j) < data.totalCount; j++) {
					console.log(data.results[4 * i + j].video_path);
					var td_video = $('<video muted></video>').attr({
						'src' : data.results[4 * i + j].video_path,
						"controls" : "controls",
						"autoplay" : "autoplay",
						"loop" : "loop",
						"width" : "250px",
						"height" : "250px",
						"allowFullScreen" : 'true',
						"quality" : 'high',
						"align" : 'middle',
						"allowScriptAccess" : 'always'
					});
					var td_edit = $('<a></a>')
							.attr("href", "./video_edit.html?id="
						+ data.results[4 * i + j].video_id);
					var td_id = $('<p></p>').append(
							data.results[4 * i + j].video_id);
					var td_name = $('<p></p>').append(
							data.results[4 * i + j].video_name);
					td_edit.append(td_id).append(td_name);
					var td = $('<td></td>').append(td_video).append(td_edit);
					tr.append(td);
					$("#videolist").append(tr);
				}
			}
		}
	});
}

function find() {
	list(1);
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