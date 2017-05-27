package com.hp.webedu.util;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.MimeMessageHelper;

/**
 * 发送邮件的类,可带附件
 * @author pan.he
 */
public class SendMail {
	
	/**
	 * 不带附件的邮件
	 * @param text
	 * @param address
	 * @return
	 */
	public static String send(String text,String subject,String address) 
	{
		Properties pros=new Properties();
		pros.setProperty("mail.smtp.auth", "true");
		pros.setProperty("mail.transport.protocol", "smtp");
		Session session=Session.getInstance(pros);
		session.setDebug(true);
		
		Message msg=new MimeMessage(session);
		int flag=1;
		Transport tran;
		try {
			msg.setFrom(new InternetAddress("hepan2013551631@sina.com"));
			msg.setSubject(subject);
			msg.setContent(text, "text/html;charset=utf-8");
			tran = session.getTransport("smtp");
			tran.connect("smtp.sina.com", 25, "hepan2013551631", "hepan2013551631");
				tran.sendMessage(msg,new Address[]{new InternetAddress(address)});
				tran.close();
		} catch (Exception e) {
			flag = 0;
			return "fail";
		} 
		if(flag==1)
		{
			return "seccess";
		}
		return "seccess";
	}

	
	/**
	 * 发送带附件的邮箱
	 * @param subject  邮箱主题
	 * @param text  文本内容
	 * @param attachFile 附件
	 * @param address  目标地址
	 * @return
	 */
	public static String send(String subject,String text,FileSystemResource attachFile,String address) 
	{
		Properties pros=new Properties();
		pros.setProperty("mail.smtp.auth", "true");
		pros.setProperty("mail.transport.protocol", "smtp");
		Session session=Session.getInstance(pros);
		session.setDebug(true);
		
		MimeMessage msg=new MimeMessage(session);
		
		int flag=1;
		Transport tran;
		try {
			MimeMessageHelper helper = new MimeMessageHelper(msg, true);
			helper.setFrom(new InternetAddress("hepan2013551631@sina.com"));
			helper.setSubject("邮箱主题");
			helper.setText(subject);
			FileSystemResource file = new FileSystemResource("d:\\1.txt");
			
			helper.addAttachment(file.getFilename(), file);
			
			tran = session.getTransport("smtp");
			tran.connect("smtp.sina.com", 25, "hepan2013551631", "hepan2013551631");
			
			tran.sendMessage(helper.getMimeMessage(),new Address[]{new InternetAddress(address)});
			tran.close();
		} catch (Exception e) {
			flag = 0;
			return "fail";
		} 
		if(flag==1)
		{
			return "seccess";
		}
		return "seccess";
	}


}
