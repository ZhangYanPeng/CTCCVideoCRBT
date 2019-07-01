package com.CTCC.CRBT.Controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.CTCC.CRBT.Common.Constant;
import com.CTCC.CRBT.DAO.PageResults;
import com.CTCC.CRBT.Entity.Video;
import com.CTCC.CRBT.Service.IVideoService;
import com.CTCC.CRBT.Service.IVideoTypeService;

@Controller
@RequestMapping("/admin/video")
public class VideoController {
	@Autowired
	IVideoService videoService;

	@Autowired
	IVideoTypeService videoTypeService;

	// 视频列表加载
	@RequestMapping(value = "/video_list")
	public @ResponseBody PageResults<Video> video_list(String pageNo, String findStr) {
		return videoService.GetByPage(Integer.valueOf(pageNo), findStr);
	}

	// 视频添加
	@RequestMapping(value = "/video_add")
	public @ResponseBody int video_add(String video_name, String type, String price, String video_path, String video_desc) {
		Video video = new Video();
		video.setVideo_name(video_name);
		video.setType(videoTypeService.getByName(type));
		video.setPrice(Double.valueOf(price));
		video_path = "http://" + Constant.ftpIp + "/" + video_path;// FTP服务器+Nginx代理
		video.setVideo_path(video_path);
		video.setVideo_desc(video_desc);
		if (videoService(video) == 0)
			return -1;
		// System.out.println(video_path);
		return videoService.Add(video);
	}

	private int videoService(Video video) {
		// TODO Auto-generated method stub
		if (videoService.validVideoName(video.getVideo_name()) != null)
			return 0;
		return 1;
	}

	// 视频上传
	@RequestMapping(value = "/video_upload")
	public @ResponseBody String video_upload(HttpServletRequest req) {
		// TODO Auto-generated method stub
		MultipartHttpServletRequest request = (MultipartHttpServletRequest) req;
		MultipartFile file = request.getFile("file");
		// 获取到upload的绝对路径（upload是目录保存在tomcat下）workspace\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\CTCCVideoCRBT\
		String path = request.getSession().getServletContext().getRealPath("/uploadfiles/tmp");
		if (file != null) {
			return videoService.uploadFile(file, path);
		} else {
			return null;
		}
	}

	// 视频上传取消
	@RequestMapping(value = "/video_upload_delete")
	public @ResponseBody boolean video_upload_delete(String video_upload_pathname) {
		// TODO Auto-generated method stub
		return videoService.deleteFTPFile(video_upload_pathname);

	}

	// 根据id获取video对象
	@RequestMapping(value = "/video_load")
	public @ResponseBody Video user_load(String id) {
		return videoService.Get(Long.valueOf(id));
	}

	// 视频编辑
	@RequestMapping(value = "/video_edit")
	public @ResponseBody Video video_edit(String video_id, String video_name, String type, String price, String video_desc) {
		Video video = new Video();
		video = videoService.Get(Long.valueOf(video_id));
		video.setVideo_name(video_name);
		video.setType(videoTypeService.getByName(type));
		video.setPrice(Double.valueOf(price));
		video.setVideo_desc(video_desc);
		return videoService.Edit(video);
	}
}
