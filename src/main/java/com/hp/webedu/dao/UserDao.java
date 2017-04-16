package com.hp.webedu.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hp.webedu.entity.User;
import com.hp.webedu.entity.Video;

public interface UserDao extends JpaRepository<User, String>{
	
	//用户登陆
	@Query(nativeQuery=true,value="select id,nick_name,user_password from t_user where user_password =:user_password and nick_name=:nick_name")
	List<Object[]> findUser(@Param("nick_name")String nickName,@Param("user_password")String userPassword);

	
	@Query("from User us where us.nickName like :search and us.state=:state")
	Page<User> getUserList(Pageable pr, @Param("search")String search, @Param("state")Boolean state);

}
