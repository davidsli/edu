package com.hp.webedu.admincomtroller;

import java.util.List;

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
	
	
	//点击登陆执行的代码
	@ResponseBody
	@RequestMapping("/login")
	public String Usualuser(String username,String password)
	{
		JSONObject json=new JSONObject();
		List<Object[]> list = adminService.findAdmin(username, password);
		if(list.size()>0){
			json.put("success", true);
		}else{
			json.put("success", false); 
		}
		return json.toJSONString();
	}
	
	//跳转到登陆界面
	@RequestMapping("/loginUI")
	public String loginUI()
	{
		return "/admin/login";
	}
	
}
