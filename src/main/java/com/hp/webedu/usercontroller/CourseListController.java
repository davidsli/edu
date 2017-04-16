package com.hp.webedu.usercontroller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonObject;
import com.hp.webedu.service.CourseService;
import com.hp.webedu.util.FileUrl;

import freemarker.template.utility.StringUtil;
import scala.annotation.meta.param;

@Controller
@RequestMapping("/course")
public class CourseListController {
	
	@Resource
	private CourseService courseService;
	
	/**
	 * 思政
	 * @return
	 * @throws SQLException
	 */
	@ResponseBody
	@RequestMapping("/getIdeList")
	public String getIdeList() throws SQLException
	{
		//查找
	    String subId="297e386a56410ddd0156411a94c50006";//学科id
	    List<Object[]> comls=courseService.getAllKindsOfList(subId);
		List compList=getDealResult(comls);
		JSONObject json=new JSONObject();
		json.put("ideList", compList);
		json.put("success", true);
		return json.toJSONString();
	}
	
	/**
	 * 哲学
	 * @return
	 * @throws SQLException
	 */
	@ResponseBody
	@RequestMapping("/getPhiList")
	public String getPhiList() throws SQLException
	{
		//查找
	    String subId="297e386a56410ddd0156411a94c50003";//学科id
	    List<Object[]> comls=courseService.getAllKindsOfList(subId);
		List compList=getDealResult(comls);
		JSONObject json=new JSONObject();
		json.put("phiList", compList);
		json.put("success", true);
		return json.toJSONString();
	}
	
	/**
	 * 人文
	 * @return
	 * @throws SQLException
	 */
	@ResponseBody
	@RequestMapping("/getHumList")
	public String getHumList() throws SQLException
	{
		//查找
	    String subId="297e386a56410ddd0156411a94c50002";//学科id
	    List<Object[]> comls=courseService.getAllKindsOfList(subId);
		List compList=getDealResult(comls);
		JSONObject json=new JSONObject();
		json.put("humList", compList);
		json.put("success", true);
		return json.toJSONString();
	}
	
	/**
	 *  查找经济课程相关的数据信息
	 * @return
	 * @throws SQLException
	 */
	@ResponseBody
	@RequestMapping("/getEcoList")
	public String getEcoList() throws SQLException
	{
		//查找
	    String subId="297e386a56410ddd0156411a94c50001";//学科id
	    List<Object[]> comls=courseService.getAllKindsOfList(subId);
		List compList=getDealResult(comls);
		JSONObject json=new JSONObject();
		json.put("ecoList", compList);
		json.put("success", true);
		return json.toJSONString();
	}
	
	/**
	 *  查找工科相关的数据信息
	 * @return
	 * @throws SQLException
	 */
	@ResponseBody
	@RequestMapping("/getEngList")
	public String getEngList() throws SQLException
	{
		//查找
	    String subId="297e386a56410ddd0156411a94c50004";//学科id
	    List<Object[]> comls=courseService.getAllKindsOfList(subId);
		List compList=getDealResult(comls);
		JSONObject json=new JSONObject();
		json.put("engList", compList);
		json.put("success", true);
		return json.toJSONString();
	}
	
	//获取计算机课程的相关的列表
	@ResponseBody
	@RequestMapping("/getComputerList")
	public String getComputerList() throws SQLException
	{
		//查找计算机相关的课程，人气，视频，集数相关的信息
	    String subId="297e386a56410ddd0156411a94c50005";//学科id
	    List<Object[]> comls=courseService.getAllKindsOfList(subId);
		List compList=getDealResult(comls);
		JSONObject json=new JSONObject();
		json.put("compList", compList);
		json.put("success", true);
		return json.toJSONString();
	}
	
	/**
	 * 显示课程的列表页面
	 * @return
	 * @throws SQLException 
	 */
	@RequestMapping("/list1")
	public String getCourseList(ModelMap model) throws SQLException
	{
		//查找计算机相关的课程，人气，视频，集数相关的信息
	    String subId="297e386a56410ddd0156411a94c50005";//学科id
//	    List<Object[]> comls=courseService.getAllKindsOfList(subId);
//		List compList=getDealResult(comls);
//		model.put("compList", compList);
	    //工科
	    subId="297e386a56410ddd0156411a94c50004";
	    List<Object[]> engls=courseService.getAllKindsOfList(subId);
		List engList=getDealResult(engls);
		model.addAttribute("engList", engList);
	    //经济
	    subId="297e386a56410ddd0156411a94c50001";
	    List<Object[]> ecomls=courseService.getAllKindsOfList(subId);
		List ecomList=getDealResult(ecomls);
		model.addAttribute("ecomList", ecomList);
	    //人文
	    subId="297e386a56410ddd0156411a94c50002";
	    List<Object[]> humls=courseService.getAllKindsOfList(subId);
		List humList=getDealResult(humls);
		model.addAttribute("humList", humList);
	    //哲学
	    subId="297e386a56410ddd0156411a94c50003";
	    List<Object[]> phils=courseService.getAllKindsOfList(subId);
		List phiList=getDealResult(phils);
		model.addAttribute("phiList", phiList);
	    //思政
	    subId="297e386a56410ddd0156411a94c50006";
	    List<Object[]> idels=courseService.getAllKindsOfList(subId);
		List ideList=getDealResult(idels);
		model.addAttribute("ideList", ideList);
	    
		return "/home/index";
	}

	List getDealResult(List<Object[]> ls)
	{
		List ls1=new ArrayList();
		for(Object[] obs:ls)
		{
			Map mp=new HashMap();
			mp.put("id", obs[0]);
			mp.put("courNmae", obs[1]);
			mp.put("imgUrl", "/course/image?imgname="+obs[2].toString());
			if(null!=obs[3]){
				mp.put("score",obs[3].toString());//分数
			}else{
				mp.put("score","0");//分数
			}
			if(null!=obs[4]){
				mp.put("episodes",obs[4].toString());//集数
			}else{
				mp.put("episodes","0");
			}
			ls1.add(mp);
		}
		return ls1;
	}
	
	
	/**
	 *获取页面上面的图片信息，采用流传输的方式
	 * @return
	 */
	@RequestMapping("/image")
	public void getImg(String imgname,String saveAddress ,HttpServletRequest request, HttpServletResponse response) throws IOException{  
        try{  
            FileInputStream hFile = new FileInputStream((FileUrl.readUrl("webEduCourseImgUrl")+imgname)); // 以byte流的方式打开文件 d:\1.gif  
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

}
