package com.hp.webedu.usercontroller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.hp.webedu.service.UserService;

@Controller
@RequestMapping("/home")
public class LoginUserController {
	
	@Resource
	private UserService userService;
	
	
	@ResponseBody
	@RequestMapping("/login")
	public String Usualuser(String nickName,String password,HttpServletRequest request)
	{
		JSONObject json=new JSONObject();
		String userId = userService.findUser(nickName, password);
		if(null!=userId)
		{
			json.put("success", true);
			request.getSession().setAttribute("userId", userId);
		}
		else
		{
			json.put("success", false); 
		}
		return json.toJSONString();
	}
	
	
	/**
	 * 普通用户首先登陆进来的页面
	 * @return
	 */
	@RequestMapping("/loginUI")
	public String login()
	{
		return "/home/login/login";
	}

}
