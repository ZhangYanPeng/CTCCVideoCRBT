package com.CTCC.CRBT.Service.Impl;

import java.util.List;

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
	public int Edit(VideoType videoType) {
		// TODO Auto-generated method stub
		try{
			videoTypeDAO.update(videoType);
			return 1;
		}catch(Exception e){
			return 0;
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
			String hql = "from VideoType";
			String countHql = "select COUNT(*) from VideoType";
			if(findStr == null || findStr.equals("")){
				Object[] params = {};
				return videoTypeDAO.findPageByFetchedHql(hql, countHql, pageNo, Constant.PAGESIZE, params);	
			}else{
				hql += " where type_name like ?";
				countHql += " where type_name like ?";
				Object[] params = {findStr};
				return videoTypeDAO.findPageByFetchedHql(hql, countHql, pageNo, Constant.PAGESIZE, params);
			}
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

	@Override
	@Transactional
	public VideoType getByName(String type) {
		// TODO Auto-generated method stub
		try{
			String hql = "from VideoType where type_name = ?";
			Object[] params = {type};
			return videoTypeDAO.getByHQL(hql, params);
		}catch(Exception e){
			return null;
		}
	}

	@Override
	@Transactional
	public List<String> GetAllVideoType() {
		// TODO Auto-generated method stub
		try{
			String hql = "select type_name from VideoType";
			return videoTypeDAO.getListByHQL(hql);
		}catch(Exception e){
			return null;
		}
	}

//	@Override
//	public VideoType GetByTypeName(String typeName) {
//		// TODO Auto-generated method stub
//		try{
//			String hql = "select COUNT(*) from VideoType where type_name = ?";
//			Object[] params = {type};
//			return videoTypeDAO.getByHQL(hql, params);
//		}catch(Exception e){
//			return null;
//		}
//	}

}
