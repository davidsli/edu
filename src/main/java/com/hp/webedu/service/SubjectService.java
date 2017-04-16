package com.hp.webedu.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.hp.webedu.dao.SubjectDao;
import com.hp.webedu.entity.Subject;

@Service
public class SubjectService {

	@Resource
	private SubjectDao subjectDao;
	
	public void saveSubject(Subject subject) {
		subjectDao.save(subject);
	}
	
	public Page<Subject> getSubjectList(String search, String colName, String sortDir,
			int start, int pageSize) {
		search = "%" + (search == null ? "" : search) + "%";
		Sort sort1 = null;
		if (colName == null || "".equals(colName)) {
			colName = "subName";
		}
		colName = "subName";
		if ("asc".equalsIgnoreCase(sortDir)) {
			sort1 = new Sort(Direction.ASC, colName);
		} else {
			sort1 = new Sort(Direction.DESC, colName);
		}
		PageRequest pr = new PageRequest(start, pageSize, sort1);
		return subjectDao.getSubjectList(pr,search,true);
	}

	public Subject getSubjectById(String id) {
		return subjectDao.getOne(id);
	}

	public List<Subject> findAllSubject() {
		return subjectDao.findAll();
	}
	
}
