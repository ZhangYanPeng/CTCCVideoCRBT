package com.CTCC.CRBT.Service;

import com.CTCC.CRBT.DAO.PageResults;
import com.CTCC.CRBT.Entity.Admin;

public interface IAdminService {
	public int Add(Admin admin);
	public Admin Get(long id);
	public Admin Edit(Admin admin);
	public Admin Login(Admin admin);
	public PageResults<Admin> GetByPage(int pageNo);
}
