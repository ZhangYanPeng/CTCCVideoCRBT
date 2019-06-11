package com.CTCC.CRBT.Service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.CTCC.CRBT.Common.Constant;
import com.CTCC.CRBT.DAO.PageResults;
import com.CTCC.CRBT.DAO.Impl.AccountDAOImpl;
import com.CTCC.CRBT.Entity.Account;
import com.CTCC.CRBT.Service.IAccountService;

@Service
public class AccountServiceImpl implements IAccountService {
	@Autowired
	AccountDAOImpl userDAO;

	@Override
	@Transactional
	public int Add(Account user) {
		// TODO Auto-generated method stub
		try{
			userDAO.saveOrUpdate(user);
		}catch(Exception  e){
			return 0;
		}
		return 1;
	}

	@Override
	@Transactional
	public Account Get(long id) {
		// TODO Auto-generated method stub
		try{
			return userDAO.get(id);
		}catch(Exception e){
			return null;
		}
	}

	@Override
	@Transactional
	public Account Edit(Account user) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		try{
			userDAO.update(user);
			return userDAO.get(user.getUsr_id());
		}catch(Exception e){
			return user;
		}
	}

	@Override
	@Transactional
	public PageResults<Account> GetByPage(int pageNo, String findStr) {
		// TODO Auto-generated method stub
		try{
			String hql = "from Account";
			String countHql = "select COUNT(*) from Account";
			
			if(findStr != "") {
				hql += " where ( usr_tel like ?";
				countHql += " where ( usr_tel like ?";
				Object[] objs = {"%"+findStr+"%"};
				PageResults<Account> pr = userDAO.findPageByFetchedHql(hql, countHql, pageNo, Constant.PAGESIZE, objs);
				return pr;
			}else {
				return userDAO.findPageByFetchedHql(hql, countHql, pageNo, Constant.PAGESIZE, null);
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
			userDAO.deleteById(id);
		}catch(Exception e){
			return 0;
		}
		return 1;
	}

	@Override
	@Transactional
	public Account validAccountTel(String usr_tel) {
		// TODO Auto-generated method stub
		try{
			String hql = "from Admin where usr_tel = ?";
			Object[] objs = {Long.valueOf(usr_tel)};
			return userDAO.getByHQL(hql, objs);
		}catch(Exception e){
			return null;
		}
	}

}
