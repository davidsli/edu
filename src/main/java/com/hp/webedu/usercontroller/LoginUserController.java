package com.hp.webedu.usercontroller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.hp.webedu.service.UserService;
import com.hp.webedu.test.Main;

@Controller
@RequestMapping("/home")
public class LoginUserController {
	 public static Logger logger = LoggerFactory.getLogger(LoginUserController.class);
	
	@Resource
	private UserService userService;
	
	//退出
	@RequestMapping("/loginOut")
	public String loginOut(String nickName,String password,HttpServletRequest request)
	{
		request.getSession().removeAttribute("userId");
		return "/home/login/loginOrReg";
	}
	//登陆1成功0失败2账号不存在
	@ResponseBody
	@RequestMapping("/login")
	public String Usualuser(String nickName,String password,HttpServletRequest request)
	{
		JSONObject json=new JSONObject();
		List<Object[]> list=userService.finUserName(nickName);
		//账号存在
		if(list.size()>0){
			List<Object[]> list1 = userService.findUser(nickName, password);
			if(list1.size()>0)
			{
				String userId=(String) list1.get(0)[0];
				json.put("success", 1);
				request.getSession().setAttribute("userId", userId);
				request.getSession().setMaxInactiveInterval(24*60*60);
			}
			else
			{
				json.put("success", 0); 
			}
		}// 账号不存在
		else{
			json.put("success", 2); 
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
		logger.debug("账号登陆测试debug");
		logger.info("账号登陆测试info");
		logger.error("账号登陆测试error");
		return "/home/login/loginOrReg";
	}

}
