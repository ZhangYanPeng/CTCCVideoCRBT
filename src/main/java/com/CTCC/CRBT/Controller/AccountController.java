package com.CTCC.CRBT.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.CTCC.CRBT.DAO.PageResults;
import com.CTCC.CRBT.Entity.Account;
import com.CTCC.CRBT.Entity.Admin;
import com.CTCC.CRBT.Service.IAccountService;

@Controller
@RequestMapping("/admin/account")
public class AccountController {
	@Autowired
	IAccountService userService;

	// 管理员列表加载
	@RequestMapping(value = "/user_list")
	public @ResponseBody PageResults<Account> userList(String pageNo, String findStr) {
		return userService.GetByPage(Integer.valueOf(pageNo), findStr);
	}

	@RequestMapping(value = "/user_add")
	public @ResponseBody int user_add(String usr_tel) {
		Account user = new Account();
		user.setUsr_tel(usr_tel);
		if (ValidAccountInfo(user) == 0)
			return -1;
		return userService.Add(user);
	}

	@RequestMapping(value = "/user_edit")
	public @ResponseBody Account user_edit(String usr_id, String usr_tel) {
		Account user = new Account();
		user = userService.Get(Long.valueOf(usr_id));
		user.setUsr_tel(usr_tel);
		if (ValidAccountInfo(user) == 0) {
			Account usr = new Account();
			usr.setUsr_id(-1);
			return usr;
		}
		return userService.Edit(user);
	}

	private int ValidAccountInfo(Account user) {
		// TODO Auto-generated method stub
		if (userService.validAccountTel(user.getUsr_tel()) != null)
			return 0;
		return 1;
	}

	// 管理员列表加载
	@RequestMapping(value = "/user_load")
	public @ResponseBody Account user_load(String id) {
		return userService.Get(Long.valueOf(id));
	}

	// 管理员删除
	@RequestMapping(value = "/delete_user")
	public @ResponseBody int delete_user(String id) {
		return userService.Delete(Long.valueOf(id));
	}
}
