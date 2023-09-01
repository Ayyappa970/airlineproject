package com.jsp.airlines.service;

import java.util.List;

import org.springframework.data.repository.query.Param;

import com.jsp.airlines.dto.AirlinesInfoDTO;
import com.jsp.airlines.dto.BookingInfoDTO;
import com.jsp.airlines.dto.CheckInDTO;
import com.jsp.airlines.dto.FareDTO;
import com.jsp.airlines.dto.FlightDTO;
import com.jsp.airlines.dto.FlightInfoDTO;
import com.jsp.airlines.dto.InventoryDTO;
import com.jsp.airlines.dto.PassengerDTO;
import com.jsp.airlines.dto.UserDTO;
import com.jsp.airlines.entity.AirlinesInfo;
import com.jsp.airlines.entity.BookingInfo;
import com.jsp.airlines.entity.CheckIn;
import com.jsp.airlines.entity.Fare;
import com.jsp.airlines.entity.Flight;
import com.jsp.airlines.entity.FlightInfo;
import com.jsp.airlines.entity.Inventory;
import com.jsp.airlines.entity.Passenger;

public interface AdminService {
	String addAirlineInfo(String name,AirlinesInfoDTO dto);
	int deleteAirline(String name);
	AirlinesInfo updateAirlineInfo(String name,AirlinesInfoDTO dto);
	AirlinesInfoDTO fetchAirlineByName(String name);
	List<AirlinesInfoDTO> getAllAirlineDetails();
	
	int addBookingInfo(BookingInfoDTO dto);
	BookingInfoDTO fetchByBookingInfoDTOId(int id);
	int deleteBookingInfoDTOById(int id);
	List<BookingInfoDTO> getAllBookings();
	BookingInfo updateBookingInfo(int id,BookingInfoDTO dto);
	
	int addCheckIn(CheckInDTO dto);
	CheckIn updateCheckIn(int id,CheckInDTO dto);
	CheckInDTO getBySeatNo(int gNo);
	List<CheckInDTO> getAllCheckInDetails();
	int deleteCheckIn(int id);
	
	int addFare(FareDTO dto);
	List<FareDTO> getDetailsByCurrency(String currency);
	List<FareDTO> getAllFareDetails();
	Fare updateFareInfo(int id,FareDTO dto);
	int deleteFare(int id);
	
	int addInventory(InventoryDTO dto);
	InventoryDTO fetchInventoryById(int id);
	List<InventoryDTO> getAllInventory();
	Inventory updateInventory(int id,InventoryDTO dto);
	int deleteInventoryByCount(int count);
	
	String addFlightInfo(AirlinesInfoDTO adto,FlightInfoDTO dto);
	FlightInfoDTO fetchFlightInfoById(int id);
	List<FlightInfoDTO> getDetailsByflightType(String flightType);
	List<FlightInfoDTO> getAllFlightInfoDetails();
	List<FlightInfoDTO> getFlightInfoBasedOnAirlineName(String airlineName);
	FlightInfo updateFlightInfo(int id,FlightInfoDTO dto);
	int deleteFlightInfoByFlightNo(String fNo);
	
	int addPassenger(PassengerDTO dto);
	PassengerDTO getPassengerByEmailAndMonileNum(String emailId,String moileNum);
	List<PassengerDTO> getAllPassengers();
	PassengerDTO getPassengerByBookingId(int id);
	PassengerDTO getPassengerByCheckInId(int id);
	Passenger updatePassengerDTO(String emailId,String moileNum,PassengerDTO dto);
	int deletePassenger(int id);
	
}
