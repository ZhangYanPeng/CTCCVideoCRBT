package com.CTCC.CRBT.Service;

import com.CTCC.CRBT.DAO.PageResults;
import com.CTCC.CRBT.Entity.Video;

public interface IVideoService {
	public PageResults<Video> GetByPage(int pageNo, String findStr);
	public Video getByName(String name);
	public int Add(Video video);
	public Video validVideoName(String video_name);
}
