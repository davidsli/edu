package com.hp.webedu.service;

import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import com.hp.webedu.dao.ScoreDao;

@Service
public class ScoreService {

	@Resource
	private ScoreDao scoreDao;
	
	//android
	@Modifying
	@Transactional
	public int updateScoreByUserIdAndVideoId(String userID, String videoID, String score) {
		return scoreDao.updateScoreByUserIdAndVideoId( userID,  videoID,  score);
	}
	
	//android
	@Modifying
	@Transactional
	public int insertScore(String uuid, String videoID, String score, String userID) {
		return scoreDao.insertScore( uuid,  videoID,  score,  userID);
	}
	
	public List<Object[]> getUserScore(String userId, String videoId) {
		return scoreDao.getUserScore( userId,  videoId);
	}
	
	public int saveVideoScore(String id, String userId, String videoId, String score) {
		return scoreDao.saveVideoScore( id,  userId,  videoId,  score);
	}

}
