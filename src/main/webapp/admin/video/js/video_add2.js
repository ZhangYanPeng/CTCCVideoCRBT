var flag=0;
function fileSelected() {
	var file = document.getElementById('fileToUpload').files[0];
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
        var dir = URL.createObjectURL(file);
        console.log(dir);
        document.getElementById("videoupload").src = dir;
        $("#videoupload").attr({
			//'src' : "file:///E:/2019618.mp4",
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
	var uploadFile = document.getElementById('fileToUpload').files[0];
	if(uploadFile==null){
		alert("未选择文件！");
		return;
	}
	flag=0;
    var fd = new FormData();
    var url = 'http://192.168.1.251/';
    fd.append('fileToUpload', uploadFile);
    var xhr = new XMLHttpRequest();
    xhr.upload.addEventListener("progress", uploadProgress, false);
    xhr.addEventListener("load", uploadComplete, false);
    xhr.addEventListener("error", uploadFailed, false);
    xhr.addEventListener("abort", uploadCanceled, false);
    xhr.open("POST", url, true);
    xhr.send(fd);
    console.log(xhr);
}

function uploadProgress(evt) {
    if (evt.lengthComputable) {
        var percentComplete = Math.round(evt.loaded * 100 / evt.total);
        document.getElementById('progressNumber').innerHTML = percentComplete.toString() + '%';
    } else {
        document.getElementById('progressNumber').innerHTML = 'unable to compute';
    }
}

function uploadComplete(evt) {
    /* 服务器端返回响应时候触发event事件 */
    alert("成功上传文件！");
    flag =1;
}

function uploadFailed(evt) {
    alert("There was an error attempting to upload the file.");
}

function uploadCanceled(evt) {
    alert("The upload has been canceled by the user or the browser dropped the connection.");
}

function save() {
	if (!flag) {
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
			admin_path : url + document.getElementById('fileToUpload').files[0].name,
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