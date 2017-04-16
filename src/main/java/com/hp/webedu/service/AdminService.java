package com.hp.webedu.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hp.webedu.dao.AdmainDao;

@Service
public class AdminService {

	@Resource
	AdmainDao admainDao;
	
	/**
	 * 依据用户名，密码查找用户
	 * @param username
	 * @param password
	 * @return
	 */
	public Boolean findAdmin(String username, String password) {
		List<Object[]> adminList=admainDao.findAdmin(username, password);
		if(adminList.size()>0)
		{
			return true;
		}
		return false;
	}

}
