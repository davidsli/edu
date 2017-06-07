package com.hp.webedu.admincomtroller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.hp.webedu.util.FileUrl;

@Controller
@RequestMapping("/admin")
public class ApkController {
	
	//上传apk
	@RequestMapping("/apk/add")
	public String addApk(@RequestParam("apkFile") MultipartFile apkFile,String newVersionName,String newVersionCode,String updateDesc)
	{
		Boolean isForced =true;
		System.out.println(isForced+"  ))))");
		if(null==isForced||isForced.toString().length()<1){
			isForced=false;
		}
		
		JSONObject json=new JSONObject();
		String fileName = "android.apk";
		//fileName=apkFile.getOriginalFilename();
		File file=new File(FileUrl.readUrl("apkUrl")+fileName);
		file.deleteOnExit();
		String readUrl = FileUrl.readUrl("apkUrl")+fileName;
		try {
			IOUtils.copy(apkFile.getInputStream(), FileUtils.openOutputStream(new File(readUrl)));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		File filename=new File("C:\\webedu\\apk\\updateInfo.json");
		filename.deleteOnExit();
		File filename2=new File("C:\\webedu\\apk\\updateInfo.json");
		try {  
            FileOutputStream out = new FileOutputStream(filename2);  
            OutputStreamWriter outWriter = new OutputStreamWriter(out, "utf-8");  
            BufferedWriter bufWrite = new BufferedWriter(outWriter);  
            
//            {
//                "newVersionCode": 3,
//                "newVersionName": "0.8.0",
//                "isForced":false,
//                "downloadUrl": "http://xhw123.cn/cloudCollege/cloudCollege.apk",
//                "updateDesc": "新版本新增功能：\n\n\t\t\t\t1.增加了短信语音验证注册功能\n\t\t\t\t2.增加自动检测更新的功能\n \t\t\t\t3.修复了一部分BUG\n\t\t\t\t4.对一些页面进行了优化\n"
//            }
            
            String[] content=new String[7];
            content[0]="{";
			content[1]="\"newVersionCode\":"+newVersionCode+",";
			content[2]="\"newVersionName\":"+"\""+newVersionName+"\""+",";
			content[3]="\"isForced\":"+isForced+",";
            content[4]="\"downloadUrl\":"+"\""+FileUrl.readUrl("androidUrl")+"/edu/home/android/apk/download"+"\""+",";
			content[5]="\"updateDesc\":"+"\""+updateDesc+"\"";
            content[6]="}";
            for (int i = 0; i < content.length; i++) {  
                bufWrite.write(content[i] + "\n");  
            }  
            bufWrite.close();  
            outWriter.close();  
            out.close();  
        } catch (Exception e) {  
            e.printStackTrace();  
            System.out.println("写入" + filename2 + "出错！");  
        }     
		json.put("success", true);
		return "/admin/frame";
	}
	
	// 跳转到上传apk的界面
	@RequestMapping("/apk/upload")
	public String uploadApk()
	{
		return "/admin/apk/uploadApk";
	}
	
	

}
