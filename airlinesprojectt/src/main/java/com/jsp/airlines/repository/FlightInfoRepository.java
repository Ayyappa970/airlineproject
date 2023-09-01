package com.jsp.airlines.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jsp.airlines.entity.BookingInfo;
import com.jsp.airlines.entity.FlightInfo;

public interface FlightInfoRepository extends JpaRepository<FlightInfo, Integer>{
//	@Query("DELETE FROM FlightInfo f1 WHERE f1.flightInfoId=:id")
//	FlightInfo deleteByflightInfoId(@Param("id") int id);
	
	@Query("SELECT f1 FROM FlightInfo f1 WHERE f1.flightType=:info")
	List<FlightInfo> findByflightType(@Param("info")String flightType);
	
	@Query("SELECT f1 FROM FlightInfo f1 WHERE f1.flightNo=:no")
	FlightInfo findByflightNo(@Param("no") String no);
}
