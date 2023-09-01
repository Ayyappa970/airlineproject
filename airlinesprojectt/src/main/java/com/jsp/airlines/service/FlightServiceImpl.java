package com.jsp.airlines.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jsp.airlines.dto.AirlinesInfoDTO;
import com.jsp.airlines.dto.FareDTO;
import com.jsp.airlines.dto.FlightDTO;
import com.jsp.airlines.dto.FlightInfoDTO;
import com.jsp.airlines.dto.InventoryDTO;
import com.jsp.airlines.entity.Flight;
import com.jsp.airlines.repository.FlightInfoRepository;
import com.jsp.airlines.repository.FlightRepository;
@Service
public class FlightServiceImpl implements FlightService{
	
	@Autowired
	private FlightRepository flightRepository;
	@Override
	public FlightDTO searchFlight(String currentLoc,String destination,LocalDate flightDate) {
		Flight flight = flightRepository.findFlight(currentLoc, destination, flightDate);
		if (flight!=null) {
			FlightDTO dto = FlightDTO.builder().currentLoc(flight.getCurrentLoc()).destination(flight.getDestination())
			.flightDate(flight.getFlightDate()).flightNo(flight.getFlightNo()).flightTime(flight.getFlightTime())
			.fareDTO(FareDTO.builder().fareAmount(flight.getFare().getFareAmount()).currency(flight.getFare().getCurrency()).build())
			.flightInfoDTO(FlightInfoDTO.builder().flightNo(flight.getFlightInfo().getFlightNo()).flightTime(flight.getFlightInfo().getFlightTime()).flightType(flight.getFlightInfo().getFlightType())
			.airlinesInfo(AirlinesInfoDTO.builder().airlineName(flight.getFlightInfo().getAirlinesInfo().getAirlineName()).build()).build())
			.inventoryDTO(InventoryDTO.builder().count(flight.getInventories().getCount()).build()).build();
			return dto;
		} else {
			return null;
		}
		
	}

}
