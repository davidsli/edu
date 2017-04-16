package com.hp.webedu.usercontroller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;

@Controller
@RequestMapping("/home")
public class TestController {
	
	
	@RequestMapping("/detail/comment/index")
	public String login()
	{
		return "/home/detail/comment/index";
	}
	
	@ResponseBody
	@RequestMapping("/add")
	public String add(@RequestParam(value="file5",required = false) MultipartFile imgUrl3)
	{
		JSONObject json = new JSONObject();
		System.out.println(imgUrl3.getOriginalFilename()+"  *****");
		json.put("success", true);
		return json.toString();
	}
	
	/**
	 * 跳转到登录界面
	 * @return
	 */
	@RequestMapping("/image")
	public void getimg(String saveAddress ,HttpServletRequest request, HttpServletResponse response) throws IOException{  
        try{  
            FileInputStream hFile = new FileInputStream("D:/images/brand/2016/02/22/15/bee8ae9b-b38a-42ab-bca5-e8894e659393.jpg"); // 以byte流的方式打开文件 d:\1.gif  
            int i=hFile.available(); //得到文件大小  
            byte data[]=new byte[i];  
            hFile.read(data); //读数据  
            hFile.close();  
            response.setContentType("image/jpg"); //设置返回的文件类型  
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
