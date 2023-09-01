package com.jsp.airlines.service;

import java.time.LocalDate;
import java.util.List;

import com.jsp.airlines.dto.FlightDTO;
import com.jsp.airlines.entity.Flight;

public interface FlightService {
	int addFlight(FlightDTO dto);
	FlightDTO getFlightById(int id);
	FlightDTO getFlightByFareId(int id);
	FlightDTO getFlightByFlightInfoId(int id);
	List<FlightDTO> getFlightByAirlineName(String name);
	FlightDTO searchByflightNo(String flightNo);
	List<FlightDTO> getAllFlightDetails();
	//FlightDTO searchFlightBycurrentLocAnddestinationAndflightDate(String currentLoc,String destination,LocalDate flightDate);
	Flight updateFlight(int id,FlightDTO dto);
	int deleteFlight(int id);
}
