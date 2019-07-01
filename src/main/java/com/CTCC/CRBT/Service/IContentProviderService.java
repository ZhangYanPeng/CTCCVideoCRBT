package com.CTCC.CRBT.Service;

import com.CTCC.CRBT.DAO.PageResults;

import com.CTCC.CRBT.Entity.ContentProvider;

public interface IContentProviderService {
	public int Add(ContentProvider cp);
	public ContentProvider Get(long id);
	public ContentProvider Edit(ContentProvider cp);
	public int Delete(long id);
	public PageResults<ContentProvider> GetByPage(int pageNo, String findStr);
	public ContentProvider validContentProvider(String cp_name);
}
