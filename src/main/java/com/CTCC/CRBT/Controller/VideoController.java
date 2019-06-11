package com.CTCC.CRBT.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.CTCC.CRBT.DAO.PageResults;
import com.CTCC.CRBT.Entity.VideoType;
import com.CTCC.CRBT.Service.IVideoTypeService;

@Controller
@RequestMapping("/video")
public class VideoController {
	@Autowired
	IVideoTypeService videoTypeService;

	// 管理员列表加载
	@RequestMapping(value = "/video_typ_list")
	public @ResponseBody PageResults<VideoType> adminList(String pageNo, String findStr) {
		return videoTypeService.GetByPage(Integer.valueOf(pageNo), findStr);
	}
}
