package com.hp.webedu.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hp.webedu.dao.CourseDao;
import com.hp.webedu.entity.Course;

@Service
public class CourseService {
	
	@Resource
	private CourseDao courseDao;
	
	//anndroid
	public List<Object[]> getAllKindsOfCourseList(String subId,String keyWords){
		return courseDao.getAllKindsOfCourseList( subId, keyWords);
	}
	
	//anndroid
	public List<Object[]> getPopularCourseLimi10() {
		return courseDao.getPopularCourseLimi10();
	}
	
	//anndroid
	public List<Object[]> getCourseAllComment(String courseId) {
		return courseDao.getCourseAllComment(courseId);
	}
	//anndroid
	public List<Object[]> getCourseLimit5() {
		return courseDao.getCourseLimit5();
	}
	
	@Modifying
	@Transactional
	public int deleteCourse(String id) {
		return courseDao.deleteCourse( id);
	}
	
	public Object[] getAllCourseCountBySearch(String serarchContent) {
		return courseDao.getAllCourseCountBySearch(serarchContent,true);
	}
	
	public List<Object[]> getAllCourseInfoBySearch(String serarchContent, Integer start, Integer len) {
		return courseDao.getAllCourseInfoBySearch( serarchContent,  start,  len,true);
	}
	
	public Object[] getAllCourseCount(String subjectId){
		return courseDao.getAllCourseCount(subjectId, true);
	}
	
	public List<Object[]> getAllCourseInfo(String subjectId, Integer start, Integer len) {
		return courseDao.getAllCourseInfo( subjectId,  start,  len,true);
	}
	
	public Page<Course> getCourseList(String search, String colName, String sortDir,
			int start, int pageSize) {
		search = "%" + (search == null ? "" : search) + "%";
		Sort sort1 = null;
		if (colName == null || "".equals(colName)) {
			colName = "courName";
		}
		colName = "courName";
		if ("asc".equalsIgnoreCase(sortDir)) {
			sort1 = new Sort(Direction.ASC, colName);
		} else {
			sort1 = new Sort(Direction.DESC, colName);
		}
		PageRequest pr = new PageRequest(start, pageSize, sort1);
		return courseDao.getCourseList(pr,search,true);
	}

	public void saveCourse(Course course) {
		courseDao.save(course);
	}

	/**
	 * 根据id返回相应的课程
	 * @param course
	 * @return
	 */
	public Course getCourseById(String course) {
		return courseDao.findOne(course);
	}

	public List<Object[]> getAllKindsOfList(String subId) {
		return courseDao.getAllKindsOfList(subId);
	}

}
