package com.jsp.airlines.dto;

import java.time.LocalTime;

import com.jsp.airlines.entity.AirlinesInfo;
import com.jsp.airlines.entity.Flight;
import com.jsp.airlines.entity.FlightInfo;

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
public class FlightInfoDTO {
	private String flightNo;
	private LocalTime flightTime;
	private String flightType;
	
	
	private AirlinesInfoDTO airlinesInfo;
}
