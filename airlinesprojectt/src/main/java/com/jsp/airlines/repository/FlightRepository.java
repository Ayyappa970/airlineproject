package com.jsp.airlines.repository;



import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jsp.airlines.dto.FlightDTO;
import com.jsp.airlines.entity.Flight;
import com.jsp.airlines.entity.FlightInfo;
import com.jsp.airlines.entity.Passenger;

public interface FlightRepository extends JpaRepository<Flight, Integer>{
	@Query("SELECT f1 FROM Flight f1 WHERE f1.currentLoc=:cloc AND f1.destination=:des AND f1.flightDate=:ldate")
	Flight findFlight(@Param("cloc") String currentLoc,@Param("des") String destination,@Param("ldate") LocalDate flightDate);
	
	@Query("SELECT f1 FROM Flight f1 WHERE f1.fare.fareId=:id")
	Flight findByfareId(@Param("id") int id);
	
	@Query("SELECT f1 FROM Flight f1 WHERE f1.flightInfo.flightInfoId=:id1")
	Flight findByflightInfoId(@Param("id1") int id);
	
	@Query("SELECT f1 FROM Flight f1 WHERE f1.flightInfo.airlinesInfo.airlineName=:name")
	List<FlightDTO> findByflightairlineName(@Param("name") String name);
	
	@Query("SELECT f1 FROM Flight f1 WHERE f1.flightNo=:no")
	Flight findByflightNo(@Param("no") String no);
	
	@Query("SELECT f1 FROM Flight f1 WHERE f1.currentLoc=:loc AND f1.destination=:des AND f1.flightDate=:fd")
	List<Flight> findBycurrentLocAnddestinationAndflightDate(@Param("loc") String currentLoc,@Param("des") String destination,@Param("fd") LocalDate flightDate);
	
	@Query("SELECT f1 FROM Flight f1 WHERE f1.currentLoc=:loc AND f1.destination=:des AND f1.flightDate=:fd AND f1.flightTime=:ft")
	List<Flight> findBycurrentLocAnddestinationAndflightDateAndflightTime(@Param("loc") String currentLoc,@Param("des") String destination,
			@Param("fd") LocalDate flightDate,@Param("ft") LocalTime flightTime);
}
