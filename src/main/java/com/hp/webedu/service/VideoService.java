package com.hp.webedu.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hp.webedu.dao.VideoDao;
import com.hp.webedu.entity.Course;
import com.hp.webedu.entity.Subject;
import com.hp.webedu.entity.Video;

@Service
public class VideoService {
	
	@Resource
	private VideoDao videoDao;
	
	//android
	public List<Object[]> getVideoListByDesc(String courseId) {
		return videoDao.getVideoListByDesc(courseId,true);
	}
	//android
	public Object getCourseScore(String courseId) {
		return videoDao.getCourseScore( courseId);
	}
	//android
	public Object getCourseScore(String courseId, String userID) {
		return videoDao.getCourseScore( courseId,userID);
	}
	
	@Modifying
	@Transactional
	public int deleteVideo(String id) {
		return videoDao.deleteVideo( id);
	}

	
	public Video getVideoById(String videoId) {
		return videoDao.findOne(videoId);
	}
	
	public List<Object[]> getVideo(String courseId, String videoNo) {
		List<Object[]> objList= videoDao.getVideo(courseId,videoNo,true);
		return objList;
	}
	
	public List<Object[]> getVideoList(String courseId) {
		return videoDao.getVideoList(courseId,true);
	}
	
	public Page<Video> getVideoList(String search, String colName, String sortDir,
			int start, int pageSize) {
		search = "%" + (search == null ? "" : search) + "%";
		Sort sort1 = null;
		if (colName == null || "".equals(colName)) {
			colName = "videoDesc";
		}
		colName = "videoDesc";
		if ("asc".equalsIgnoreCase(sortDir)) {
			sort1 = new Sort(Direction.ASC, colName);
		} else {
			sort1 = new Sort(Direction.DESC, colName);
		}
		PageRequest pr = new PageRequest(start, pageSize, sort1);
		return videoDao.getVideotList(pr,search,true);
	}

	public List<Course> findAllCourse() {
		return videoDao.findAllCourse(true);
	}

	public void saveVideo(Video video) {
		videoDao.save(video);
	}
}
