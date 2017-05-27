package com.hp.webedu.service;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hp.webedu.dao.UserDao;
import com.hp.webedu.entity.User;
import com.hp.webedu.entity.Video;

@Service
public class UserService {
	
	@Resource
	private UserDao userDao;
	
	//android
	public List<Object[]> getnickNameAndImg(String userID) {
		return userDao.getnickNameAndImg( userID);
	}
	
	//android
	@Modifying
	@Transactional
	public int updateUserByPhone(String phone, String pwd) {
		return userDao.updateUserByPhone( phone,  pwd);
	}
	
	//android
	@Modifying
	@Transactional
	public int insetAndroidUser(String uuid, String phone, String pwd) {
		return userDao.insetAndroidUser( uuid,  phone,  pwd);
	}
	
	//android
	public List<Object[]> findUserByPhoneAndPassword(String phone, String pwd) {
		return userDao.findUserByPhoneAndPassword( phone,  pwd);
	}
	
	//android
	public List<Object[]> getUserByPhone(String phone) {
		return userDao.getUserByPhone( phone);
	}
	
	public List<Object[]> getUserByEmail(String email) {
		return userDao.getUserByEmail( email);
	}
	
	@Modifying
	@Transactional
	public int updateUserById(String id) {
		return userDao.updateUserById(id);
	}
	
	public List<Object[]> finUserName(String nickName) {
		return userDao.finUserName( nickName);
	}
	
	@Modifying
	@Transactional
	public int deleteUser(String id) {
		return userDao.deleteUser( id);
	}
	
	@Modifying
	@Transactional
	public int updateUserPassword(String userId, String newpsw) {
		return userDao.updateUserPassword( userId,  newpsw);
	}
	
	public List<Object[]> checkIsReg(String email) {
		return userDao.checkIsReg(email);
	}
	
	public User saveUser(User user) {
		return userDao.save(user);
	}
	
	public User getUserById(String userId) {
		return userDao.findOne(userId);
	}
	
	public List<Object[]> findUser(String nickName,String userPassword)
	{
		return userDao.findUser(nickName,userPassword);
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
