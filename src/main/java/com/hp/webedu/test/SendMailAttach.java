package com.hp.webedu.test;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailParseException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import com.hp.webedu.util.SendMail;

public class SendMailAttach {
	
	private JavaMailSender mailSender;
	private SimpleMailMessage simpleMailMessage;
	
	 public static Logger logger = LoggerFactory.getLogger(Main.class);
	 
	 public void sendMail(String dear, String content) {
		    Properties pros=new Properties();
			pros.setProperty("mail.smtp.auth", "true");
			pros.setProperty("mail.transport.protocol", "smtp");
			Session session=Session.getInstance(pros);
			session.setDebug(true);
			
			//Message msg=new MimeMessage(session);
			
		   MimeMessage message = new MimeMessage(session);
		   simpleMailMessage=new SimpleMailMessage();
		   simpleMailMessage.setFrom("hepan2013551631@sina.com");
		   simpleMailMessage.setTo("13259499906@163.com");
		   simpleMailMessage.setSubject("主题");
		   simpleMailMessage.setText("正文文本内容");
		   try{
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			
			helper.setFrom(simpleMailMessage.getFrom());
			helper.setTo(simpleMailMessage.getTo());
			helper.setSubject(simpleMailMessage.getSubject());
			helper.setText(String.format(
				simpleMailMessage.getText(), dear, content));
				
			FileSystemResource file = new FileSystemResource("d:\\1.txt");
			helper.addAttachment(file.getFilename(), file);
            
			
		     }catch (Exception e) {
			throw new MailParseException(e);
		     }
		     mailSender.send(message);
	         }
	 
	 public static void main(String[] args) {
		 SendMail sendMail=new SendMail();
		 sendMail.send("hhhhhhhhhhhhh","", "13257499906@163.com");
		   // SendMailAttach mm = new SendMailAttach();
	        //mm.sendMail("Yiibai", "This is text content");
	}
	 

}
