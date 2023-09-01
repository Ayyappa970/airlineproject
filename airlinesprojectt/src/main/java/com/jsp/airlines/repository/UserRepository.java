package com.jsp.airlines.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jsp.airlines.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	@Query("SELECT u1 FROM User u1 WHERE u1.userName=:name AND u1.password=:password")
	User findByuserNameAndpassword(@Param("name")String userName,@Param("password")String password);
	
//	@Query("UPDATE User u1 SET u1.password=:pass WHERE u1.userId=:id")
//	User updatePasswordById(@Param("id") int id,@Param("pass") String password);
	User findBypassword(String password);
}
