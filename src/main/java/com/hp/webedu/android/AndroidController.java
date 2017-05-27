package com.hp.webedu.android;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.hp.webedu.entity.Course;
import com.hp.webedu.service.CollectionService;
import com.hp.webedu.service.CommentService;
import com.hp.webedu.service.CourseService;
import com.hp.webedu.service.ScoreService;
import com.hp.webedu.service.VideoService;
import com.hp.webedu.util.FileUrl;
import com.hp.webedu.util.Page;

@Controller
@RequestMapping("/home/android")
public class AndroidController {
	
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
	//为视频打分
	@ResponseBody
	@RequestMapping("/score/add")
	public String addScore(String videoID,String score,String userID) 
	{
		//commentService
		//查找计算机相关的课程，人气，视频，集数相关的信息
		if(null==userID||""==userID){
			userID="297e386a56410ddd0156411a94c50021";
		}
		List<Object[]> userScore = scoreService.getUserScore(userID, videoID);
		if(userScore.size()>0){
			int i=scoreService.updateScoreByUserIdAndVideoId(userID, videoID,score);
		}
		else{
			String str = UUID.randomUUID().toString();
		    String uuid=str.substring(0, 8) + str.substring(9, 13) + str.substring(14, 18) + str.substring(19, 23) + str.substring(24);
		    int i=scoreService.insertScore(uuid,videoID,score,userID);
		}
		JSONObject json=new JSONObject();
		json.put("state", 0);
		json.put("desc", "success");
		return json.toJSONString();
	}
	
	///home/android/popularCourse/data
	@ResponseBody
	@RequestMapping("/popularCourse/data")
	public String getPopularCourse() 
	{
		JSONObject json=new JSONObject();
		List<Object[]> list1=courseService.getPopularCourseLimi10();
		List list=new ArrayList();
		for(Object[] obs:list1){
			Map mp=new HashMap();
			mp.put("courseID", obs[0].toString());
			mp.put("courseImg", FileUrl.readUrl("androidUrl")+"/edu/course/image?imgname="+obs[1].toString());
			mp.put("courseName", obs[2].toString());
			list.add(mp);
		}
		json.put("data", list);
		return json.toJSONString();
	}
	
	//http://localhost:8080/home/android/courseList/data?courseId=
	@ResponseBody
	@RequestMapping("/courseList/data")
	public String getCourseList(String courseID) 
	{
		System.out.println(courseID+"1111111111");
		if(null==courseID){
			courseID="40283f815af07e8f015af0802bab0001";
		}
		JSONObject json=new JSONObject();
		json.put("courseID", courseID);
		Course course = courseService.getCourseById(courseID);
		json.put("courseName", course.getCourName());
		json.put("thumbnail", FileUrl.readUrl("androidUrl")+"/edu/course/image?imgname="+course.getImgUrl());
		List<Object[]> list1=videoService.getVideoListByDesc(courseID);
		List list=new ArrayList();
		//0id
		//1video_name
		//2video_desc
		//3thumb
		//4chapter
		
		for(Object[] obs:list1)
		{
			Map mp=new HashMap();
			mp.put("videoID", obs[0].toString());
			mp.put("name", obs[2].toString());
			mp.put("thumb", obs[3].toString());
			mp.put("seq", Integer.parseInt(obs[4].toString()));
			mp.put("duration", obs[5].toString());
			mp.put("size", Integer.parseInt(obs[6].toString()));
			mp.put("url_h", "rtmpt://"+FileUrl.readUrl("videoUrl")+"/oflaDemo/"+obs[1].toString());
			mp.put("url_m", "rtmpt://"+FileUrl.readUrl("videoUrl")+"/oflaDemo/"+obs[1].toString());
			list.add(mp);
		}
		Map json2=new HashMap();
		json2.put("video", list);
		
		
		//Map json2=new HashMap();
		Map mp=new HashMap();
		mp.put("seq", 1);
		mp.put("name", "无");
		json2.put("chapter", mp);
		
		List list2=new ArrayList();
		list2.add(json2);
		//list2.add(json3);
		
		json.put("data", list2);
		return json.toJSONString();
	}
	
	//http://localhost:8080/home/android/courseInfo/data
	@ResponseBody
	@RequestMapping("/courseInfo/data")
	public String getCourseInfo(String courseID) 
	{
		if(null==courseID){
			courseID="40283f815af07e8f015af0802bab0001";
		}
		JSONObject json=new JSONObject();
		Course course = courseService.getCourseById(courseID);
		json.put("courseID", courseID);
		json.put("courseName", course.getCourName());
		Object obj=videoService.getCourseScore(courseID);
		if(null==obj){
			obj=0;
		}
		json.put("score", 3.0);
		json.put("studyNumber", 10);
		json.put("courseDesc", course.getCourIntro());
		json.put("providerImg",FileUrl.readUrl("androidUrl")+"/edu/course/image?imgname="+course.getImgUrl());
		json.put("providerName", course.getSchool());
		json.put("providerDesc", course.getCourName());
		json.put("teacherImg", FileUrl.readUrl("androidUrl")+"/edu/home/android/getAndroidImage?imgname=user.PNG");
		json.put("teacherName", course.getTeacherName());
		json.put("teacherDesc", course.getTeacherIntro());
		
		return json.toJSONString();
	}
	

	//主界面的轮播图
	//http://localhost:8080/home/android/banner/data
	@ResponseBody
	@RequestMapping("/banner/data")
	public String getbannerData() 
	{
		JSONObject json=new JSONObject();
		List<Object[]> list1= courseService.getCourseLimit5();
		List images=new ArrayList();
		images.add("1.jpg");images.add("2.PNG");images.add("3.PNG");images.add("4.PNG");images.add("5.jpg");
		List list=new ArrayList();
		for(int i=0;i<list1.size();i++)
		{
			Map map=new HashMap();
			Object[] object = list1.get(i);
			map.put("courseID", object[0].toString());
			map.put("bannerURL", FileUrl.readUrl("androidUrl")+"/edu/home/android/getAndroidImage?imgname="+images.get(i));
			list.add(map);
		}
		json.put("data", list);
		return json.toJSONString();
	}
	
	@RequestMapping("/getAndroidImage")
	public void getImg(String imgname,HttpServletResponse response) throws IOException{  
        try{  
            FileInputStream hFile = new FileInputStream((FileUrl.readUrl("androidimg")+imgname)); // 以byte流的方式打开文件 d:\1.gif  
            int i=hFile.available(); //得到文件大小  
            byte data[]=new byte[i];  
            hFile.read(data); //读数据  
            hFile.close();  
            response.setContentType("image/*"); //设置返回的文件类型  
            OutputStream toClient=response.getOutputStream(); //得到向客户端输出二进制数据的对象  
            toClient.write(data); //输出数据  
            toClient.close();  
        }  
        catch(IOException e) //错误处理  
        {  
            PrintWriter toClient = response.getWriter(); //得到向客户端输出文本的对象  
            response.setContentType("text/html;charset=utf-8");  
            toClient.write("无法打开图片!");  
            toClient.close();  
        }   
    }  

}
