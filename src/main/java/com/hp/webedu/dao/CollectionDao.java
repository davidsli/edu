package com.hp.webedu.dao;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.hp.webedu.entity.Collection;

public interface CollectionDao extends JpaRepository<Collection, String>{
	
	@Modifying
	@Transactional
	@Query(nativeQuery=true,value="insert into t_collection(id,user_id,cour_id,coll_time,state) values(:uuid,:userId,:courseId,:timeStamps,:state)")
	int insertCollInfo(@Param("uuid")String uuid, @Param("courseId")String courseId, @Param("userId")String userId, @Param("state")Integer state, @Param("timeStamps")Timestamp timeStamps);
	
	@Modifying
	@Transactional
	@Query(nativeQuery=true,value="update t_collection set state=:state where cour_id=:courseId and user_id=:userId")
	int updateCollectionInfo(@Param("userId")String userId, @Param("courseId")String courseId, @Param("state")Integer state);
	
	@Query(nativeQuery=true,value="select col.state as state from t_collection col where col.user_id=:userId and cour_id= :courseId")
	List<Boolean> getCollectionByCourseIdUserId(@Param("userId")String userId, @Param("courseId")String courseId);
	
	@Query(nativeQuery=true,value="	select count(*) as num "+
			"from(select col.cour_id as cour_id,col.coll_time as coll_time "+
			 "from t_collection col where col.user_id=:userId and col.state=1)"
			 + " a1 left join t_course cou on a1.cour_id=cou.id where cou.state=1")
	Object[] getCollCourseCount(@Param("userId")String userId);

	@Query(nativeQuery=true,value="	select a1.cour_id as id,a1.coll_time as coll_time,cou.img_url as img_url,cou.cour_name as cour_name,cou.teacher_name as teacher_name,"+
		"cou.school as school,cou.cour_intro as cour_intro "+
		"from(select col.cour_id as cour_id,col.coll_time as coll_time "+
		 "from t_collection col where col.user_id=:userId and col.state=1)"
		 + " a1 left join t_course cou on a1.cour_id=cou.id where cou.state=1 limit :start,:len")
	List<Object[]> getCollCourse(@Param("userId")String userId, @Param("start")Integer start, @Param("len")Integer len);

}
