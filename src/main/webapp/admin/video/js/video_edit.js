var video_id;
$(document).ready(function() {
	//console.log("1111");
	video_id = getQueryString("id");
	load_data(video_id);
	load_type();
});

function load_data(video_id){
	$.ajax({
		sync : false,
		cache : false,
		type : 'POST',
		crossDomain : true,
		url : "./video_load",
		data : {
			id : video_id
		},
		dataType : "json",
		contentType : "application/x-www-form-urlencoded; charset=utf-8",
		error : function(e) {
			alert("网络错误，请重试");
		},
		success : function(data) {
			$("#videoname").val(data.video_name);
			//$("#videotype").val(data.type.type_name);
			//load_type(data.type.type_name);
			$("#videotype").val(data.type.type_name);//设置默认选中
			$("#videodesc").val(data.video_desc);
			$("#videoprice").val(data.price);
			$("#videoedit").attr({
				'src' : data.video_path,
				"autoplay" : "autoplay",
				"loop" : "loop",
				"width" : "250px",
				"allowFullScreen" : 'true',
				"quality" : 'high',
				"align" : 'middle',
				"allowScriptAccess" : 'always'
			});
		}
	});
}

function load_type(){
	$.ajax({
		sync : false,
		cache : false,
		type : 'POST',
		crossDomain : true,
		url : "./video_type_select_list",
		data : null,
		dataType : "json",
		contentType : "application/x-www-form-urlencoded; charset=utf-8",
		error : function(e) {
			alert("网络错误，请重试");
		},
		success : function(data) {
			for (var i = 0; i < data.length; i++) {
                // 先创建好select里面的option元素
				var option = document.createElement("option");
                // 转换DOM对象为JQ对象,好用JQ里面提供的方法 给option的value赋值
				$(option).val(data[i]);
                // 给option的text赋值,这就是你点开下拉框能够看到的东西
				$(option).text(data[i]);
                // 获取select 下拉框对象,并将option添加进select
				$('#videotype').append(option);
			}
		}
	});
}

function edit() {
	if ($("#videoname").val() == ''||$("#videotype").val() == '' ||$("#videoprice").val() == '') {
		alert("视频名、类型、价格不允许空！");
		return;
	}
	$.ajax({
		sync : false,
		cache : false,
		type : 'POST',
		crossDomain : true,
		url : "./video_edit",
		data : {
			video_id : video_id,
			video_name : $("#videoname").val(),
			type : $("#videotype").val(),
			price : $("#videoprice").val(),
			video_desc : $("#videodesc").val()
		},
		dataType : "json",
		contentType : "application/x-www-form-urlencoded;charset=utf-8",
		error : function(e) {
			alert("提交失败，请重试！");
		},
		success : function(data) {
			if (data == -1) {
				alert("视频名已存在！");
			}else if (data == 0) {
				alert("提交失败，请重试！");
			}else{
				alert("编辑成功！");
				load_data(video_id);
			}
		}
	});
}