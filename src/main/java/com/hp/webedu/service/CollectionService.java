package com.hp.webedu.service;

import java.sql.Timestamp;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hp.webedu.dao.CollectionDao;

@Service
public class CollectionService {

	@Resource
	private CollectionDao collectionDao;
	
	@Modifying
	@Transactional
	public int insertCollInfo(String uuid, String courseId, String userId, int i, Timestamp timeStamps) {
		return collectionDao.insertCollInfo( uuid,  courseId,  userId,  i,  timeStamps);
	}
	
	@Modifying
	@Transactional
	public int updateCollectionInfo(String userId, String courseId, Integer state) {
		return collectionDao.updateCollectionInfo( userId,  courseId,  state);
	}
	
	public List<Boolean> getCollectionByCourseIdUserId(String userId, String courseId) {
		return collectionDao.getCollectionByCourseIdUserId( userId,  courseId);
	}
	
	public Object[] getCollCourseCount(String userId){
		return collectionDao.getCollCourseCount( userId);
	}
	
	public List<Object[]> getCollCourse(String userId, Integer start, Integer len) {
		return collectionDao.getCollCourse( userId,start, len);
	}

}
