package com.CTCC.CRBT.Controller;

import java.sql.SQLIntegrityConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.CTCC.CRBT.DAO.PageResults;
import com.CTCC.CRBT.Entity.Admin;
import com.CTCC.CRBT.Service.IAdminService;

@Controller
@RequestMapping("/admin/admin")
public class AdminController {
	@Autowired
	IAdminService adminService;

	// 管理员列表加载
	@RequestMapping(value = "/admin_list")
	public @ResponseBody PageResults<Admin> adminList(String pageNo, String findStr) {
		return adminService.GetByPage(Integer.valueOf(pageNo), findStr);
	}

	@RequestMapping(value = "/admin_add")
	public @ResponseBody int admin_add(String admin_name, String admin_desc, String valid_state, String admin_pwd) {
		Admin admin = new Admin();
		admin.setAdmin_name(admin_name);
		admin.setAdmin_desc(admin_desc);
		admin.setValid_state(Integer.valueOf(valid_state));
		admin.setAdmin_pwd(admin_pwd);
		if( ValidAdminInfo(admin) == 0)
			return -1;
		return adminService.Add(admin);
	}
	
	@RequestMapping(value = "/admin_edit")
	public @ResponseBody Admin admin_edit(String admin_id, String admin_name, String admin_desc, String valid_state, String admin_pwd) {
		Admin admin = new Admin();
		admin = adminService.Get(Long.valueOf(admin_id));
		admin.setAdmin_name(admin_name);
		admin.setAdmin_desc(admin_desc);
		admin.setValid_state(Integer.valueOf(valid_state));
		admin.setAdmin_pwd(admin_pwd);
		if( ValidAdminInfo(admin) == 0) {
			Admin adn = new Admin();
			adn.setAdmin_id(-1);
			return adn;
		}
		return adminService.Edit(admin);
	}

	private int ValidAdminInfo(Admin admin) {
		// TODO Auto-generated method stub
		if( adminService.validAdminName(admin.getAdmin_name()) != null)
			return 0;
		return 1;
	}

	// 管理员列表加载
	@RequestMapping(value = "/admin_load")
	public @ResponseBody Admin admin_load(String id) {
		return adminService.Get(Long.valueOf(id));
	}

	// 管理员删除
	@RequestMapping(value = "/delete_admin")
	public @ResponseBody int delete_admin(String id) {
		return adminService.Delete(Long.valueOf(id));
	}

	// 管理员状态信息变更加载
	@RequestMapping(value = "/valid_admin")
	public @ResponseBody int valid_admin(String id, String state) {
		Admin admin = adminService.Get(Long.valueOf(id));
		admin.setValid_state(Integer.valueOf(state));
		try {
			adminService.Edit(admin);
			return 1;
		} catch (Exception exp) {
			return 0;
		}
	}
	
	
	
	
	
}
