package com.hp.webedu.usercontroller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.hp.webedu.entity.User;
import com.hp.webedu.service.UserService;
import com.hp.webedu.util.FileUrl;
import com.hp.webedu.util.SendMail;

/**
 * 实现账号注册和激活的类
 * @author pan.he
 *
 */
@Controller
@RequestMapping("/home")
public class RegController {
	
	
	@Resource
	private UserService userService;
	
	@ResponseBody
	@RequestMapping("/setpsw")
	public String setpsw(String userId,String password)
	{
		JSONObject json=new JSONObject();
		int i=userService.updateUserPassword(userId, password);
		if(i>0){
			json.put("success", true);
		}else{
			json.put("success", false);
		}
		return json.toJSONString();
	}
	
	//跳转到更高密码的界面
	@RequestMapping("/updatePsw")
	public String updatePsw(String id ,Model map)
	{
		map.addAttribute("id", id);
		return "/home/login/updatepsw";
	}
	
	/**
	 * 忘记密码，向邮件发送更改密码邮件
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/forgetpsw")
	public String forgetpsw(String email)
	{
		List<Object[]> list=userService.getUserByEmail(email);
		JSONObject json=new JSONObject();
		//邮箱存在
		if(list.size()==1){
			Object[] objects = list.get(0);
			String id = objects[0].toString();
			StringBuffer sb=new StringBuffer("点击下面链接激活账号，48小时生效，否则重新注册账号，链接只能使用一次，请尽快激活！</br>");
			String site=FileUrl.readUrl("href");
		    String str="<a href='"+site+":8080/edu/home/updatePsw?id=";
		    sb.append(str);
		    sb.append(id);
		    sb.append("'>激活"); 
	        sb.append("</a>");
			String bool=SendMail.send(sb.toString(),"网站密码重置邮件", email);
			json.put("success", true); 
		}else{//邮箱不存在
			json.put("success", false); 
		}
		return json.toJSONString();
	}
	
	/**
	 * 跳转到忘记密码界面
	 * @return
	 */
	@RequestMapping("/forgetpswUI")
	public String forgetpswUI()
	{
		return "/home/login/forgetpsw";
	}
	 //账户激活
	@RequestMapping("/active")
	public String active(String id)
	{
		int i=userService.updateUserById(id);
		if(i>0){
			return "/home/login/activesc";
		}
		return "/home/login/activesc";
	}
		
	 //注册
	@ResponseBody
	@RequestMapping("/register")
	public String Usualuser(String email,String password,HttpServletRequest request)
	{
		JSONObject json=new JSONObject();
		json.put("success", true); 
		//检查邮箱是否被注册过
		List<Object[]> list=userService.checkIsReg(email);
		if(list.size()>0){
			json.put("isEmailExist", true);
			return json.toJSONString();
		}else{
			json.put("isEmailExist", false);
		}
		//保存用户
		User user =new User(email,password);
		User user2 =userService.saveUser(user);
		StringBuffer sb=new StringBuffer("点击下面链接激活账号，48小时生效，否则重新注册账号，链接只能使用一次，请尽快激活！</br>");
		String site=FileUrl.readUrl("href");
	    String str="<a href='"+site+":8080/edu/home/active?id=";
	    sb.append(str);
	    sb.append(user2.getId());
	    sb.append("'>激活"); 
        sb.append("</a>");
		String bool=SendMail.send(sb.toString(),"网站激活邮件", email);
		if("seccess"==bool){
			json.put("emailSuccecc", true);
		}else{
			json.put("emailSuccecc", false);
		}
		return json.toJSONString();
	}
	
	
	

}
