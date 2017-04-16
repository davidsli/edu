package com.hp.webedu.usercontroller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.hp.webedu.entity.Course;
import com.hp.webedu.entity.Video;
import com.hp.webedu.service.CourseService;
import com.hp.webedu.service.VideoService;
import com.hp.webedu.util.FileUrl;
/**
 * 播放详情页
 * @author pan.he
 *
 */
@Controller
@RequestMapping("/home")
public class PlayDetailController {
	
	@Resource
	private VideoService videoService;
	@Resource
	private CourseService courseService;
	
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
	public String playDetailData(String courseId)
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
		return json.toJSONString();
	}
	
	//courseId课程号，videoNo当前视频的第几集
	@RequestMapping("/detail/{courseId}")
	public String playDetail(@PathVariable("courseId") String courseId,ModelMap model
			,HttpServletRequest request,String videoId)
	{
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
			request.setAttribute("videoName", video.getVideoDesc());
		}
		
		return "/home/detail/play";
	}

}
