package com.jsp.airlines.entity;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
public class FlightInfo {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int flightInfoId;
	private String flightNo;
	private LocalTime flightTime;
	private String flightType;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "airlineId")
	private AirlinesInfo airlinesInfo;
	
	@OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER,mappedBy = "flightInfo")
	private Flight flight;
}
