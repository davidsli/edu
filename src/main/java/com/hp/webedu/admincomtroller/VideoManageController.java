package com.hp.webedu.admincomtroller;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.hp.webedu.entity.Course;
import com.hp.webedu.entity.Subject;
import com.hp.webedu.entity.Video;
import com.hp.webedu.service.CourseService;
import com.hp.webedu.service.VideoService;
import com.hp.webedu.util.FileUrl;

//import it.sauronsoftware.jave.Encoder;
//import it.sauronsoftware.jave.EncoderException;
//import it.sauronsoftware.jave.FFMPEGLocator;
//import it.sauronsoftware.jave.InputFormatException;
//import it.sauronsoftware.jave.MultimediaInfo;  


@Controller
@RequestMapping("/admin/video")
public class VideoManageController {
	
	@Resource
	private VideoService videoService;
	
	@Resource
	private CourseService courseService;
	
	//删除评论
	@ResponseBody
	@RequestMapping("/delete")
	public String deleteCourse(String id) throws SQLException
	{
		JSONObject json=new JSONObject();
		int i=videoService.deleteVideo(id);
		json.put("success", true);
		return json.toJSONString();
	}
			
	
	/**
	 * 上传视频文件处理的类
	 * @param videoName视频文件名
	 * @param chapter章节
	 * @param course课程
	 * @param thumb视频缩略图
	 * @param video视频文件
	 * @return
	 * @throws EncoderException 
	 * @throws InputFormatException 
	 */
	@RequestMapping("/add")
	public @ResponseBody String add(String videoName,String chapter,String course,
			@RequestParam("thumb") MultipartFile thumb,@RequestParam("video") MultipartFile video) 
	{
		
		String fileName1 = thumb.getOriginalFilename();
		String suffix = fileName1.substring(fileName1.indexOf('.'));
		Long time = System.currentTimeMillis();
		String fileName = time.toString()+suffix;
		//存放视频图片文件的路径
		String readUrl = FileUrl.readUrl("webEduVideoImgUrl")+fileName;
		
		String fileName2 = video.getOriginalFilename();
		String suffix2 = fileName2.substring(fileName2.indexOf('.'));
		Long time2 = System.currentTimeMillis();
		String fileName22 = time2.toString()+suffix2;
		//存放视频图片文件的路径
		String readUrl2 = FileUrl.readUrl("webEduVideoUrl")+fileName22;
		
		Long videoSize=(video.getSize()/1024);
		System.out.println(videoSize.toString());
		
//		File source= new File("E:\\测试视频\\R41.avi");
//        Encoder encoder = new Encoder();
//	    MultimediaInfo m = encoder.getInfo(source);
//	    long ls = m.getDuration();
//	    System.out.println("此视频时长为:"+ls/60000+"分"+(ls*000)/1000+"秒！");
		
		JSONObject json = new JSONObject();
		try {
			IOUtils.copy(thumb.getInputStream(), FileUtils.openOutputStream(new File(readUrl)));
			IOUtils.copy(video.getInputStream(), FileUtils.openOutputStream(new File(readUrl2)));
			
			
			Course cour=courseService.getCourseById(course);
			Double random = Math.random()%5.5;
			String timeLong=random.toString()+"分钟";
			videoService.saveVideo(new Video(cour,videoName,fileName22,timeLong,chapter,fileName,videoSize.toString()));
			json.put("success", true);
		} catch (IOException e) {
			json.put("success", false);
			e.printStackTrace();
		}
		return "/admin/course/videoList";
	}
	
	/**
	 *  跳转到添加视频的界面
	 * @return
	 */
	@RequestMapping("/addVideo")
	public String addVideo(Model model)
	{
		List<Course> subs=videoService.findAllCourse();
		model.addAttribute("courseList",subs);
		return "admin/course/addVideo";
	}
	
	/**
	 * 视频管理的列表界面
	 * @param map
	 * @param request
	 * @param response
	 * @param search
	 * @param sortingCols
	 * @param start
	 * @param pageSize
	 * @return
	 */
	@RequestMapping("/listData")
	@ResponseBody
	public String videoList(ModelMap map,HttpServletRequest request, HttpServletResponse response, 
			@RequestParam(value = "sSearch") String search, @RequestParam(value = "iSortingCols") int sortingCols,
			@RequestParam(value = "iDisplayStart") int start, @RequestParam(value = "iDisplayLength") int pageSize ) {
		start=start/pageSize;
		int sortCol = 0;
		String sortDir = "asc";
		try {
			sortCol = Integer.parseInt(request.getParameter("iSortCol_" + (sortingCols - 1)));
			sortDir = request.getParameter("sSortDir_" + (sortingCols - 1));
		} catch (Exception e) {
			sortCol = 0;
			sortDir = "asc";
		}
		String colName = request.getParameter("mDataProp_" + sortCol);
		Page<Video> wfs = videoService.getVideoList(search,colName, sortDir, start, pageSize);
		
		JSONObject json = new JSONObject();
		json.put("iTotalRecords",wfs.getTotalElements());//总的记录数
		json.put("iTotalDisplayRecords", wfs.getTotalElements());//需要显示的符合要求的记录数
		List<Video> subList = wfs.getContent();
		List result = new ArrayList();
		//取出项目下的所有任务，里程碑相关人员的id
		for (Video wf: subList) {
			Map wfMap = new HashMap();
			wfMap.put("id", wf.getId());
			wfMap.put("videoDesc", wf.getVideoDesc());
			wfMap.put("timeLong", wf.getTimeLong());
			wfMap.put("chapter", wf.getChapter());
			wfMap.put("course", wf.getCourse().getCourName());
			result.add(wfMap);
		}
		json.put("aaData",result );
		return json.toString();
	}
	
	@RequestMapping("/list")
	public String courseList()
	{
		return "admin/course/videoList";
	}
	

}
