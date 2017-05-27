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
public class AndroidCommController {
	
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
	
    ///home/android/comment/add
	//添加相应的评论信息
	@ResponseBody
	@RequestMapping("/addComment/data")
	public String addComment(String videoID,String content,String userID) 
	{
		System.out.println(content+"     **********");
		//commentService
		//查找计算机相关的课程，人气，视频，集数相关的信息
		if(null==userID||""==userID){
			userID="297e386a56410ddd0156411a94c50021";
		}
	    String str = UUID.randomUUID().toString();
	    String uuid=str.substring(0, 8) + str.substring(9, 13) + str.substring(14, 18) + str.substring(19, 23) + str.substring(24);
	    Boolean bool=commentService.saveComment(videoID,content,userID,uuid,true,new Timestamp(new Date().getTime()));
		JSONObject json=new JSONObject();
		json.put("state", 0);
		json.put("desc", "success");
		return json.toJSONString();
	}
	
	//http://localhost:8080/home/android/getComment/data?courseId=40283f815beca962015becaa7c4f0000
	@ResponseBody
	@RequestMapping("/getComment/data")
	public String getCourseComm(String courseID,String userID) 
	{
		JSONObject json=new JSONObject();
		if(null==courseID){
			courseID="40283f815beca962015becaa7c4f0000";
		}
		if(null==userID||""==userID){
			json.put("myScore", 0.0);
		}else{
			Object obj=videoService.getCourseScore(courseID,userID);
			if(null==obj){
				obj=0;
			}
			json.put("myScore", Double.parseDouble(obj.toString()));
		}
//			select sum(cast(sc.score as signed  Integer)) as sum from (select vi.id as id from t_video vi where vi.cour_id='' and vi.state=1) a1 
//			left join t_score sc on sc.video_id=a1.id
		
		List<Object[]> objects=courseService.getCourseAllComment(courseID);
		List list=new ArrayList();
		
		for(Object[] objs:objects){
			Map map=new HashMap();
			map.put("userImg", FileUrl.readUrl("androidUrl")+"/edu/home/android/getAndroidImage?imgname=user.PNG");
			if(null==objs[1]){
				map.put("nickname", "匿名");
			}else{
				map.put("nickname", objs[1]);
			}
			map.put("time", objs[2]);
			map.put("desc", objs[3]);
			list.add(map);
		}
		json.put("data", list);
		json.put("courseID", courseID);
		return json.toJSONString();
	}
	
		

}
