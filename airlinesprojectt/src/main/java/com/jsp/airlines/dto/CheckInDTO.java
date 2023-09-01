package com.jsp.airlines.dto;

import com.jsp.airlines.entity.CheckIn;
import com.jsp.airlines.entity.Passenger;

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
public class CheckInDTO {
	private int seatNo;
	private int gateNO;
}
