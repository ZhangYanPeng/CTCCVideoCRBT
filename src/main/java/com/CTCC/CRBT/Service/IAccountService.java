package com.CTCC.CRBT.Service;

import com.CTCC.CRBT.DAO.PageResults;
import com.CTCC.CRBT.Entity.Account;

public interface IAccountService {
	public int Add(Account user);
	public Account Get(long id);
	public Account Edit(Account user);
	public int Delete(long id);
	public PageResults<Account> GetByPage(int pageNo, String findStr);
	public Account validAccountTel(String usr_tel);
}
