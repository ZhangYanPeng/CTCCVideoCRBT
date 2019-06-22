package com.CTCC.CRBT.Controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.CTCC.CRBT.Common.Constant;
import com.CTCC.CRBT.DAO.PageResults;
import com.CTCC.CRBT.Entity.Video;
import com.CTCC.CRBT.Entity.VideoType;
import com.CTCC.CRBT.Service.IVideoService;

@Controller
@RequestMapping("/admin/video")
public class VideoController {
	@Autowired
	IVideoService videoService;

	// 视频类型列表加载
	@RequestMapping(value = "/video_list")
	public @ResponseBody PageResults<Video> video_list(String pageNo, String findStr) {
		return videoService.GetByPage(Integer.valueOf(pageNo), findStr);
	}

	@RequestMapping("/video_upload")
	public String video_upload(HttpServletRequest request) {
		try {
			DiskFileItemFactory factory = new DiskFileItemFactory();
			// 设置缓冲区大小，这里是4kb
			factory.setSizeThreshold(4096);
			ServletFileUpload upload = new ServletFileUpload(factory);
			// 解决文件名称乱码
			upload.setHeaderEncoding("utf-8");
			// 设置最大文件尺寸，这里是10MB
			upload.setSizeMax(10485760);
			String path = request.getSession().getServletContext().getRealPath("Constant.UPLOAD_URL");

			List<FileItem> itemsList = upload.parseRequest(request);
			for (int i = 0; i < itemsList.size(); i++) {
				FileItem fileItem = itemsList.get(i);
				String fileName = fileItem.getFieldName();
				File dirFile = new File(path, fileName);
				System.out.println("dir.exists()>>" + dirFile.exists());
				if (!dirFile.exists()) {
					dirFile.createNewFile();
				}
				System.out.println("dir.exists()>>" + dirFile.exists());
				InputStream inputStream = fileItem.getInputStream();
				FileOutputStream outputStream = new FileOutputStream(dirFile);
				int len = 0;
				byte[] bt = new byte[1024 * 1024];
				while ((len = inputStream.read(bt)) != -1) {
					outputStream.write(bt, 0, len);
				}
				inputStream.close();
				outputStream.flush();
				outputStream.close();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "ok";
	}

	@RequestMapping(value = "/video_add")
	public @ResponseBody int user_add(String video_name, String type, String price, String video_path) {
		Video video = new Video();
		video.setVideo_name(video_name);
		video.setType(new VideoType(type));
		video.setPrice(Double.valueOf(price));
		video.setVideo_path(video_path);
		if (videoService(video) == 0)
			return -1;
		return videoService.Add(video);
	}

	private int videoService(Video video) {
		// TODO Auto-generated method stub
		if (videoService.validVideoName(video.getVideo_name()) != null)
			return 0;
		return 1;
	}
}
