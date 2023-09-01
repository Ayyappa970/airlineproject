package com.jsp.airlines.service;

import java.time.LocalDate;

import com.jsp.airlines.dto.FlightDTO;

public interface FlightService {
	FlightDTO searchFlight(String currentLoc,String destination,LocalDate flightDate);
}
