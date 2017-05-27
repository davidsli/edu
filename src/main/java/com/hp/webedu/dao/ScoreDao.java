package com.hp.webedu.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.hp.webedu.entity.Admin;
import com.hp.webedu.entity.Score;

public interface ScoreDao extends JpaRepository<Score, String>{

	//android
	@Modifying
	@Transactional
	@Query(nativeQuery=true,value="update t_score set score=:score where user_id=:userID and video_id=:videoID")
	int updateScoreByUserIdAndVideoId(@Param("userID")String userID, @Param("videoID")String videoID, @Param("score")String score);

	
	//android
	@Modifying
	@Transactional
	@Query(nativeQuery=true,value="insert into t_score(id,user_id,video_id,score) values(:id,:userID,:videoID,:score)")
	int insertScore(@Param("id")String id, @Param("videoID")String videoID, @Param("score")String score, @Param("userID")String userID);
	
	@Query(nativeQuery=true,value="select sc.id from t_score sc where sc.user_id =:userId and sc.video_id =:videoId")
	List<Object[]> getUserScore(@Param("userId")String userId, @Param("videoId")String videoId);
	
	@Modifying
	@Transactional
	@Query(nativeQuery=true,value="insert into t_score(id,user_id,video_id,score) values(:id,:userId,:videoId,:score)")
	int saveVideoScore(@Param("id")String id, @Param("userId")String userId, @Param("videoId")String videoId, @Param("score")String score);

}
