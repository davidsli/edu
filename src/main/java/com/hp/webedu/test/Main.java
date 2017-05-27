package com.hp.webedu.test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Date;
import java.util.UUID;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailParseException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import com.hp.webedu.util.FileUrl;
import com.hp.webedu.util.SendMail;

//import it.sauronsoftware.jave.AudioAttributes;
//import it.sauronsoftware.jave.Encoder;
//import it.sauronsoftware.jave.EncoderException;
//import it.sauronsoftware.jave.EncodingAttributes;
//import it.sauronsoftware.jave.InputFormatException;
//import it.sauronsoftware.jave.MultimediaInfo;
//import it.sauronsoftware.jave.VideoAttributes;
//import it.sauronsoftware.jave.VideoSize;

public class Main {
	private JavaMailSender mailSender;
	private SimpleMailMessage simpleMailMessage;
	
	 public static Logger logger = LoggerFactory.getLogger(Main.class);
	 
	 public void sendMail(String dear, String content) {
			
		   MimeMessage message = mailSender.createMimeMessage();
			
		   try{
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
				
			helper.setFrom(simpleMailMessage.getFrom());
			helper.setTo(simpleMailMessage.getTo());
			helper.setSubject(simpleMailMessage.getSubject());
			helper.setText(String.format(
				simpleMailMessage.getText(), dear, content));
				
			FileSystemResource file = new FileSystemResource("C:\\log.txt");
			helper.addAttachment(file.getFilename(), file);

		     }catch (MessagingException e) {
			throw new MailParseException(e);
		     }
		     mailSender.send(message);
	         }
	 
	
	/*public void sms() throws Exception {
		String result = "0";
		*//** 手机号码 *//*
		String jbPhone = WebContextUtil.getRequest().getParameter("jbPhone");
		*//** 短信验证码 *//*
		String code = WebContextUtil.getRequest().getParameter("code");
		*//** 短信验证码存入session(session的默认失效时间30分钟) *//*
		WebContextUtil.getSession().setAttribute("code", code);
		*//** 如何初始化失败返回 *//*
		if(!initClient()) {
			return;
		}
		*//** 单个手机号发送短信的方法的参数准备 *//*
		// 手机号码
		String mobilephone = jbPhone;
		// 短信内容+随机生成的6位短信验证码
		String content = "根据中国证监会举报中心委托，特向您发送此条短信。您的注册验证码为:" + code;
		// 操作用户的ID
		Integer operId = Integer.parseInt(Env.getInstance().getProperty("operId"));
		// 定时发送的的发送时间(缺省为空，如果即时发送，填空)
		String tosend_time = "";
		// 应用系统的短信ID，用户查询该短信的状态报告(缺省为0，即不需查询短信的状态报告)
		int sms_id = 0;
		// 黑名单过滤(0：不需要黑名单过滤，1：需要黑名单过滤，缺省为0)
		short backlist_filter = 0;
		// 禁止语过滤(0：不需要禁止语过滤，1：需要禁止语过滤，缺省为0)
		short fbdword_filter = 0;
		// 优先级(值越大优先级越高，0：普通，1,：优先，2：最高，缺省为0)
		short priority = 0;
		// 短信有效时间(格式为：YYYY-MM-DD HH:mm:ss目前为空)
		String valid_time = "";
		*//** 发送短信之前先统计一个已经发送的短信条数 *//*
		int messageCount = countService.findAllRecord(mobilephone);
		System.out.println("已发短信条数为：" +messageCount);
		if(messageCount < 5){
			*//** 单个手机号发送短信 *//*
			if (!sendMessage(mobilephone, content, operId, tosend_time, sms_id,
					backlist_filter, fbdword_filter, priority, valid_time)) {
				result = "0";// 失败
			} else {
				result = "1";// 成功
				*//** 发送一条短信，记录一条短信记录，为了方便之后的统计短信发送次数 *//*
				count.setPhone(mobilephone);// 手机号码
				count.setCaptcha(code);// 短信验证码
				count.setSendTime(CommonUtil.getNowDate());// 短信发送时间
				if(count != null){
					countService.saveEntity(count);
					System.out.println("保存成功!");
				}
			}
		}else{
			System.out.println("该手机号码今天发送验证码过多");
			result = "2";//一个手机号码最多发送3条短信验证码
		}
		HttpServletResponse response = WebContextUtil.getResponse();
		response.setContentType("application/json;charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		out.write(result.toString());
	}
	
	*//**
	 * WebService客户端初始化
	 * 
	 *//*
	public static boolean initClient() {
		*//**
		 * 判断客户端是否已经初始化
		 *//*
		if (!SmsWebClient.enable()) {
			int ret = 0;
			try {
				ret = SmsWebClient.init(url, userName, passWord);
				if (ret == -1 || !SmsWebClient.enable()) {
					System.out.println("短信平台接口初始化失败！");
					return false;
				}
				System.out.println("短信平台接口初始化成功！"+ret+"----------");
			} catch (Exception ex) {
				ex.printStackTrace();
				System.out.println("短信平台接口初始化过程中异常！");
			}
		}
		return true;
	}
	
	
	*//**
	 * 单个手机号码发送
	 * 
	 * @param mobilephone
	 *            手机号
	 * @param content
	 *            短信内容
	 * @param operId
	 *            操作用户的ID
	 * @param tosend_time
	 *            定时发送的发送时间
	 * @param sms_id
	 *            应用系统的短信ID
	 * @param backlist_filter
	 *            黑名单过滤
	 * @param fbdword_filter
	 *            禁止语过滤
	 * @param priority
	 *            优先级
	 * @param valid_time
	 *            短信有效时间
	 * @throws FileNotFoundException 
	 *//*
	public static boolean sendMessage(java.lang.String mobilephone,
			java.lang.String content, int operId, java.lang.String tosend_time,
			int sms_id, short backlist_filter, short fbdword_filter,
			short priority, java.lang.String valid_time) {
		// 单个手机号码发送
		try {
			SmsReturnObj retObj = SmsWebClient.webSendMessage(mobilephone,
					content, operId, tosend_time, sms_id, backlist_filter,
					fbdword_filter, priority, valid_time);
			if (retObj.getReturnCode() != 1) {
				System.out.println("短信发送失败，原因为：" + retObj.getReturnMsg());
				return false;
			} else {
				System.out.println("短信发送成功！返回结果为：" + retObj.getReturnMsg());
				return true;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("短信发送过程发生异常!");
		}
		return true;
	}*/
	
