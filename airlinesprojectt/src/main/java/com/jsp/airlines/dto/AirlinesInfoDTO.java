package com.jsp.airlines.dto;

import java.util.List;

import com.jsp.airlines.entity.AirlinesInfo;
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
public class AirlinesInfoDTO {
	private String airlineName;
}
