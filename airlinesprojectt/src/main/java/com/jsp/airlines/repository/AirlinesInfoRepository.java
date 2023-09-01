package com.jsp.airlines.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jsp.airlines.entity.AirlinesInfo;

public interface AirlinesInfoRepository extends JpaRepository<AirlinesInfo, Integer>{

	@Query("SELECT a1 FROM AirlinesInfo a1 WHERE  a1.airlineName =:name")
	AirlinesInfo findByairlineName(@Param("name") String name);
}
