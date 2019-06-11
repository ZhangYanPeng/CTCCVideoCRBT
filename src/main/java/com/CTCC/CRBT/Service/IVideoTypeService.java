package com.CTCC.CRBT.Service;

import com.CTCC.CRBT.DAO.PageResults;
import com.CTCC.CRBT.Entity.VideoType;

public interface IVideoTypeService {
	public int Add(VideoType videoType);
	public VideoType Get(long id);
	public int Edit(VideoType videoType);
	public int Delete(long id);
	public PageResults<VideoType> GetByPage(int pageNo, String findStr);
	public VideoType getByName(String type);
}
