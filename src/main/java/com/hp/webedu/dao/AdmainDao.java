package com.hp.webedu.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hp.webedu.entity.Admin;

public interface AdmainDao extends JpaRepository<Admin, String>{

	@Query(nativeQuery=true,value="select user_name,password1 from t_admin where user_name=:user_name and password1=:password1")
	List<Object[]> findAdmin(@Param("user_name")String username, @Param("password1")String password);
	
	

}
