package com.CTCC.CRBT.Service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.CTCC.CRBT.Common.Constant;
import com.CTCC.CRBT.DAO.PageResults;
import com.CTCC.CRBT.DAO.Impl.ContentProviderDAOImpl;
import com.CTCC.CRBT.Entity.ContentProvider;
import com.CTCC.CRBT.Service.IContentProviderService;

@Service
public class ContentProviderServiceImpl implements IContentProviderService {
	
	@Autowired
	ContentProviderDAOImpl cpDAO; 

	@Override
	@Transactional
	public int Add(ContentProvider cp) {
		// TODO Auto-generated method stub
		try{
			cpDAO.saveOrUpdate(cp);
		}catch(Exception  e){
			return 0;
		}
		return 1;
	}

	@Override
	@Transactional
	public ContentProvider Get(long id) {
		// TODO Auto-generated method stub
		try{
			return cpDAO.get(id);
		}catch(Exception e){
			return null;
		}
	}

	@Override
	@Transactional
	public ContentProvider Edit(ContentProvider cp) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		try{
			cpDAO.update(cp);
			return cpDAO.get(cp.getCp_id());
		}catch(Exception e){
			return cp;
		}
	}

	@Override
	@Transactional
	public PageResults<ContentProvider> GetByPage(int pageNo, String findStr) {
		System.err.println(pageNo);
		System.err.println(findStr);
		// TODO Auto-generated method stub
		try{
			String hql = "from ContentProvider";
			String countHql = "select COUNT(*) from ContentProvider";
			
			if(findStr != "") {
				hql += " where company like ?";
				countHql += " where company like ?";
				Object[] objs = {"%"+findStr+"%"};
				PageResults<ContentProvider> pr = cpDAO.findPageByFetchedHql(hql, countHql, pageNo, Constant.PAGESIZE, objs);
				return pr;
			}else {
				return cpDAO.findPageByFetchedHql(hql, countHql, pageNo, Constant.PAGESIZE, null);
			}
		}catch(Exception e){
			return null;
		}
	}

	@Override
	@Transactional
	public int Delete(long id) {
		// TODO Auto-generated method stub
		try{
			cpDAO.deleteById(id);
		}catch(Exception e){
			return 0;
		}
		return 1;
	}

	@Override
	@Transactional
	public ContentProvider validContentProvider(String cp_name) {
		// TODO Auto-generated method stub
		try{
			String hql = "from ContentProvider where cp_name = ?";
			Object[] objs = {cp_name};
			return cpDAO.getByHQL(hql, objs);
		}catch(Exception e){
			return null;
		}
	}
}
