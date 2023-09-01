package com.jsp.airlines.dto;

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
public class PassengerDTO {
	private String emailId;
	private String firstName;
	private String lastName;
	private String gender;
	private String moileNum;
	private BookingInfoDTO bookingInfoDTO;
	private CheckInDTO checkInDTO;
}
