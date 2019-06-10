package com.CTCC.CRBT.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.CTCC.CRBT.DAO.PageResults;
import com.CTCC.CRBT.Entity.Admin;
import com.CTCC.CRBT.Service.IAdminService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	IAdminService adminService;

	// 管理员登陆
	@RequestMapping(value = "/login")
	public @ResponseBody Admin adminLogin(String username, String password) {
		Admin admin = new Admin();
		admin.setAdmin_name(username);
		admin.setAdmin_pwd(password);
		admin = adminService.Login(admin);
		return admin;
	}

	// 管理员列表加载
	@RequestMapping(value = "/admin_list")
	public @ResponseBody PageResults<Admin> adminList(String pageNo, String findStr) {
		return adminService.GetByPage(Integer.valueOf(pageNo), findStr);
	}

	// 管理员列表加载
	@RequestMapping(value = "/delete_admin")
	public @ResponseBody int delete_admin(String id) {
		return adminService.Delete(Long.valueOf(id));
	}
	
	// 管理员状态信息变更加载
	@RequestMapping(value = "/valid_admin")
	public @ResponseBody int valid_admin(String id, String state) {
		Admin admin = adminService.Get(Long.valueOf(id));
		admin.setValid_state(Integer.valueOf(state));
		try{
			adminService.Edit(admin);
			return 1;
		}catch(Exception exp){
			return 0;
		}
	}
	
	
	
	
	
}
