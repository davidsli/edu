package com.hp.webedu.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hp.webedu.entity.Course;

public interface CourseDao extends JpaRepository<Course, String>{

//	select a1.id as id,a1.cour_name as cour_name,a1.img_url as img_url,sum(cast(a2.score as signed  Integer)) as score,count(distinct(a1.videoid)) as su
//	from (select aaa.id as id,aaa.cour_name as cour_name,aaa.img_url as img_url,bbb.id as videoid from 
//		(select ttt.id as id,ttt.cour_name as cour_name,ttt.img_url as img_url from t_course ttt 
//		where ttt.sub_id='297e386a56410ddd0156411a94c50005' and ttt.state=1) aaa left join( select vi.id as id,vi.cour_id as cour_id from t_video vi where vi.state=1) bbb
//		on aaa.id=bbb.cour_id  ) a1  left join t_score a2 on a1.videoid=a2.video_id group by a1.id,a1.cour_name,a1.img_url;
	
	@Query(nativeQuery=true,value="select a1.id as id,a1.cour_name as cour_name,a1.img_url as img_url,sum(cast(a2.score as signed  Integer)) as score,count(distinct(a1.videoid)) as su "+
   "from (select aaa.id as id,aaa.cour_name as cour_name,aaa.img_url as img_url,bbb.id as videoid from "+ 
	"(select ttt.id as id,ttt.cour_name as cour_name,ttt.img_url as img_url from t_course ttt "+
	"where ttt.sub_id=:subId and ttt.state=1) aaa left join( select vi.id as id,vi.cour_id as cour_id from t_video vi where vi.state=1) bbb "+
	"on aaa.id=bbb.cour_id  ) a1  left join t_score a2 on a1.videoid=a2.video_id group by a1.id,a1.cour_name,a1.img_url;")
	List<Object[]> getAllKindsOfList(@Param("subId")String subId);
	
	@Query("from Course cor where cor.courName like :search and cor.state=:state")
	Page<Course> getCourseList(Pageable pr, @Param("search")String search, @Param("state")Boolean state);

}
