package com.hp.webedu.service;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.hp.webedu.dao.UserDao;
import com.hp.webedu.entity.User;
import com.hp.webedu.entity.Video;

@Service
public class UserService {
	
	@Resource
	private UserDao userDao;
	
	public String findUser(String nickName,String userPassword)
	{
		List<Object[]> user = userDao.findUser(nickName,userPassword);
		System.out.println(user.size());
		if(user.size()>0)
		{
			Object object = user.get(0)[0];
			return object.toString();
		}
		else 
		{
			return null;
		}
		
	}

	public Page<User> getUserList(String search, String colName, String sortDir,
			int start, int pageSize) {
		search = "%" + (search == null ? "" : search) + "%";
		Sort sort1 = null;
		if (colName == null || "".equals(colName)) {
			colName = "nickName";
		}
		colName = "nickName";
		if ("asc".equalsIgnoreCase(sortDir)) {
			sort1 = new Sort(Direction.ASC, colName);
		} else {
			sort1 = new Sort(Direction.DESC, colName);
		}
		PageRequest pr = new PageRequest(start, pageSize, sort1);
		return userDao.getUserList(pr,search,true);
	}
}
