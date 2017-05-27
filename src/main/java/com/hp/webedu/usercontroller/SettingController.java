package com.hp.webedu.usercontroller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.hp.webedu.entity.Course;
import com.hp.webedu.entity.User;
import com.hp.webedu.service.CollectionService;
import com.hp.webedu.service.CourseService;
import com.hp.webedu.service.UserService;
import com.hp.webedu.util.Page;
import com.hp.webedu.util.WebUtil;

//设置个人信息界面
@Controller
@RequestMapping("/home")
public class SettingController {
	
	@Resource
	private UserService userService;
	
	@Resource
	private CollectionService collectionService;
	
	@Resource
	private CourseService courseService;
	
	//更新用户的密码
	@ResponseBody
	@RequestMapping("/updatePassword/data")
	public String getUpdatePasswordManage(HttpServletRequest request,String newpsw)
	{
		JSONObject json=new JSONObject();
		Object userId = request.getSession().getAttribute("userId");
		int i=userService.updateUserPassword(userId.toString(),newpsw);
		json.put("success", true);
		return json.toJSONString();
	}
	
	//点击账号绑定发生的事件
	@ResponseBody
	@RequestMapping("/account/data")
	public String getAccountManage(HttpServletRequest request,Integer pageNo)
	{
		Object userId = request.getSession().getAttribute("userId");
		User user = userService.getUserById(userId.toString());
		JSONObject json=new JSONObject();
		json.put("userId", user.getId());
		json.put("email", user.getEmail());
		json.put("phoneNumber", user.getPhoneNumber());
		json.put("userPassword", user.getUserPassword());
		return json.toJSONString();
	}
	//课程管理管理返回的数据
	@ResponseBody
	@RequestMapping("/courseManage/data")
	public String getCourseManage(HttpServletRequest request,Integer pageNo)
	{
		Integer start,len=Page.collSize;
		if(null==pageNo){
			start=0;
		}else{
			start=(pageNo-1)*Page.collSize;
		}
		Object userId = request.getSession().getAttribute("userId");
		List<Object[]> list1=collectionService.getCollCourse(userId.toString(),start,len);
		Object[] obj=collectionService.getCollCourseCount(userId.toString());
		Integer count=Integer.parseInt(obj[0].toString());
		
		JSONObject json=new JSONObject();
		List list=new ArrayList();
		for(Object[] objs:list1){
			Map mp=new HashMap();
			mp.put("courId", objs[0]);//课程id
			mp.put("collTime", objs[1]);//收藏时间
			mp.put("imgUrl", "/edu/course/image?imgname="+objs[2].toString());//课程图片
			mp.put("courName", objs[3]);//课程名
			mp.put("teacherName", objs[4]);//教师名
			mp.put("school", objs[5]);//学校
			mp.put("courIntro", objs[6]);//课程介绍
			list.add(mp);
		}
//		select a1.cour_id as id,a1.coll_time as coll_time,cou.img_url as img_url,cou.cour_name as cour_name,cou.teacher_name as teacher_name,
//		cou.school as school,cou.cour_intro as cour_intro
//		from(
//		 select col.cour_id as cour_id,col.coll_time as coll_time
//		 from t_collection col where col.user_id='297e386a56410ddd0156411a94c50020' and col.state=1
//		 ) a1 left join t_course cou on a1.cour_id=cou.id where cou.state=1
		Integer pageCount=count%Page.collSize==0?count/Page.collSize:count/Page.collSize+1;
		json.put("pageCount", pageCount);
		json.put("collList", list);
		json.put("collListSize", list.size());
		json.put("success", true);
		
		String cookies = WebUtil.getCookieByName(request, "courseViewhistory");
		String[] split = cookies.split("-");
		List list2=new ArrayList();
		
		for(int i=start;i<split.length;i++ ){
			Course courseById = courseService.getCourseById(split[i]);
			if(null==courseById||i>=(start+Page.collSize)){
				break;
			}
			Map mp=new HashMap();
			mp.put("courId",courseById.getId());
			mp.put("courName", courseById.getCourName());
			mp.put("imgUrl", "/edu/course/image?imgname="+courseById.getImgUrl());
			mp.put("teacherName", courseById.getTeacherName());
			mp.put("courIntro", courseById.getCourIntro());
			mp.put("school", courseById.getSchool());
			list2.add(mp);
		}
		json.put("hisList", list2);
		Integer strlen=split.length;
		Integer hisPageCount=strlen%Page.collSize==0?strlen/Page.collSize:strlen/Page.collSize+1;
		json.put("hisPageCount", hisPageCount);
		return json.toJSONString();
	}
	//个人信息的数据
	@ResponseBody
	@RequestMapping("/personalInfo/data")
	public String getPersonalInfo(HttpServletRequest request)
	{
		Object userId = request.getSession().getAttribute("userId");
		User user=userService.getUserById(userId.toString());
		JSONObject json=new JSONObject();
		json.put("id", user.getId());
		json.put("email", user.getEmail());
		json.put("nickName", user.getNickName());
		if(user.getSex()==true){
			json.put("sex", "男");
		}else{
			json.put("sex", "女");
		}
		json.put("phoneNumber", user.getPhoneNumber());
		json.put("personalIntro", user.getPersonalIntro());
		json.put("success", true);
		return json.toJSONString();
	}
	
	//跳转到设置界面
	@RequestMapping("/setting")
	public String getSetting(HttpServletRequest request)
	{
		String userID=(String) request.getSession().getAttribute("userId");
		if(null==userID||userID==""){
			return "/home/loginUI";
		}
		return "/home/setting/user";
	}

}
