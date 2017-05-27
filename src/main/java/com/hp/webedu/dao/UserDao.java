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

import com.hp.webedu.entity.User;
import com.hp.webedu.entity.Video;

public interface UserDao extends JpaRepository<User, String>{
	
	//android
	@Query(nativeQuery=true,value="select us.nick_name as nick_name,us.head_image as head_image from t_user us where us.id=:userID")
	List<Object[]> getnickNameAndImg(@Param("userID")String userID);
	
	//android
	@Modifying
	@Transactional
	@Query(nativeQuery=true,value="update t_user set user_password=:pwd where phone_number=:phone")
	int updateUserByPhone(@Param("phone")String phone, @Param("pwd")String pwd);
	
	@Query(nativeQuery=true,value="select us.id as id,us.head_image as head_image from t_user us where us.email=:email")
	List<Object[]> getUserByEmail(@Param("email")String email);
	
	//账号激活
	@Modifying
	@Transactional
	@Query(nativeQuery=true,value="update t_user set state=1 where id=:id")
	int updateUserById(@Param("id")String id);
	
	
	@Query(nativeQuery=true,value="select us.id as id,us.nick_name as nick_name from t_user us where (us.nick_name=:nick_name or us.email=:nick_name or us.phone_number=:nick_name)")
	List<Object[]> finUserName(@Param("nick_name")String nickName);
	
	@Modifying
	@Transactional
	@Query(nativeQuery=true,value="update t_user set state=0 where id=:id")
	int deleteUser(@Param("id")String id);
	
	@Modifying
	@Transactional
	@Query(nativeQuery=true,value="insert into t_user(id,phone_number,user_password,state) values(:uuid,:phone,:pwd,1)")
	int insetAndroidUser(@Param("uuid")String uuid,@Param("phone") String phone, @Param("pwd")String pwd);
	
	//android
	@Query(nativeQuery=true,value="select us.id as id,us.phone_number as phone_number from t_user us where us.phone_number =:phone and us.user_password =:pwd")
	List<Object[]> findUserByPhoneAndPassword(@Param("phone")String phone, @Param("pwd")String pwd);
	
	//android
	@Query(nativeQuery=true,value="select us.id as id from t_user us where us.phone_number =:phone")
	List<Object[]> getUserByPhone(@Param("phone")String phone);
	
	@Modifying
	@Transactional
	@Query(nativeQuery=true,value="update t_user set user_password =:newpsw where id=:userId")
	int updateUserPassword(@Param("userId")String userId, @Param("newpsw")String newpsw);
	
	@Query(nativeQuery=true,value="select us.id as id from t_user us where us.email=:email")
	List<Object[]> checkIsReg(@Param("email")String email);
	
	//用户登陆
	@Query(nativeQuery=true,value="select id,nick_name,user_password from t_user where user_password =:user_password and "
			+ " (nick_name=:nick_name or email=:nick_name or phone_number=:nick_name) and state=1")
	List<Object[]> findUser(@Param("nick_name")String nickName,@Param("user_password")String userPassword);

	
	@Query("from User us where us.nickName like :search and us.state=:state")
	Page<User> getUserList(Pageable pr, @Param("search")String search, @Param("state")Boolean state);

}
