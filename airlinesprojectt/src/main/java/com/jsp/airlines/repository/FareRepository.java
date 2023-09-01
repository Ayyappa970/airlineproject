package com.jsp.airlines.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jsp.airlines.entity.Fare;

public interface FareRepository extends JpaRepository<Fare, Integer>{
	@Query("SELECT f1 FROM Fare f1 WHERE f1.currency=:cur")
	List<Fare> findBycurrency(@Param("cur") String currency);
	
	@Query("SELECT f1 FROM Fare f1 WHERE f1.currency=:cur AND f1.fareAmount=:am")
	Fare findBycurrencyAndfareAmount(@Param("cur") String currency,@Param("am") double fareAmount);
}
