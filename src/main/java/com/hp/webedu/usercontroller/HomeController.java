package com.hp.webedu.usercontroller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class HomeController {
	
	//跳转到课程列表界面
	@RequestMapping("/index")
	public String getDetailIndex(HttpServletRequest request)
	{
		String userID=(String) request.getSession().getAttribute("userId");
		if(null==userID||userID==""){
			return "/home/loginUI";
		}
		return "/home/index";
	}
	
//	@RequestMapping("/index")
//	public String getIndex()
//	{
//		return "/home/index";
//	}
	
	@RequestMapping("/top")
	public String getTop()
	{
		//userService.findUser("user","123456");
		return "/home/top";
	}
	

}
