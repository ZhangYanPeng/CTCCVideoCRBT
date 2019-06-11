package com.CTCC.CRBT;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.CTCC.CRBT.Entity.Admin;
import com.CTCC.CRBT.Service.IAdminService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	@Autowired
	IAdminService adminService;

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	

	@RequestMapping(value = "/topage")
	public String topage(HttpServletRequest request, HttpServletResponse response, String url) {
		response.setContentType("text/html;charset=utf-8");
		return "/" + url;
	}

	// 管理员登陆
	@RequestMapping(value = "/admin/login")
	public @ResponseBody Admin adminLogin(String username, String password) {
		Admin admin = new Admin();
		admin.setAdmin_name(username);
		admin.setAdmin_pwd(password);
		admin = adminService.Login(admin);
		return admin;
	}

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/index")
	public String home(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response) {
		logger.info("Welcome home! The client locale is {}.", locale);
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		String formattedDate = dateFormat.format(date);
		model.addAttribute("serverTime", formattedDate);response.setContentType("text/html;charset=utf-8");
		return "/log";
	}

}
