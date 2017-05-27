package com.hp.webedu.usercontroller;

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
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.hp.webedu.entity.Collection;
import com.hp.webedu.entity.Course;
import com.hp.webedu.entity.Video;
import com.hp.webedu.service.CollectionService;
import com.hp.webedu.service.CourseService;
import com.hp.webedu.service.ScoreService;
import com.hp.webedu.service.VideoService;
import com.hp.webedu.util.FileUrl;
import com.hp.webedu.util.ViewHisCour;
import com.hp.webedu.util.WebUtil;
/**
 * 播放详情页
 * @author pan.he
 *
 */
@Controller
@RequestMapping("/home")
public class PlayDetailController {
	
	@Resource
	private CollectionService collectionService;
	@Resource
	private VideoService videoService;
	@Resource
	private CourseService courseService;
	@Resource
	private ScoreService scoreService;
	
	//收藏此门课程
	@ResponseBody
	@RequestMapping("/collectCourse/data")
	public String collectCourse(HttpServletRequest request,String courseId)
	{
		JSONObject json=new JSONObject();
		String userId =(String) request.getSession().getAttribute("userId");
		List<Boolean> list=collectionService.getCollectionByCourseIdUserId(userId,courseId);
		//取消收藏
		if(list.size()>0){
			Boolean bool = list.get(0);
			Integer state=0;
			json.put("collection", false);
			if(false==bool){
				state=1;
				json.put("collection", true);
			}
			int i=collectionService.updateCollectionInfo(userId,courseId,state);
		}else{//第一次收藏
			String str = UUID.randomUUID().toString();
		    String uuid=str.substring(0, 8) + str.substring(9, 13) + str.substring(14, 18) + str.substring(19, 23) + str.substring(24);
		    Timestamp timeStamps=new Timestamp(new Date().getTime());
			int i=collectionService.insertCollInfo(uuid,courseId,userId,1,timeStamps);
			json.put("collection", true);
		}
		json.put("success", true);
		return json.toJSONString();
	}
	
	//点击顶一下
	@ResponseBody
	@RequestMapping("/detail/add/videoScore")
	public String addVideoScore(HttpServletRequest request,String videoId)
	{
		String userId =(String) request.getSession().getAttribute("userId");
		String str = UUID.randomUUID().toString();
	    String uuid=str.substring(0, 8) + str.substring(9, 13) + str.substring(14, 18) + str.substring(19, 23) + str.substring(24);
		int kk=scoreService.saveVideoScore(uuid,userId,videoId,"5");
		JSONObject json=new JSONObject();
		json.put("success", true);
		return json.toJSONString();
	}
	
	@RequestMapping("/top2")
	public String login()
	{
		return "/home/detail/top";
	}
	
	/**
	 *获取页面上面的图片信息，采用流传输的方式
	 * @return
	 */
	@RequestMapping("/getVideoImage")
	public void getImg(String imgname,HttpServletResponse response) throws IOException{  
        try{  
            FileInputStream hFile = new FileInputStream((FileUrl.readUrl("webEduVideoImgUrl")+imgname)); // 以byte流的方式打开文件 d:\1.gif  
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
            response.setContentType("text/html;charset=gb2312");  
            toClient.write("无法打开图片!");  
            toClient.close();  
        }   
    }  
	
	@ResponseBody
	@RequestMapping("/detail/data")
	public String playDetailData(String courseId,String videoId,HttpServletRequest request)
	{
		Course courseById = courseService.getCourseById(courseId);
		JSONObject json=new JSONObject();
		json.put("courName", courseById.getCourName());
		json.put("courIntro", courseById.getCourIntro());
		json.put("teacherName", courseById.getTeacherName());
		json.put("teacherIntro", courseById.getTeacherIntro());
		json.put("school", courseById.getSchool());
		json.put("success", true);
		List<Object[]> list1= videoService.getVideoList(courseId);
		//课程下面的视频集数
		json.put("videoSize", list1.size());
		List list=new ArrayList();
		//0id
		//1video_name
		//2video_desc
		//3thumb
		//4chapter
		
		for(Object[] obs:list1)
		{
			Map mp=new HashMap();
			mp.put("courseId", courseId);
			mp.put("id", obs[0].toString());
			mp.put("videoName", obs[1].toString());
			mp.put("videoDesc", obs[2].toString());
			mp.put("thumb", obs[3].toString());
			mp.put("chapter", obs[4].toString());
			list.add(mp);
		}
		json.put("videoList", list);
		//判断当前用户是否顶一下
		String userId =(String) request.getSession().getAttribute("userId");
		List<Object[]> list2= scoreService.getUserScore(userId,videoId);
		if(list2.size()>0){
			json.put("dyx", true);
		}else{
			json.put("dyx", false);
		}
		//判断此门课程是否被收藏过
		List<Boolean> list3=collectionService.getCollectionByCourseIdUserId(userId,courseId);
		json.put("collection", false);
		if(list3.size()>0){
			Boolean bool = list3.get(0);
			if(true==bool){
				json.put("collection", true);
			}
		}
		return json.toJSONString();
	}
	
	//courseId课程号，videoNo当前视频的第几集
	@RequestMapping("/detail/{courseId}")
	public String playDetail(@PathVariable("courseId") String courseId,ModelMap model
			,HttpServletRequest request,HttpServletResponse response,String videoId)
	{
		String userID=(String) request.getSession().getAttribute("userId");
		if(null==userID||userID==""){
			return "/home/loginUI";
		}
		String cookie = WebUtil.getCookieByName(request, "courseViewhistory");
		if(null==cookie){
			WebUtil.addCookie(response, "courseViewhistory", "", 30*24*60*60);
		}
		String cookValue = ViewHisCour.buildViewHistory(request, courseId);
		WebUtil.addCookie(response, "courseViewhistory", cookValue, 30*24*60*60);
		model.put("courseId", courseId);
		//从列表页面进入的时候
		if(null==videoId||videoId.isEmpty()){
			String videoNo="1";
			List<Object[]> objList= videoService.getVideo(courseId,videoNo);
			for(Object[] obs:objList){
				model.put("videoId", obs[0].toString());
				request.setAttribute("videoName", obs[1].toString());
				//model.put("videoName",obs[1].toString());
			}
		}
		//点击列表页面进入的时候
		else{
			Video video=videoService.getVideoById(videoId);
			model.put("videoId", video.getId());
			request.setAttribute("videoName", video.getVideoName());
		}
		
		model.put("streamer", FileUrl.readUrl("streamer"));
		return "/home/detail/play2";
	}

}
