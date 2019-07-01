var pathname;

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