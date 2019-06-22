package com.CTCC.CRBT.Service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.CTCC.CRBT.Common.Constant;
import com.CTCC.CRBT.DAO.PageResults;
import com.CTCC.CRBT.DAO.Impl.VideoDAOImpl;
import com.CTCC.CRBT.Entity.Video;
import com.CTCC.CRBT.Service.IVideoService;

@Service
public class VideoServiceImpl implements IVideoService {
	@Autowired
	VideoDAOImpl videoDAO;

	@Override
	@Transactional
	public PageResults<Video> GetByPage(int pageNo, String findStr) {
		// TODO Auto-generated method stub
		System.err.println(pageNo);
		System.err.println(findStr);
		try{
			String hql = "from Video";
			String countHql = "select COUNT(*) from Video";
			if(findStr == null || findStr.equals("")){
				Object[] params = {};
				return videoDAO.findPageByFetchedHql(hql, countHql, pageNo, Constant.PAGESIZE, params);	
			}else{
				hql += " where video_name like ?";
				countHql += " where video_name like ?";
				Object[] params = {"%"+findStr+"%"};
				return videoDAO.findPageByFetchedHql(hql, countHql, pageNo, Constant.PAGESIZE, params);
			}
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

	@Override
	@Transactional
	public Video getByName(String name) {
		// TODO Auto-generated method stub
		try{
			String hql = "from Video where video_name = ?";
			Object[] params = {name};
			return videoDAO.getByHQL(hql, params);
		}catch(Exception e){
			return null;
		}
	}

	@Override
	public int Add(Video video) {
		// TODO Auto-generated method stub
		try{
			videoDAO.saveOrUpdate(video);
		}catch(Exception  e){
			return 0;
		}
		return 1;
		
	}

	@Override
	public Video validVideoName(String video_name) {
		// TODO Auto-generated method stub
		try{
			String hql = "from Video where video_name = ?";
			Object[] objs = {video_name};
			return videoDAO.getByHQL(hql, objs);
		}catch(Exception e){
			return null;
		}
	}

}
