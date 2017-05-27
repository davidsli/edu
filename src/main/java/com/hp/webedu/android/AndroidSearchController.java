package com.hp.webedu.android;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
public class AndroidSearchController {
	
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
	
	//http://localhost:8080/home/android/SearchResult/data?typeId=""&keyWords="11"
	@ResponseBody
	@RequestMapping("/SearchResult/data")
	public String getSearchResult(String typeID,String keyWords) 
	{
		if(null==typeID||typeID==""){
			typeID="";
		}
		System.out.println(typeID+"  "+keyWords);
		JSONObject json=new JSONObject();
		List<Object[]> list1=courseService.getAllKindsOfCourseList(typeID,keyWords);
		List list=new ArrayList();
		for(Object[] obs:list1){
			Map mp=new HashMap();
			mp.put("courseID", obs[0].toString());
			mp.put("courseImg", FileUrl.readUrl("androidUrl")+"/edu/course/image?imgname="+obs[2].toString());
			mp.put("courseName", obs[1]);
			if(null!=obs[3]){
				mp.put("score",Double.parseDouble(obs[3].toString()));//分数
			}else{
				Double dou=3.0;
				mp.put("score",dou);//分数
			}
			mp.put("studyNumber", 10);
			list.add(mp);
		}
		json.put("data", list);
		return json.toJSONString();
	}

}
