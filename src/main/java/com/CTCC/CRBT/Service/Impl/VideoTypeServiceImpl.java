package com.CTCC.CRBT.Service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.CTCC.CRBT.Common.Constant;
import com.CTCC.CRBT.DAO.PageResults;
import com.CTCC.CRBT.DAO.Impl.VideoTypeDAOImpl;
import com.CTCC.CRBT.Entity.VideoType;
import com.CTCC.CRBT.Service.IVideoTypeService;

@Service
public class VideoTypeServiceImpl implements IVideoTypeService {
	@Autowired
	VideoTypeDAOImpl videoTypeDAO;

	@Override
	@Transactional
	public int Add(VideoType videoType) {
		// TODO Auto-generated method stub
		try{
			videoTypeDAO.saveOrUpdate(videoType);
			return 1;
		}catch(Exception e){
			return 0;
		}
	}

	@Override
	@Transactional
	public VideoType Get(long id) {
		// TODO Auto-generated method stub
		try{
			return videoTypeDAO.get(id);
		}catch(Exception e){
			return null;
		}
	}

	@Override
	@Transactional
	public VideoType Edit(VideoType videoType) {
		// TODO Auto-generated method stub
		try{
			videoTypeDAO.update(videoType);
			return videoTypeDAO.get(videoType.getVideo_type_id());
		}catch(Exception e){
			return videoType;
		}
	}

	@Override
	@Transactional
	public int Delete(long id) {
		// TODO Auto-generated method stub
		try{
			videoTypeDAO.deleteById(id);
			return 1;
		}catch(Exception e){
			return 0;
		}
	}

	@Override
	@Transactional
	public PageResults<VideoType> GetByPage(int pageNo, String findStr) {
		// TODO Auto-generated method stub
		try{
			String hql = "from VideoType where type_name like ?";
			String countHql = "select COUNT(*) from VideoType where type_name like ?";
			Object[] params = {findStr};
			return videoTypeDAO.findPageByFetchedHql(hql, countHql, pageNo, Constant.PAGESIZE, params);
		}catch(Exception e){
			return null;
		}
	}

}
