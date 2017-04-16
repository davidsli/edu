package com.hp.webedu;

import javax.servlet.MultipartConfigElement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.embedded.MultipartConfigFactory;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

import com.hp.webedu.Appctx;

@SpringBootApplication
public class Application extends SpringBootServletInitializer {

	public SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(Application.class); 
	}

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(Application.class);
		Appctx.ctx = app.run(args);
		/*
		 * InnerUserService suerService=(InnerUserService)
		 * Appctx.ctx.getBean("innerUserService"); //BCryptPasswordEncoder
		 * bc=new BCryptPasswordEncoder(4); //suerService.addInnerUser(new
		 * InnerUser("linna",CloudPasswordEncoder.encode("linna")));
		 * suerService.addInnerUser(new
		 * InnerUser("linna",CloudPasswordEncoder.encode("linna")));
		 */
	}
	
	//注册上传文件的一个bean，实现上传文件的控制
    @Bean
	public MultipartConfigElement multipartConfigElement() {
		MultipartConfigFactory factory = new MultipartConfigFactory();
		factory.setMaxFileSize("2048MB");
		factory.setMaxRequestSize("2048MB");
		return factory.createMultipartConfig();
	}
	
}
