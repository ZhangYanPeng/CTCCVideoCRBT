package com.CTCC.CRBT.Service;

import org.springframework.web.multipart.MultipartFile;

import com.CTCC.CRBT.DAO.PageResults;
import com.CTCC.CRBT.Entity.Video;

public interface IVideoService {
	public PageResults<Video> GetByPage(int pageNo, String findStr);
	public Video getByName(String name);
	public int Add(Video video);
	public Video validVideoName(String video_name);
	public String uploadFile(MultipartFile file, String path);//文件上传成功返回true
	public boolean deleteFTPFile(String pathname);
	public Video Edit(Video video);
	public Video Get(long video_id);
}
