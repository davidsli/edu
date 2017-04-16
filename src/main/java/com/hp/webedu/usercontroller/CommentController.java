package com.hp.webedu.usercontroller;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.hp.webedu.service.CommentService;

/**
 * 评论控制类
 * @author pan.he
 *
 */
@Controller
@RequestMapping("/home/detail")
public class CommentController {
	
	@Resource
	private CommentService commentService;
	
	/**
	 * 开始进去的时候获取用户的评论列表
	 * @param videoId
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/comment/list")
	public String getCommentList(String videoId) 
	{
		List<Object[]> comList=commentService.getCommentList(videoId);
		JSONObject json=new JSONObject();
		List list=new ArrayList();
		for(Object[] obj:comList)
		{
			Map map=new HashMap();
			String nickName;
			if(null==obj[0]){
				nickName="匿名";
			}
			else{
				nickName=obj[0].toString();
			}
			map.put("nickName", nickName);//昵称
			map.put("content", obj[1].toString());//内容
			map.put("comTime", obj[2].toString());//评论时间
			list.add(map);
		}
		json.put("comList", list);
		json.put("success", true);
		return json.toString();
	}
	
	/**
	 * 添加用户的评论
	 * @param request
	 * @param videoId
	 * @param content
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/comment/add")
	public String addComment(HttpServletRequest request,String videoId,String content) 
	{
		//commentService
		//查找计算机相关的课程，人气，视频，集数相关的信息
	    String subId="297e386a56410ddd0156411a94c50005";//学科id
	    String userId=(String) request.getSession().getAttribute("userId");
	    String str = UUID.randomUUID().toString();
	    String uuid=str.substring(0, 8) + str.substring(9, 13) + str.substring(14, 18) + str.substring(19, 23) + str.substring(24);
	    Boolean bool=commentService.saveComment(videoId,content,userId,uuid,true,new Timestamp(new Date().getTime()));
		JSONObject json=new JSONObject();
		json.put("success", true);
		return json.toJSONString();
	}
	

}
