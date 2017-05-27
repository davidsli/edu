package com.hp.webedu.android;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.hp.webedu.service.CourseService;
import com.hp.webedu.service.UserService;
import com.hp.webedu.util.FileUrl;

//{
//	"state": 0,
//    "desc": "success"
//}

@Controller
@RequestMapping("/home/android")
public class AndroidLoginController {
	
	@Resource
	private UserService userService;
	
	@ResponseBody
	@RequestMapping("/getnickNameImg/data")
	public String getNickNameAndImg(String userID)
	{
		JSONObject json=new JSONObject();
		List<Object[]> list=userService.getnickNameAndImg(userID);
		if(list.size()>0){
			Object[] objects = list.get(0);
			if(null==objects[0]||0==objects[0].toString().length()){
				json.put("nickname", objects[0]);
			}else{
				json.put("nickname", "没有设置昵称");
			}
			json.put("avatar", FileUrl.readUrl("androidUrl")+"/edu/home/android/getAndroidImage?imgname="+"user.PNG");
		}
		return json.toJSONString();
	}
	///home/android/login/data
//	String phone,String pwd
	//安卓登陆
	@ResponseBody
	@RequestMapping("/login/data")
	public String login(String phone,String pwd)
	{
		JSONObject json=new JSONObject();
		json.put("desc", "success");
		List<Object[]> list=userService.getUserByPhone(phone);
		if(list.size()>0){
			List<Object[]> list1=userService.findUserByPhoneAndPassword(phone, pwd);
			if(list1.size()>0){
				json.put("state", 0); //登陆成功
				Object[] objects = list1.get(0);
				json.put("desc", objects[0].toString());//userID
			}else{
				json.put("state",2);//密码不正确
			}
			 return json.toJSONString();	
		}else{
			json.put("state",2);//用户名不存在
		}
	    return json.toJSONString();	
	}

}
