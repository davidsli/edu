package com.hp.webedu.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hp.webedu.entity.Admin;
import com.hp.webedu.entity.Subject;

public interface SubjectDao extends JpaRepository<Subject, String>{

	/**
	 * 显示学科列表，加上分页，搜索条件
	 * @param pr
	 * @param search
	 * @param state
	 * @return
	 */
	@Query("from Subject sub where sub.subName like :search and sub.state=:state")
	Page<Subject> getSubjectList(Pageable pr, @Param("search")String search, @Param("state")Boolean state);

}
