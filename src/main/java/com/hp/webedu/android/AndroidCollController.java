package com.hp.webedu.android;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.hp.webedu.service.CollectionService;
import com.hp.webedu.service.CommentService;
import com.hp.webedu.service.CourseService;
import com.hp.webedu.service.ScoreService;
import com.hp.webedu.service.VideoService;
import com.hp.webedu.util.FileUrl;

@Controller
@RequestMapping("/home/android")
public class AndroidCollController {
	
	@Resource
	private CourseService courseService;
	@Resource
	private VideoService videoService;
	@Resource
	private CommentService commentService;
	@Resource
	private CollectionService collectionService;
	@Resource
	private ScoreService scoreService;
	
	
	//获取收藏列表
	///home/android/getCollection/data
	@ResponseBody
	@RequestMapping("/getCollection/data")
	public String getgetCollection(String userID)
	{
		
		if(null==userID||""==userID){
			userID="297e386a56410ddd0156411a94c50021";
		}
		List<Object[]> list1=collectionService.getCollCourse(userID.toString(),0,100);
		
		JSONObject json=new JSONObject();
		List list=new ArrayList();
		for(Object[] objs:list1){
			Map mp=new HashMap();
			mp.put("courseID", objs[0]);//课程id
			mp.put("collectTime", objs[1]);//收藏时间
			mp.put("courseImg", FileUrl.readUrl("androidUrl")+"/edu/course/image?imgname="+objs[2].toString());//课程图片
			mp.put("score", Math.random()%5.0);//课程分数
			mp.put("courseName", objs[3]);//课程名
			list.add(mp);
		}
		json.put("data", list);
		return json.toJSONString();
	}
	
	//收藏此门课程
	///home/android/collectCourse/data
	@ResponseBody
	@RequestMapping("/collectCourse/data")
	public String collectCourse(String userID,String courseID)
	{
		if(null==userID||""==userID){
			userID="297e386a56410ddd0156411a94c50021";
		}
		JSONObject json=new JSONObject();
		List<Boolean> list=collectionService.getCollectionByCourseIdUserId(userID,courseID);
		//取消收藏
		if(list.size()>0){
			Boolean bool = list.get(0);
			Integer state=0;
			json.put("state", 1);
			if(false==bool){
				state=1;
				json.put("state", 0);
			}
			int i=collectionService.updateCollectionInfo(userID,courseID,state);
		}else{//第一次收藏
			String str = UUID.randomUUID().toString();
		    String uuid=str.substring(0, 8) + str.substring(9, 13) + str.substring(14, 18) + str.substring(19, 23) + str.substring(24);
		    Timestamp timeStamps=new Timestamp(new Date().getTime());
			int i=collectionService.insertCollInfo(uuid,courseID,userID,1,timeStamps);
			json.put("state", 0);
		}
		json.put("desc", "success");
		return json.toJSONString();
	}
	
	
		

}
