package com.hp.webedu.service;

import java.sql.Timestamp;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hp.webedu.dao.CommentDao;
import com.hp.webedu.entity.Comment;

@Service
public class CommentService {
	
	@Resource
	private CommentDao commentDao;

	@Modifying
	@Transactional
	public int deleteComm(String id) {
		return commentDao.deleteComm( id);
	}

	public Page<Comment> getCommList(String search, String colName, String sortDir, int start, int pageSize) {
		search = "%" + (search == null ? "" : search) + "%";
		Sort sort1 = null;
		if (colName == null || "".equals(colName)) {
			colName = "content";
		}
		colName = "content";
		if ("asc".equalsIgnoreCase(sortDir)) {
			sort1 = new Sort(Direction.ASC, colName);
		} else {
			sort1 = new Sort(Direction.DESC, colName);
		}
		PageRequest pr = new PageRequest(start, pageSize, sort1);
		return commentDao.getCommList(pr,search,true);
	}
	
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
