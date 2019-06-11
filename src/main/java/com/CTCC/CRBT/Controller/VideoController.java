package com.CTCC.CRBT.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.CTCC.CRBT.DAO.PageResults;
import com.CTCC.CRBT.Entity.VideoType;
import com.CTCC.CRBT.Service.IVideoTypeService;

@Controller
@RequestMapping("/admin/video")
public class VideoController {
	@Autowired
	IVideoTypeService videoTypeService;

	// 视频类型列表加载
	@RequestMapping(value = "/video_type_list")
	public @ResponseBody PageResults<VideoType> video_type_list(String pageNo, String findStr) {
		return videoTypeService.GetByPage(Integer.valueOf(pageNo), findStr);
	}
	
	// 视频类型列表加载
	@RequestMapping(value = "/video_type_add")
	public @ResponseBody int video_type_add(String type) {
		if(videoTypeService.getByName(type) != null)
			return -1;
		else
			videoTypeService.Add(new VideoType(type));
		return 1;
	}
}
