package com.hp.webedu.admincomtroller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONObject;
import com.hp.webedu.service.AdminService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Resource
	AdminService adminService;
	
	@RequestMapping("/frame")
	public String frame(String username,String password)
	{
		return "admin/frame";
	}
	
	
	@RequestMapping("/loginUI1")
	public String get()
	{
		//userService.findUser("user","123456");
		return "admin/login";
	}

}
