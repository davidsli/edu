package com.hp.webedu.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.hp.webedu.dao.CourseDao;
import com.hp.webedu.entity.Course;

@Service
public class CourseService {
	
	@Resource
	private CourseDao courseDao;
	
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
