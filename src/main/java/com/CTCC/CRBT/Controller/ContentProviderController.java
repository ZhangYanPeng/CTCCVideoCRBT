package com.CTCC.CRBT.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.CTCC.CRBT.DAO.PageResults;
import com.CTCC.CRBT.Entity.ContentProvider;
import com.CTCC.CRBT.Service.IContentProviderService;

@Controller
@RequestMapping("/admin/contentProvider")
public class ContentProviderController {

	@Autowired
	IContentProviderService cpService;

	// 内容提供商列表加载
	@RequestMapping(value = "/contentProvider_list")
	public @ResponseBody PageResults<ContentProvider> userList(String pageNo, String findStr) {
		return cpService.GetByPage(Integer.valueOf(pageNo), findStr);
	}

	@RequestMapping(value = "/contentProvider_add")
	public @ResponseBody int cp_add(String cp_name, String cp_pwd, String company) {
		ContentProvider cp = new ContentProvider();
		cp.setCp_name(cp_name);
		cp.setCp_pwd(cp_pwd);
		cp.setCompany(company);
		if (ValidContentProviderInfo(cp) == 0)
			return -1;
		return cpService.Add(cp);
	}

	@RequestMapping(value = "/contentProvider_edit")
	public @ResponseBody ContentProvider user_edit(String cp_id, String cp_name, String cp_pwd, String company) {
		ContentProvider cp = new ContentProvider();
		cp = cpService.Get(Long.valueOf(cp_id));
		cp.setCp_name(cp_name);
		cp.setCp_pwd(cp_pwd);
		cp.setCompany(company);
		if (ValidContentProviderInfo(cp) == 0) {
			ContentProvider cpn = new ContentProvider();
			cpn.setCp_id(-1);
			return cpn;
		}
		return cpService.Edit(cp);
	}

	private int ValidContentProviderInfo(ContentProvider cp) {
		// TODO Auto-generated method stub
		if (cpService.validContentProvider(cp.getCp_name()) != null)
			return 0;
		return 1;
	}

	// 内容提供商列表加载
	@RequestMapping(value = "/contentProvider_load")
	public @ResponseBody ContentProvider contentProvider_load(String id) {
		return cpService.Get(Long.valueOf(id));
	}

	// 内容提供商删除
	@RequestMapping(value = "/delete_contentProvider")
	public @ResponseBody int delete_contentProvider(String id) {
		return cpService.Delete(Long.valueOf(id));
	}
}
