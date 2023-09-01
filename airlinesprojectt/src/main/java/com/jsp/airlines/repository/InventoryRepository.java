package com.jsp.airlines.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jsp.airlines.entity.Inventory;

public interface InventoryRepository extends JpaRepository<Inventory, Integer>{
	@Query("SELECT i1 FROM Inventory i1 WHERE i1.count=:co")
	Inventory findBycount(@Param("co") int count);
}
