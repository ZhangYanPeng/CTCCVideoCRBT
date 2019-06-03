package com.CTCC.CRBT.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.CTCC.CRBT.Entity.Admin;
import com.CTCC.CRBT.Service.IAdminService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	IAdminService adminService;
	
	@RequestMapping(value = "/login")
	public @ResponseBody Admin adminLogin(String username, String password) {
		Admin admin = new Admin();
		admin.setUsername(username);
		admin.setPassword(password);
		admin = adminService.Login(admin);
		return admin;
	}
}
