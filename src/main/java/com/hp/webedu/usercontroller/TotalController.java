package com.hp.webedu.usercontroller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.hp.webedu.entity.Subject;
import com.hp.webedu.service.CourseService;
import com.hp.webedu.service.SubjectService;
import com.hp.webedu.service.VideoService;
import com.hp.webedu.util.FileUrl;
import com.hp.webedu.util.Page;

@Controller
@RequestMapping("/home")
public class TotalController {
	
	@Resource
	private VideoService videoService;
	@Resource
	private CourseService courseService;
	@Resource
	private SubjectService subjectService;
	
	/**
	 * 点击搜索发生的事件
	 * @param subjectId
	 * @param pageNo
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/total/search")
	public String searchSub(String serarchContent,Integer pageNo)
	{
		Integer start=0;
		if(null==pageNo){
			start = 0;
		}else{
			start = (pageNo-1)*Page.pageSize;
		}
		Integer len=Page.pageSize;
		
		List<Object[]> list=courseService.getAllCourseInfoBySearch(serarchContent,start,len);
		List dealResult = getDealResult(list);
		//记录总条数
		Object[] courseCount = courseService.getAllCourseCountBySearch(serarchContent);
		Integer sum=Integer.parseInt(courseCount[0].toString());
		//总页数
		Integer totalPageCount=sum%Page.pageSize==0?sum/Page.pageSize:sum/Page.pageSize+1;
		
		JSONObject json=new JSONObject();
		json.put("courList", dealResult);
		json.put("totalPageCount", totalPageCount);
		json.put("success", true);
		return json.toJSONString();
	}
	
	/**
	 * 点击查看全部进入的界面
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/total/data")
	public String Total(String subjectId,Integer pageNo)
	{
		JSONObject json=new JSONObject();
		json.put("success", true);
		Integer start=0;
		if(null==pageNo){
			start = 0;
		}else{
			start = (pageNo-1)*Page.pageSize;
		}
		Integer len=Page.pageSize;
		if(subjectId.length()<10){
			return json.toJSONString();
		}
		Subject subject = subjectService.getSubjectById(subjectId);
		String subName = subject.getSubName();
		String id = subject.getId();
		List<Object[]> list=courseService.getAllCourseInfo(subjectId,start,len);
		List dealResult = getDealResult(list);
		//记录总条数
		Object[] courseCount = courseService.getAllCourseCount(subjectId);
		Integer sum=Integer.parseInt(courseCount[0].toString());
		//总页数
		Integer totalPageCount=sum%Page.pageSize==0?sum/Page.pageSize:sum/Page.pageSize+1;
		
		json.put("courList", dealResult);
		json.put("subId", id);
		json.put("subName", subName);
		json.put("totalPageCount", totalPageCount);
		return json.toJSONString();
	}
	
	List getDealResult(List<Object[]> ls)
	{
		List ls1=new ArrayList();
		for(Object[] obs:ls)
		{
			Map mp=new HashMap();
			mp.put("id", obs[0]);//课程id
			mp.put("courNmae", obs[1]);//课程名称
			mp.put("teacherName", obs[2]);//讲师名
			mp.put("school", obs[3]);//学校
			mp.put("courIntro", obs[4]);//课程介绍
			mp.put("courImg", "/edu/course/image?imgname="+obs[5]);//课程图片
			ls1.add(mp);
		}
		return ls1;
	}
	
	/**
	 * 跳转到查看全部课程的界面
	 * @param subjectId学科id
	 * @param map
	 * @return
	 */
	@RequestMapping("/total")
	public String Totalui(String subjectId,Model map)
	{
		map.addAttribute("subjectId", subjectId);
		return "/home/total/total";
	}
	

}
