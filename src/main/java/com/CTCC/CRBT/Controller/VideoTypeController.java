package com.CTCC.CRBT.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.CTCC.CRBT.DAO.PageResults;
import com.CTCC.CRBT.Entity.VideoType;
import com.CTCC.CRBT.Service.IVideoTypeService;

@Controller
@RequestMapping("/admin/video")
public class VideoTypeController {
	@Autowired
	IVideoTypeService videoTypeService;

	// 视频类型列表加载
	@RequestMapping(value = "/video_type_list")
	public @ResponseBody PageResults<VideoType> video_type_list(String pageNo, String findStr) {
		return videoTypeService.GetByPage(Integer.valueOf(pageNo), findStr);
	}

	// 视频类型列表下拉框
	@RequestMapping(value = "/video_type_select_list")
	public @ResponseBody List<String> video_type_select_list() {
		//System.out.println(videoTypeService.GetAllVideoType());
		return videoTypeService.GetAllVideoType();
	}

	// 视频类型添加
	@RequestMapping(value = "/video_type_add")
	public @ResponseBody int video_type_add(String type) {
		if (videoTypeService.getByName(type) != null)
			return -1;
		else
			videoTypeService.Add(new VideoType(type));
		return 1;
	}

	// 视频类型删除
	@RequestMapping(value = "/delete_video_type")
	public @ResponseBody int delete_video_type(String id) {
		return videoTypeService.Delete(Long.valueOf(id));
	}

	// 视频类型信息加载
	@RequestMapping(value = "/video_type_load")
	public @ResponseBody VideoType video_type_load(String id) {
		return videoTypeService.Get(Long.valueOf(id));
	}

	// 视频类型列表加载
	@RequestMapping(value = "/video_type_edit")
	public @ResponseBody int vide_type_edit(String id, String name) {
		if (videoTypeService.getByName(name) != null)
			return -1;
		else {
			VideoType vt = videoTypeService.Get(Long.valueOf(id));
			vt.setType_name(name);
			return videoTypeService.Edit(vt);
		}
	}
}
