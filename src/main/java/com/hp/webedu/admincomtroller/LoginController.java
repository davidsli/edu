package com.hp.webedu.admincomtroller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.hp.webedu.service.AdminService;
import com.hp.webedu.service.UserService;

@Controller
@RequestMapping("/admin")
public class LoginController {
	
	@Resource
	AdminService adminService;
	
	
	/**
	 * 登陆成功后将用户id添加到Session里面
	 * @param nickName用户名
	 * @param password密码
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/login")
	public String Usualuser(String username,String password)
	{
		JSONObject json=new JSONObject();
		Boolean bool = adminService.findAdmin(username, password);
		if(true==bool)
		{
			json.put("success", true);
		}
		else
		{
			json.put("success", false); 
		}
		return json.toJSONString();
	}
	
	
	/**
	 * 跳转到登录界面
	 * @return
	 */
	@RequestMapping("/loginUI")
	public String get()
	{
		//userService.findUser("user","123456");
		return "/admin/login";
	}
	
}
