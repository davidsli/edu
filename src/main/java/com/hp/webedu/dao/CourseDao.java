package com.hp.webedu.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.hp.webedu.entity.Course;

public interface CourseDao extends JpaRepository<Course, String>{
	
	@Modifying
	@Transactional
	@Query(nativeQuery=true,value="update t_course set state=0 where id =:id")
	int deleteCourse(@Param("id")String id);
	
	//anndroid
	@Query(nativeQuery=true,value="select a1.id as id,a1.cour_name as cour_name,a1.img_url as img_url,sum(cast(a2.score as signed  Integer)) as score,count(distinct(a1.videoid)) as su,a1.school as school "+
			   "from (select aaa.id as id,aaa.cour_name as cour_name,aaa.img_url as img_url,bbb.id as videoid,aaa.school as school from "+ 
				"(select ttt.id as id,ttt.cour_name as cour_name,ttt.img_url as img_url,ttt.school as school from t_course ttt "+
				"where ttt.sub_id like %:subId% and ttt.cour_name like %:keyWords% and ttt.state=1) aaa left join( select vi.id as id,vi.cour_id as cour_id from t_video vi where vi.state=1) bbb "+
				"on aaa.id=bbb.cour_id  ) a1  left join t_score a2 on a1.videoid=a2.video_id group by a1.id,a1.cour_name,a1.img_url order by score desc")
	List<Object[]> getAllKindsOfCourseList(@Param("subId")String subId, @Param("keyWords")String keyWords);

	
	//anndroid
	@Query(nativeQuery=true,value="select co.id as id,co.img_url as img_url,co.cour_name as cour_name from t_course co where co.state=1  ORDER BY  RAND() limit 0,10")
	List<Object[]> getPopularCourseLimi10();
	
	//anndroid
//	select us.head_image as head_image,us.nick_name as nick_name,a2.com_time as com_time,a2.content as content from (
//			select com.com_time as com_time,com.content as content,com.user_id as user_id from
//			(select vi.id as id from t_video vi where vi.cour_id ='' and vi.state=1) a1 left join t_comment com on a1.id=com.video_id where com.state=1
//			) a2 left join t_user us on a2.user_id=us.id
	@Query(nativeQuery=true,value="select us.head_image as head_image,us.nick_name as nick_name,a2.com_time as com_time,a2.content as content from ( "+
"select com.com_time as com_time,com.content as content,com.user_id as user_id from "+
"(select vi.id as id from t_video vi where vi.cour_id =:courseId and vi.state=1) a1 left join t_comment com on a1.id=com.video_id where com.state=1"+
") a2 left join t_user us on a2.user_id=us.id order by com_time desc")
	List<Object[]> getCourseAllComment(@Param("courseId")String courseId);
	
	//anndroid
	@Query(nativeQuery=true,value="select co.id as id,co.cour_name as cour_name from t_course co where co.state=1 limit 0,5")
	List<Object[]> getCourseLimit5();
	
	@Query(nativeQuery=true,value="select count(co.id) as courCount "
			+ " from t_course co where co.cour_name like %:serarchContent% and co.state =:state")
	Object[] getAllCourseCountBySearch(@Param("serarchContent")String serarchContent, @Param("state")Boolean state);
	
	@Query(nativeQuery=true,value="select co.id as id,co.cour_name as cour_name,co.teacher_name as teacher_name,co.school as school,co.cour_intro as cour_intro,co.img_url as img_url "
			+ " from t_course co where co.cour_name like %:serarchContent% and co.state =:state limit :start,:len")
	List<Object[]> getAllCourseInfoBySearch(@Param("serarchContent")String serarchContent,@Param("start") Integer start, @Param("len")Integer len,@Param("state")Boolean state);
	
	
	@Query(nativeQuery=true,value="select count(co.id) as courCount "
			+ " from t_course co where co.sub_id =:subjectId and co.state =:state")
	Object[] getAllCourseCount(@Param("subjectId")String subjectId,@Param("state")Boolean state);
	
	//查找科目下面的课程
	@Query(nativeQuery=true,value="select co.id as id,co.cour_name as cour_name,co.teacher_name as teacher_name,co.school as school,co.cour_intro as cour_intro,co.img_url as img_url "
			+ " from t_course co where co.sub_id =:subjectId and co.state =:state limit :start,:len")
	List<Object[]> getAllCourseInfo(@Param("subjectId")String subjectId,@Param("start") Integer start, @Param("len")Integer len,@Param("state")Boolean state);
	

//	select a1.id as id,a1.cour_name as cour_name,a1.img_url as img_url,sum(cast(a2.score as signed  Integer)) as score,count(distinct(a1.videoid)) as su
//	from (select aaa.id as id,aaa.cour_name as cour_name,aaa.img_url as img_url,bbb.id as videoid from 
//		(select ttt.id as id,ttt.cour_name as cour_name,ttt.img_url as img_url from t_course ttt 
//		where ttt.sub_id='297e386a56410ddd0156411a94c50005' and ttt.state=1) aaa left join( select vi.id as id,vi.cour_id as cour_id from t_video vi where vi.state=1) bbb
//		on aaa.id=bbb.cour_id  ) a1  left join t_score a2 on a1.videoid=a2.video_id group by a1.id,a1.cour_name,a1.img_url;
	
	@Query(nativeQuery=true,value="select a1.id as id,a1.cour_name as cour_name,a1.img_url as img_url,sum(cast(a2.score as signed  Integer)) as score,count(distinct(a1.videoid)) as su,a1.school as school "+
   "from (select aaa.id as id,aaa.cour_name as cour_name,aaa.img_url as img_url,bbb.id as videoid,aaa.school as school from "+ 
	"(select ttt.id as id,ttt.cour_name as cour_name,ttt.img_url as img_url,ttt.school as school from t_course ttt "+
	"where ttt.sub_id=:subId and ttt.state=1) aaa left join( select vi.id as id,vi.cour_id as cour_id from t_video vi where vi.state=1) bbb "+
	"on aaa.id=bbb.cour_id  ) a1  left join t_score a2 on a1.videoid=a2.video_id group by a1.id,a1.cour_name,a1.img_url order by score desc limit 0,10;")
	List<Object[]> getAllKindsOfList(@Param("subId")String subId);
	
	@Query("from Course cor where cor.courName like :search and cor.state=:state")
	Page<Course> getCourseList(Pageable pr, @Param("search")String search, @Param("state")Boolean state);


}
