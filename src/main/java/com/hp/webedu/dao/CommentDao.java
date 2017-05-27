package com.hp.webedu.dao;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.hp.webedu.entity.Comment;

public interface CommentDao extends JpaRepository<Comment, String>{

	@Modifying
	@Transactional
	@Query(nativeQuery=true,value="update t_comment set state=0 where id=:id")
	int deleteComm(@Param("id")String id);
	
	
	@Query("from Comment com where com.content like :search and com.state=:state")
	Page<Comment> getCommList(Pageable pr,@Param("search") String search, @Param("state")Boolean state);
	
	@Query(nativeQuery=true,value="select us.nick_name as nick_name,com.content as content,com.com_time as com_time from t_comment com left join t_user us on us.id=com.user_id "
			+ "where com.video_id =:videoId and com.state=:state order by com_time desc")
	List<Object[]> getCommentList(@Param("videoId")String videoId, @Param("state")Boolean state);
	
	@Modifying
	@Transactional
	@Query(nativeQuery=true,value="insert into t_comment(id,user_id,video_id,content,state,com_time) values(:id,:userId,:videoId,:content,:state,:com_time) ")
	int saveComment(@Param("videoId")String videoId, @Param("content")String content, @Param("userId")String userId
			,@Param("id")String id,@Param("state")Boolean state,@Param("com_time")Timestamp com_time);

}
