var pathname;// FTP上传视频文件目录（2019-06-26/3a021862-2616-418b-8302-163decc98b48.mp4）

// 视频类型获取
$(document).ready(function() {
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
});

// 选择本地视频文件
function fileSelected() {
	var file = document.getElementById('fileUpload').files[0];
    console.log(file);
    if (file) {
        var fileSize = 0;
        if (file.size > 1024 * 1024)
            fileSize = (Math.round(file.size * 100 / (1024 * 1024)) / 100).toString() + 'MB';
        else
            fileSize = (Math.round(file.size * 100 / 1024) / 100).toString() + 'KB';
        document.getElementById('fileName').innerHTML = '文件名: ' + file.name;
        document.getElementById('fileSize').innerHTML = '文件大小: ' + fileSize;
        document.getElementById('fileType').innerHTML = '文件类型: ' + file.type;
        var url = URL.createObjectURL(file);
        console.log(url);
        // 视频信息赋值
        var formatindex=(file.type).lastIndexOf("/");
        var filenameExtension = (file.type).slice(formatindex+1);
        $("#videoformat").val(filenameExtension);
        var nameindex=(file.name).lastIndexOf(".");
        var filenameExtension = (file.name).slice(0,formatindex+2);
        $("#videoname").val(filenameExtension);
        document.getElementById("videoupload").src = url;
        $("#videoupload").attr({
			// 'src' : "file:///E:/2019618.mp4",
			"autoplay" : "autoplay",
			"loop" : "loop",
			"width" : "250px",
			"allowFullScreen" : 'true',
			"quality" : 'high',
			"align" : 'middle',
			"allowScriptAccess" : 'always'
		});
    }
}

// 上传视频文件到FTP文件服务器
function uploadFile() {
	 var formData = new FormData(); 
	 var file = document.getElementById('fileUpload').files[0];
	 formData.append("file", file);
	 $.ajax({ 
		url: "./video_upload", 
		data: formData, 
		type: "post", 
		processData: false, 
		contentType: false, 
		success: function(data) {
			pathname = data;
			alert("上传成功！") 
		}, 
		error: function() { 
			alert("上传失败！") 
		} 
	});
}

// 删除本次上传到FTP文件服务器的视频文件
function uploadCanceled() {
	console.log(pathname);
	if(pathname!=null){
		$.ajax({
			sync : false,
			cache : false,
			type : 'POST',
			crossDomain : true,
			url : "./video_upload_delete",
			data : {
				video_upload_pathname : pathname
			},
			dataType : "json",
			contentType : "application/x-www-form-urlencoded;charset=utf-8",
			error : function(e) {
				alert("网络错误，请重试");
			},
			success : function(data) {
				if (data) {
					alert("上传取消成功！");
				}else{
					alert("上传取消失败！");
				}
			}
		});
	}else{
		alert("未上传文件！") 
	}
	 
}

// 添加视频信息
function save() {
	var file = document.getElementById('fileUpload').files[0];
	if (pathname==null) {
		alert("未上传视频！");
		return;
	}
	if ($("#videoname").val() == ''||$("#videotype").val() == '' ||$("#videoprice").val() == '') {
		alert("视频名、类型、价格不允许空！");
		return;
	}
	$.ajax({
		sync : false,
		cache : false,
		type : 'POST',
		crossDomain : true,
		url : "./video_add",
		data : {
			video_name : $("#videoname").val(),
			type : $("#videotype").val(),
			price : $("#videoprice").val(),
			video_desc : $("#videodesc").val(),
			video_path : pathname,
		},
		dataType : "json",
		contentType : "application/x-www-form-urlencoded;charset=utf-8",
		error : function(e) {
			alert("网络错误，请重试");
		},
		success : function(data) {
			if (data == -1) {
				alert("视频名已存在！");
			}else if (data <= 0) {
				alert("提交失败，请重试！");
			}else if (data == 1) {
				alert("添加成功！");
				window.location.href = "./video_list.html";
			}
		}
	});
}

// 取消添加视频信息
function cancel() {
	if (pathname!=null){
		alert("将删除已上传文件！");
		$.ajax({
			sync : false,
			cache : false,
			type : 'POST',
			crossDomain : true,
			url : "./video_upload_delete",
			data : {
				video_upload_pathname : pathname
			},
			dataType : "json",
			contentType : "application/x-www-form-urlencoded;charset=utf-8",
			error : function(e) {
				alert("网络错误，请重试");
			},
			success : function(data) {
				if (data) {
					window.location.href = "./video_list.html";
				}else{
					alert("上传取消失败！");
				}
			}
		});
	}else{
		window.location.href = "./video_list.html";
	}
}
