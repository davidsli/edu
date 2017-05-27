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
	public List<Object[]> findAdmin(String username, String password) {
		return admainDao.findAdmin(username, password);
	}

}
