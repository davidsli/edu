package com.hp.webedu.util;

import org.springframework.stereotype.Service;

public class StuServiceImpl {
	
	
	public static void main(String[] args) {
		StuServiceImpl impl=new StuServiceImpl();
		impl.sendEmail("13257499906@163.com", "hepan");
	}
	//完成邮箱验证的功能
	public String sendEmail(String res_email, String res_userName) {
		 //HttpServletRequest request = ServletActionContext.getRequest();
		 int code=(int) (Math.random()*100000);
		 StringBuffer sb=new StringBuffer("点击下面链接激活账号，48小时生效，否则重新注册账号，链接只能使用一次，请尽快激活！</br>");
		    String site=FileUrl.readUrl("href");
		    String str="<a href='"+site+"/softtestmeg/sys/stu_active?mail=";
		    sb.append(str);
//		    sb.append();  
//		    sb.append(ServletActionContext.getRequest().getContextPath());
//	        sb.append("/sys/stu_active.action?mail=");
	        sb.append(res_email); 
	        sb.append("'>激活"); 
	        sb.append("</a>");
		return SendMail.send(sb.toString(), "网站激活邮件",res_email);
	}
	
//	sb.append("=activate&email=");
//    sb.append(email); 
//    sb.append("&validateCode="); 
//    sb.append(user.getValidateCode());
//    sb.append("\">http://localhost:8080/springmvc/user/register?action=activate&email="); 
//    sb.append(email);
//    sb.append("&validateCode=");
//    sb.append(user.getValidateCode());
//    sb.append("</a>");


}
