package com.jsp.airlines.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.airlines.dto.FlightDTO;
import com.jsp.airlines.service.FlightService;

@RestController
@RequestMapping("api/airline/flight")
public class FlightController {
	@Autowired
	private FlightService flightService;
	@GetMapping("/get/{loc}/{des}/{date}")
	public ResponseEntity getFlightDetails(@PathVariable("loc") String currentLoc,@PathVariable("des") String destination,@PathVariable("date")LocalDate flightDate) {
		FlightDTO dto = flightService.searchFlight(currentLoc, destination, flightDate);
		if (dto!=null) {
			return ResponseEntity.status(HttpStatus.FOUND).body(dto);
		} else {
			return ResponseEntity.status(HttpStatus.FOUND).body("No data Found");
		}
	}
}
