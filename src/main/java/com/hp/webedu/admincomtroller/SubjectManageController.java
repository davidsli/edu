package com.hp.webedu.admincomtroller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.hp.webedu.entity.Subject;
import com.hp.webedu.service.SubjectService;

@Controller
@RequestMapping("/admin/subject")
public class SubjectManageController {
	
	@Resource
	private SubjectService subjectService;
	
	//项目成员列表页面
	@RequestMapping("/listData")
	@ResponseBody
	public String subjectList(ModelMap map,HttpServletRequest request, HttpServletResponse response, Principal user,
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
		Page<Subject> wfs = subjectService.getSubjectList(search,colName, sortDir, start, pageSize);
		
		JSONObject json = new JSONObject();
		json.put("iTotalRecords",wfs.getTotalElements());//总的记录数
		json.put("iTotalDisplayRecords", wfs.getTotalElements());//需要显示的符合要求的记录数
		List<Subject> subList = wfs.getContent();
		List result = new ArrayList();
		//取出项目下的所有任务，里程碑相关人员的id
		for (Subject wf: subList) {
			Map wfMap = new HashMap();
			wfMap.put("subName", wf.getSubName());
			wfMap.put("id", wf.getId());
			result.add(wfMap);
		}
		json.put("aaData",result);
		return json.toString();
	}
	
	/**
	 * 跳转到学科的列表页面
	 * @return
	 */
	@RequestMapping("/list")
	public String subList()
	{
		return "admin/subject/subList";
	}
	
	/**
	 *  添加科目
	 * @param subName
	 * @return
	 */
	@RequestMapping("/add")
	public String frame(String subName)
	{
		JSONObject json=new JSONObject();
		try{
			subjectService.saveSubject(new Subject(subName));
			json.put("success", true);
		}catch(Exception e){
			json.put("success", false);
		}
		return json.toString();
	}

}
