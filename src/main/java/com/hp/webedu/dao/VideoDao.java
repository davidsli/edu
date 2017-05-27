package com.hp.webedu.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.hp.webedu.entity.Course;
import com.hp.webedu.entity.Subject;
import com.hp.webedu.entity.Video;

public interface VideoDao extends JpaRepository<Video, String>{
	
	@Modifying
	@Transactional
	@Query(nativeQuery=true,value="update t_video set state=0 where id=:id")
	int deleteVideo(@Param("id")String id);
	
	//android
	@Query(nativeQuery=true,value="select vi.id as id,vi.video_name as video_name,vi.video_desc as video_desc,vi.thumb as thumb,vi.chapter as chapter,vi.time_long as time_long,vi.size as size "
			+ "from t_video vi where vi.cour_id=:courseId and vi.state=:state order by chapter asc")
	List<Object[]> getVideoListByDesc(@Param("courseId")String courseId, @Param("state")Boolean state);
	
	//android
	@Query(nativeQuery=true,value="select sum(cast(sc.score as signed  Integer)) as sum from (select vi.id as id from t_video vi where vi.cour_id=:courseId and vi.state=1) a1 left join "
			+ "t_score sc on sc.video_id=a1.id")
	Object getCourseScore(@Param("courseId")String courseIdD);
		
	//android
	@Query(nativeQuery=true,value="select sum(cast(sc.score as signed  Integer)) as sum from (select vi.id as id from t_video vi where vi.cour_id=:courseId and vi.state=1) a1 left join "
			+ "t_score sc on sc.video_id=a1.id where sc.user_id=:userID")
	Object getCourseScore(@Param("courseId")String courseId, @Param("userID")String userID);
	
	/**
	 * 查询课程下面的视频信息
	 * @param courseId
	 * @param state
	 * @return
	 */
	@Query(nativeQuery=true,value="select vi.id as id,vi.video_name as video_name,vi.video_desc as video_desc,vi.thumb as thumb,vi.chapter as chapter "
			+ "from t_video vi where vi.cour_id=:courseId and vi.state=:state order by chapter asc")
	List<Object[]> getVideoList(@Param("courseId")String courseId, @Param("state")Boolean state);
	
	/**
	 * 依据课程号，视频集数查询视频的文件名
	 * @param courseId
	 * @param videoNo
	 * @param b
	 * @return
	 */
	@Query(nativeQuery=true,value="select vi.id as id,vi.video_name as video_name from t_video vi "
			+ "where vi.cour_id=:courseId and vi.state=:state and vi.chapter =:chapter")
	List<Object[]> getVideo(@Param("courseId")String courseId,@Param("chapter") String videoNo, @Param("state")Boolean state);
	
	/**
	 * 查询所有可见的视频
	 * @param state
	 * @return
	 */
	@Query("from Course vi where vi.state=:state")
	List<Course> findAllCourse(@Param("state")Boolean state);
	
	
	/**
	 * 显示学科列表，加上分页，搜索条件
	 * @param pr
	 * @param search
	 * @param state
	 * @return
	 */
	@Query("from Video vi where vi.videoDesc like :search and vi.state=:state")
	Page<Video> getVideotList(Pageable pr, @Param("search")String search, @Param("state")Boolean state);

}
