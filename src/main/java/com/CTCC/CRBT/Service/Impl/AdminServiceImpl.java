package com.CTCC.CRBT.Service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.CTCC.CRBT.Common.Constant;
import com.CTCC.CRBT.DAO.PageResults;
import com.CTCC.CRBT.DAO.Impl.AdminDAOImpl;
import com.CTCC.CRBT.Entity.Admin;
import com.CTCC.CRBT.Service.IAdminService;

@Service
public class AdminServiceImpl implements IAdminService {
	@Autowired
	AdminDAOImpl adminDAO;

	@Override
	@Transactional
	public int Add(Admin admin) {
		// TODO Auto-generated method stub
		try{
			adminDAO.saveOrUpdate(admin);
		}catch(Exception e){
			return 0;
		}
		return 1;
	}

	@Override
	@Transactional
	public Admin Get(long id) {
		// TODO Auto-generated method stub
		try{
			return adminDAO.get(id);
		}catch(Exception e){
			return null;
		}
	}

	@Override
	@Transactional
	public Admin Edit(Admin admin) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		try{
			adminDAO.update(admin);
			return adminDAO.get(admin.getAdmin_id());
		}catch(Exception e){
			return admin;
		}
	}

	@Override
	@Transactional
	public Admin Login(Admin admin) {
		// TODO Auto-generated method stub
		try{
			String hql = "from Admin where admin_name = ? and admin_pwd = ?";
			Object[] values = {admin.getAdmin_name(), admin.getAdmin_pwd()};
			return adminDAO.getByHQL(hql, values);
		}catch(Exception e){
			admin.setAdmin_id(-1);
			return admin;
		}
	}

	@Override
	@Transactional
	public PageResults<Admin> GetByPage(int pageNo) {
		// TODO Auto-generated method stub
		try{
			String hql = "from Admin";
			String countHql = "select COUNT(*) from Admin";
			return adminDAO.findPageByFetchedHql(hql, countHql, pageNo, Constant.PAGESIZE, null);
		}catch(Exception e){
			return null;
		}
	}

	@Override
	@Transactional
	public int Delete(long id) {
		// TODO Auto-generated method stub
		try{
			adminDAO.deleteById(id);
		}catch(Exception e){
			return 0;
		}
		return 1;
	}

}
