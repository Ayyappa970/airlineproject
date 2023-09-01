package com.jsp.airlines.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jsp.airlines.entity.BookingInfo;

public interface BookingInfoRepository extends JpaRepository<BookingInfo, Integer>{
	@Query("SELECT b1 FROM BookingInfo b1 WHERE b1.flightNo=:no")
	BookingInfo findByflightNo(@Param("no") int no);
}
