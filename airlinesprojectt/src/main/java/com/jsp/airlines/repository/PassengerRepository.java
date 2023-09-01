package com.jsp.airlines.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jsp.airlines.entity.Passenger;

public interface PassengerRepository extends JpaRepository<Passenger, Integer>{
	
	@Query("SELECT p1 FROM Passenger p1 WHERE p1.emailId=:email AND p1.moileNum=:mb")
	Passenger findByemailIdAndmoileNum(@Param("email") String emailId,@Param("mb") String moileNum);
	
	@Query("SELECT p1 FROM Passenger p1 WHERE p1.bookingInfo.bookingId=:id")
	Passenger findBybookingInfo(@Param("id") int id);
	
	@Query("SELECT p1 FROM Passenger p1 WHERE p1.checkIn.checkInId=:idd")
	Passenger findBycheckIn(@Param("idd") int id);
}
