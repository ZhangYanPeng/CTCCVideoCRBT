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
		admin.setUsername(username);
		admin.setPassword(password);
		admin = adminService.Login(admin);
		return admin;
	}

	// 管理员列表加载
	@RequestMapping(value = "/admin_list")
	public @ResponseBody PageResults<Admin> adminList(String pageNo) {
		return adminService.GetByPage(Integer.valueOf(pageNo));
	}

	// 管理员列表加载
	@RequestMapping(value = "/delete_admin")
	public @ResponseBody int delete_admin(String id) {
		return adminService.Delete(Long.valueOf(id));
	}
}
