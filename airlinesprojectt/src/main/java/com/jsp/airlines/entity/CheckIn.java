package com.jsp.airlines.entity;


import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@Entity
public class CheckIn {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int checkInId;
	private int seatNo;
	private int gateNO;
	
	@OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER ,mappedBy = "checkIn")
	private Passenger passenger;
}
