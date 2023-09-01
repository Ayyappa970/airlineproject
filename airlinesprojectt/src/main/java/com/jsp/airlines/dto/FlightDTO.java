package com.jsp.airlines.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import com.jsp.airlines.entity.Fare;
import com.jsp.airlines.entity.Flight;
import com.jsp.airlines.entity.FlightInfo;
import com.jsp.airlines.entity.Inventory;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@ToString
public class FlightDTO {
	private String destination;
	private LocalDate flightDate;
	private String flightNo;
	private LocalTime flightTime;
	private String currentLoc;
	private FareDTO fareDTO;
	private FlightInfoDTO flightInfoDTO;
	private InventoryDTO inventoryDTO;
}
