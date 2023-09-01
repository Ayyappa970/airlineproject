package com.jsp.airlines.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jsp.airlines.entity.CheckIn;

public interface CheckInRepository extends JpaRepository<CheckIn, Integer>{
	@Query("SELECT c1 FROM CheckIn c1 WHERE c1.seatNo=:no")
	CheckIn findByseatNo(@Param("no") int id);
}
