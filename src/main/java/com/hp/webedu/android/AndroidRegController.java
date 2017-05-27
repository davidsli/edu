package com.hp.webedu.android;

import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.hp.webedu.entity.User;
import com.hp.webedu.service.CourseService;
import com.hp.webedu.service.UserService;

//{
//	"state": 0,
//    "desc": "success"
//}

@Controller
@RequestMapping("/home/android")
public class AndroidRegController {
	
	@Resource
	private UserService userService;
	
	/**
	 * 
	 * @param phone
	 * @param pwd
	 * @param type 0注册1修改密码2忘记密码
	 * @return
	 */
	//注册
	@ResponseBody
	@RequestMapping("/reg/data")
	public String reg(String phone,String pwd,Integer type)
	{
		JSONObject json=new JSONObject();
		json.put("desc", "success");
		if(0==type){//注册
			List<Object[]> list = userService.getUserByPhone(phone);
			if(list.size()>0){
				json.put("state", 1);
			}else{
				String str = UUID.randomUUID().toString();
			    String uuid=str.substring(0, 8) + str.substring(9, 13) + str.substring(14, 18) + str.substring(19, 23) + str.substring(24);
				int i=userService.insetAndroidUser(uuid,phone,pwd);
				json.put("state", 0);
			}
			return json.toJSONString();	
		}
		if(1==type||2==type){//修改密码
			List<Object[]> list = userService.getUserByPhone(phone);
			if(list.size()>0){
				int i=userService.updateUserByPhone(phone,pwd);
				json.put("state", 0);
			}else{
				json.put("state", 1);
			}
		}
	    return json.toJSONString();	
	}

}
