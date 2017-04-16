package com.hp.webedu.service;

import java.sql.Timestamp;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hp.webedu.dao.CommentDao;

@Service
public class CommentService {
	
	@Resource
	private CommentDao commentDao;

	public List<Object[]> getCommentList(String videoId) {
		return commentDao.getCommentList( videoId,true);
	}
	
	@Modifying
	@Transactional
	public Boolean saveComment(String videoId, String content, String userId,String uuid,Boolean state,Timestamp timestamp) {
		int kk=commentDao.saveComment( videoId,  content,  userId, uuid, state, timestamp);
		return kk>0;
	}

}
