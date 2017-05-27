package com.hp.webedu.admincomtroller;

import java.io.File;
import java.io.IOException;
import java.security.Principal;
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
import com.hp.webedu.service.CourseService;
import com.hp.webedu.service.SubjectService;
import com.hp.webedu.util.FileUrl;

@Controller
@RequestMapping("/admin/course")
public class CourseManageController {
	
	@Resource
	private CourseService courseService;
	
	@Resource
	private SubjectService subjectService;
	
	//删除课程
	@ResponseBody
	@RequestMapping("/delete")
	public String deleteCourse(String id) throws SQLException
	{
		JSONObject json=new JSONObject();
		int i=courseService.deleteCourse(id);
		json.put("success", true);
		return json.toJSONString();
	}
		
		
	/**
	 *  处理添加一门课程
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/add")
	public String add(@RequestParam("courseimgUrl") MultipartFile imgUrl,String courseName,String courIntro,String teacherName
			,String teacherIntro,String school,String subject)
	{
		String fileName1 = imgUrl.getOriginalFilename();
		String suffix = fileName1.substring(fileName1.indexOf('.'));
		Long time =System.currentTimeMillis();
		String fileName = time.toString()+suffix;
		String readUrl = FileUrl.readUrl("webEduCourseImgUrl")+fileName;
		JSONObject json = new JSONObject();
		try {
			IOUtils.copy(imgUrl.getInputStream(), FileUtils.openOutputStream(new File(readUrl)));
			Subject sub=subjectService.getSubjectById(subject);
			courseService.saveCourse(new Course(courseName,courIntro,teacherName,teacherIntro,school,sub,fileName));
			json.put("success", true);
		} catch (IOException e) {
			json.put("success", false);
			e.printStackTrace();
		}
		return json.toString();
	}
	
	/**
	 *  跳转到添加课程的界面
	 * @return
	 */
	@RequestMapping("/addCourse")
	public String addCourse(Model model)
	{
		List<Subject> subs=subjectService.findAllSubject();
		model.addAttribute("subList",subs);
		return "/admin/course/addCourse";
	}
	
	/**
	 * 查找课程列表
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
	public String courseList(ModelMap map,HttpServletRequest request, HttpServletResponse response, 
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
		Page<Course> wfs = courseService.getCourseList(search,colName, sortDir, start, pageSize);
		
		JSONObject json = new JSONObject();
		json.put("iTotalRecords",wfs.getTotalElements());//总的记录数
		json.put("iTotalDisplayRecords", wfs.getTotalElements());//需要显示的符合要求的记录数
		List<Course> subList = wfs.getContent();
		List result = new ArrayList();
		//取出项目下的所有任务，里程碑相关人员的id
		for (Course wf: subList) {
			Map wfMap = new HashMap();
			wfMap.put("id", wf.getId());
			wfMap.put("courName", wf.getCourName());
			wfMap.put("teacherName", wf.getTeacherName());
			wfMap.put("school", wf.getSchool());
			wfMap.put("subName", wf.getSubject().getSubName());
			result.add(wfMap);
		}
		json.put("aaData",result );
		return json.toString();
	}
	
	/**
	 * 跳转到课程的列表页面
	 * @return
	 */
	@RequestMapping("/list")
	public String courseList()
	{
		return "/admin/course/courseList";
	}

}
