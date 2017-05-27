package com.hp.webedu.android;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.hp.webedu.entity.User;
import com.hp.webedu.service.CourseService;
import com.hp.webedu.service.UserService;
import com.hp.webedu.util.FileUrl;

//{
//	"state": 0,
//    "desc": "success"
//}

@Controller
@RequestMapping("/home/android")
public class AndroidUpdateApkController {
	
	@Resource
	private UserService userService;
	
	//下载apk
	@RequestMapping(value = "/updatejson/download")
	@ResponseBody
	public ResponseEntity<InputStreamResource> jsonDownload() throws IOException{
    	String fileName1="updateInfo.json";
        //String filePath = FileUrl.readUrl("docModelUrl")+fileName;  
        FileSystemResource file = new FileSystemResource((FileUrl.readUrl("apkUrl")+fileName1));  
        HttpHeaders headers = new HttpHeaders();  
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");  
        headers.add("Content-Disposition", "attachment; filename="+new String(fileName1.getBytes("utf-8"),"ISO-8859-1")); 
        headers.add("Pragma", "no-cache");  
        headers.add("Expires", "0");  
  
        InputStreamResource inputStreamResource = new InputStreamResource(file.getInputStream());
        return ResponseEntity  
                .ok()  
                .headers(headers)  
                .contentLength(file.contentLength())  
                .contentType(MediaType.parseMediaType("application/octet-stream"))  
                .body(inputStreamResource);
	}
	
	//下载apk
	@RequestMapping(value = "/apk/download")
	@ResponseBody
	public ResponseEntity<InputStreamResource> docDownload() throws IOException{
    	String fileName1="android.apk";
        //String filePath = FileUrl.readUrl("docModelUrl")+fileName;  
        FileSystemResource file = new FileSystemResource((FileUrl.readUrl("apkUrl")+fileName1));  
        HttpHeaders headers = new HttpHeaders();  
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");  
        headers.add("Content-Disposition", "attachment; filename="+new String(fileName1.getBytes("utf-8"),"ISO-8859-1")); 
        headers.add("Pragma", "no-cache");  
        headers.add("Expires", "0");  
  
        InputStreamResource inputStreamResource = new InputStreamResource(file.getInputStream());
        return ResponseEntity  
                .ok()  
                .headers(headers)  
                .contentLength(file.contentLength())  
                .contentType(MediaType.parseMediaType("application/octet-stream"))  
                .body(inputStreamResource);
	}
	
	@ResponseBody
	@RequestMapping("/apk/data")
	public String reg(String phone,String pwd,Integer type)
	{
		JSONObject json=new JSONObject();
		json.put("desc", "success");
		
	    return json.toJSONString();	
	}

}
