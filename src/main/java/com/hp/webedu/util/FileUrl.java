package com.hp.webedu.util;

import java.io.IOException;
import java.util.Properties;

public class FileUrl {
	//让它实现单例状态
		private static Properties pro=new Properties();
		//获取他的类装载器这个装载器肯定是从类路径下面去装载它,从类路
		static
		{
			try {
				pro.load(FileUrl.class.getClassLoader().getResourceAsStream("siteurl.properties"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		//读取属性文件，将他的value返回去，传进来的参数是键
		public static String readUrl(String key)
		{
			return pro.getProperty(key);
		}

}