	public static void main(String[] args) throws FileNotFoundException{
		
		logger.debug("sdfffffffff");
		
		//完成邮箱验证的功能
		
			 //SendMail sendMail=new SendMail();
			 //HttpServletRequest request = ServletActionContext.getRequest();
			 int code=(int) (Math.random()*100000);
			 StringBuffer sb=new StringBuffer("点击下面链接激活账号，48小时生效，否则重新注册账号，链接只能使用一次，请尽快激活！</br>");
			    //String site=SiteUrl.readUrl("href");
			    String str="<a href=http://localhost:8080/softtestmeg/sys/stu_active.action?mail=";
			    //sb.append(str);
//			    sb.append();  
//			    sb.append(ServletActionContext.getRequest().getContextPath());
//		        sb.append("/sys/stu_active.action?mail=");
		        sb.append("123"); 
		        sb.append("&&validateCode="); 
		        sb.append(String.valueOf(code));
		        sb.append("&&stu_userName=");
		        sb.append("hepan");
		        sb.append("'>激活"); 
		        sb.append("</a>");
		        System.out.println(sb.toString()+"####################################");
		        SendMail.send(sb.toString(), "","13257499906@163.com");
		
		
//		File filename=new File("C:\\webedu\\apk\\updateInfo.json");
//		try {  
//            FileOutputStream out = new FileOutputStream(filename);  
//            OutputStreamWriter outWriter = new OutputStreamWriter(out, "UTF-8");  
//            BufferedWriter bufWrite = new BufferedWriter(outWriter);  
//            String[] content=new String[2];
//            content[0]="0000000000";
//            content[1]="1111111111";
//            for (int i = 0; i < content.length; i++) {  
//                bufWrite.write(content[i] + "\n");  
//            }  
//            bufWrite.close();  
//            outWriter.close();  
//            out.close();  
//        } catch (Exception e) {  
//            e.printStackTrace();  
//            System.out.println("读取" + filename + "出错！");  
//        }     
//		
//		try {  
//            FileInputStream in = new FileInputStream(filename);  
//            InputStreamReader inReader = new InputStreamReader(in, "UTF-8");  
//            BufferedReader bufReader = new BufferedReader(inReader);  
//            String line = null;  
//            int i = 1;  
//            while((line = bufReader.readLine()) != null){  
//                System.out.println("第" + i + "行：" + line);  
//                i++;  
//            }  
//            bufReader.close();  
//            inReader.close();  
//            in.close();  
//        } catch (Exception e) {  
//            e.printStackTrace();  
//            System.out.println("读取" + filename + "出错！");  
//        }  
		
//		String string = UUID.randomUUID().toString();
//		System.out.println(string.length());
//		File source = new File("source.avi");  
//		File target = new File("target.flv");  
//		AudioAttributes audio = new AudioAttributes();  
//		audio.setCodec("libmp3lame");  
//		audio.setBitRate(new Integer(64000));  
//		audio.setChannels(new Integer(1));  
//		audio.setSamplingRate(new Integer(22050));  
//		VideoAttributes video = new VideoAttributes();  
//		video.setCodec("flv");  
//		video.setBitRate(new Integer(160000));  
//		video.setFrameRate(new Integer(15));  
//		video.setSize(new VideoSize(400, 300));  
//		EncodingAttributes attrs = new EncodingAttributes();  
//		attrs.setFormat("flv");  
//		attrs.setAudioAttributes(audio);  
//		attrs.setVideoAttributes(video);  
//		Encoder encoder = new Encoder();  
//		encoder.encode(source, target, attrs);
		
//		Long time = new Date().getTime();
//		Long currentTimeMillis = System.currentTimeMillis();
//		System.out.println(time);
//		System.out.println(currentTimeMillis);
		
//		StringBuffer sb=new StringBuffer("点击下面链接激活账号，48小时生效，否则重新注册账号，链接只能使用一次，请尽快激活！</br>");
//	    String site=FileUrl.readUrl("href");
//	    String str="<a href='"+site+"/softtestmeg/sys/stu_active.action?mail=";
//	    sb.append(str);
//        sb.append("gfjh");
//        sb.append("'>激活&lt;"); 
//        sb.append("</a>");
//        SendMail.send(sb.toString(), "13257499906@163.com");
        
	}

}
