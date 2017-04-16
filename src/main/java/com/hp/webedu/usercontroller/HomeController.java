package com.hp.webedu.usercontroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class HomeController {
	
	@RequestMapping("/index")
	public String getDetailIndex()
	{
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
