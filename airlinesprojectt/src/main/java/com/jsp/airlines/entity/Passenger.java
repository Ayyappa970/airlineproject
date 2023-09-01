package com.jsp.airlines.entity;

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
public class Passenger {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int passengerId;
	private String emailId;
	private String firstName;
	private String lastName;
	private String gender;
	private String moileNum;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "bookingId")
	private BookingInfo bookingInfo;
	
	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "checkInId")
	private CheckIn checkIn;
}
