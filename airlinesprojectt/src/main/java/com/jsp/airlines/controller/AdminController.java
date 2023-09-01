package com.jsp.airlines.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
import com.jsp.airlines.service.AdminService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/airline/admin")
public class AdminController {
	@Autowired
	private final AdminService service;
	@PostMapping("/addairline/{nam}")
	public ResponseEntity<String> addingAirline(@PathVariable("nam") String name,@RequestBody AirlinesInfoDTO dto){
		String info = service.addAirlineInfo(name, dto);
		return ResponseEntity.status(HttpStatus.CREATED).body("Airline Name : "+info);
	}
	
	@DeleteMapping("/deleteairline/{name}")
	public ResponseEntity<String> deleteAirline(@PathVariable("name") String name) {
		int i = service.deleteAirline(name);
		if (i>0) {
			return ResponseEntity.status(HttpStatus.FOUND).body("Record deleted : "+i);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No data found");
		}
	}
	@PutMapping("/updateairline/{name}")
	public ResponseEntity<String> updateAirlineInfo(@PathVariable("name") String name,@RequestBody AirlinesInfoDTO dto) {
		System.out.println(name);
		AirlinesInfo info = service.updateAirlineInfo(name,dto);
		if (info!=null) {
			return ResponseEntity.status(HttpStatus.FOUND).body("Airline name changed into "+info.getAirlineName());
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Airline name not found");
		}
	}
	@GetMapping("/findName/{name}")
	public ResponseEntity<String> fetch(@PathVariable("name") String name) {
		AirlinesInfoDTO dto = service.fetchAirlineByName(name);
		if (dto!=null) {
			return ResponseEntity.status(HttpStatus.FOUND).body("data founded : "+dto.getAirlineName());
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No data Founded By Airline Name");
		}
	}
	@GetMapping("/getallAirline")
	public ResponseEntity getAllAirlineDetails() {
		List<AirlinesInfoDTO> list = service.getAllAirlineDetails();
		if (list!=null) {
			return ResponseEntity.status(HttpStatus.FOUND).body(list);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No details found");
		}
	}
	
	@PostMapping("/book")
	public ResponseEntity<String> addingBookingInfo(@RequestBody BookingInfoDTO dto) {
		int id = service.addBookingInfo(dto);
		System.out.println(dto);
		return ResponseEntity.status(HttpStatus.CREATED).body("Booking id : "+id);
	}
	@GetMapping("/get/{id}")
	public ResponseEntity<BookingInfoDTO> getDetails(@PathVariable("id") int id){
		if (service.fetchByBookingInfoDTOId(id)!=null) {
			return ResponseEntity.status(HttpStatus.FOUND).body(service.fetchByBookingInfoDTOId(id));
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}
	@DeleteMapping("/deletebooking/{id}")
	public ResponseEntity deleteBookInfo(@PathVariable("id") int id) {
		int byId = service.deleteBookingInfoDTOById(id);
		if (byId>0) {
			return ResponseEntity.status(HttpStatus.FOUND).body("Record deleted : "+byId);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Record Found");
		}
	}
	@GetMapping("/getAllBookings")
	public ResponseEntity getAllBookings() {
		List<BookingInfoDTO> list = service.getAllBookings();
		if (list!=null) {
			return ResponseEntity.status(HttpStatus.FOUND).body(list);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Record Found");
		}
	}
	@PutMapping("/updateBooking/{id}")
	public ResponseEntity updateBookingDetails(@PathVariable("id") int id,@RequestBody BookingInfoDTO dto) {
		BookingInfo info = service.updateBookingInfo(id, dto);
		if (info!=null) {
			return ResponseEntity.status(HttpStatus.FOUND).body(info);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Records Found");
		}
	}
	@PostMapping("/addCheck")
	public ResponseEntity<Integer> addingCheckingDetails(@RequestBody CheckInDTO dto) {
		int in = service.addCheckIn(dto);
		return ResponseEntity.status(HttpStatus.CREATED).body(in);
	}
	@PutMapping("/updateCheckIn/{id}")
	public ResponseEntity updateCheckIn(@PathVariable("id") int id,@RequestBody CheckInDTO dto) {
		CheckIn info = service.updateCheckIn(id, dto);
		if (info!=null) {
			return ResponseEntity.status(HttpStatus.FOUND).body(info);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Records Found");
		}
	}
	@GetMapping("/getCheck/{no}")
	public ResponseEntity getCheckInByGateNo(@PathVariable("no") int id) {
		CheckInDTO dto = service.getBySeatNo(id);
		if (dto!=null) {
			return ResponseEntity.status(HttpStatus.FOUND).body(dto);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No details found");
		}
	}
	@GetMapping("/getAllCheckIn")
	public ResponseEntity getAllCheckInDetails() {
		List<CheckInDTO> list = service.getAllCheckInDetails();
		if (list!=null) {
			return ResponseEntity.status(HttpStatus.FOUND).body(list);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No details found");
		}
	}
	@DeleteMapping("/deletecheckin/{id}")
	public ResponseEntity deleteCheckIn(@PathVariable("id") int id) {
		int i= service.deleteCheckIn(id);
		if (i>0) {
			return ResponseEntity.status(HttpStatus.FOUND).body("Record deleted : "+i);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No data found");
		}
	}
	@PostMapping("/addAmount")
	public ResponseEntity<String> addingAmount(@RequestBody FareDTO dto) {
		System.out.println(dto);
		int fare = service.addFare(dto);
		return ResponseEntity.status(HttpStatus.CREATED).body("Fare id : "+fare);
	}
	@GetMapping("/getFare/{curr}")
	public ResponseEntity getfare(@PathVariable("curr") String currency) {
		List<FareDTO> list = service.getDetailsByCurrency(currency);
		if (list!=null) {
			return ResponseEntity.status(HttpStatus.FOUND).body(list);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No details found");
		}
	}
	@GetMapping("/getAllFare")
	public ResponseEntity getAllFareDetails() {
		List<FareDTO> list = service.getAllFareDetails();
		if (list!=null) {
			return ResponseEntity.status(HttpStatus.FOUND).body(list);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No details found");
		}
	}
	@PutMapping("/updateFare/{id}")
	public ResponseEntity updateFare(@PathVariable("id") int id,@RequestBody FareDTO dto) {
		Fare fare = service.updateFareInfo(id, dto);
		if (fare!=null) {
			return ResponseEntity.status(HttpStatus.FOUND).body(fare);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Records Found");
		}
	}
	@DeleteMapping("/deleteFare/{id}")
	public ResponseEntity deleteFare(@PathVariable("id") int id) {
		int fare = service.deleteFare(id);
		if (fare>0) {
			return ResponseEntity.status(HttpStatus.FOUND).body("Record deleted : "+fare);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No data found");
		}
	}
	@PostMapping("/addinventory")
	public ResponseEntity<String> addingInventories(@RequestBody InventoryDTO dto) {
		int inventory = service.addInventory(dto);
		return ResponseEntity.status(HttpStatus.CREATED).body("Inventory count : "+inventory);
	}
	@GetMapping("/getinventory/{id}")
	public ResponseEntity getInventoryByCount(@PathVariable("id") int id) {
		InventoryDTO dto = service.fetchInventoryById(id);
		if (dto!=null) {
			return ResponseEntity.status(HttpStatus.FOUND).body(dto);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No data Found");
		}
	}
	@GetMapping("/getAllInventories")
	public ResponseEntity getAlllInventory() {
		 List<InventoryDTO> dto = service.getAllInventory();
		if (dto!=null) {
			return ResponseEntity.status(HttpStatus.FOUND).body(dto);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No details found");
		}
	}
	@PutMapping("/updateinventory/{id}")
	public ResponseEntity updateInventory(@PathVariable("id") int id,@RequestBody InventoryDTO dto) {
		Inventory inventory = service.updateInventory(id, dto);
		if (inventory!=null) {
			return ResponseEntity.status(HttpStatus.FOUND).body(inventory);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No details Found");
		}
	}
	@DeleteMapping("/deleteInventory/{count}")
	public ResponseEntity deleteInventory(@PathVariable("count") int count) {
		int inventory = service.deleteInventoryByCount(count);
		if (inventory>0) {
			return ResponseEntity.status(HttpStatus.FOUND).body("Record deleted : "+inventory);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No data found");
		}
	}
	@PostMapping("/addflightinfo/{name}")
	public ResponseEntity<String> addingFlightInfo(@PathVariable("name") String name,@RequestBody FlightInfoDTO dto) {
		System.out.println(dto);
		AirlinesInfoDTO adto=new AirlinesInfoDTO();
		adto.setAirlineName(name);
		String info = service.addFlightInfo(adto, dto);
		if (info!=null) {
			return ResponseEntity.status(HttpStatus.CREATED).body("Flight info id : "+info);
		} else {
			return ResponseEntity.status(HttpStatus.CREATED).body("Enter valid flight no");
		}
	}
	@GetMapping("/gettFlightInfo/{id}")	
	public ResponseEntity getFlightInfoById(@PathVariable("id") int id) {
		FlightInfoDTO dto = service.fetchFlightInfoById(id);
		if (dto!=null) {
			return ResponseEntity.status(HttpStatus.FOUND).body(dto);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No data found");
		}
	}
	@GetMapping("/getFlightInfo/{fType}")
	public ResponseEntity getFlightInfoByGateNo(@PathVariable("fType") String flightType) {
		 List<FlightInfoDTO> dto = service.getDetailsByflightType(flightType);
		if (dto!=null) {
			return ResponseEntity.status(HttpStatus.FOUND).body(dto);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No details found");
		}
	}
	@GetMapping("/getAllFlightInfo")
	public ResponseEntity getAllFlightInfo() {
		 List<FlightInfoDTO> dto = service.getAllFlightInfoDetails();
		if (dto!=null) {
			return ResponseEntity.status(HttpStatus.FOUND).body(dto);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No details found");
		}
	}
	@GetMapping("/getFlightInf/{name}")
	public ResponseEntity getFlightInfoByAirlineName(@PathVariable("name") String airlineName) {
		List<FlightInfoDTO> list = service.getFlightInfoBasedOnAirlineName(airlineName);
		if (list!=null) {
			return ResponseEntity.status(HttpStatus.FOUND).body(list);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No details found");
		}
	}
	@PutMapping("/updateflightinfo/{id}")
	public ResponseEntity updateFlightInfo(@PathVariable("id") int id,@RequestBody FlightInfoDTO dto) {
		FlightInfo info = service.updateFlightInfo(id, dto);
		if (info!=null) {
			return ResponseEntity.status(HttpStatus.FOUND).body("Information updated Successfully");
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No details Found");
		}	
	}
	@DeleteMapping("/deleteflightinfo/{id}")
	public ResponseEntity<String> deleteFlightInformation(@PathVariable("id") String id) {
		System.out.println(id);
		int i = service.deleteFlightInfoByFlightNo(id);
		if (i>0) {
			return ResponseEntity.status(HttpStatus.FOUND).body("Record deleted : "+id);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No data found");
		}
	}
	@PostMapping("/addPassenger")
	public ResponseEntity<String> addingPassenger(@RequestBody PassengerDTO dto) {
		int i = service.addPassenger(dto);
		System.out.println(dto);
		return ResponseEntity.status(HttpStatus.CREATED).body("Passenger id : "+i);
	}
	@GetMapping("/getpassenger/{mail}/{mb}")
	public ResponseEntity getPassengerByEmailAndMb(@PathVariable("mail") String emailId,@PathVariable("mb") String moileNum) {
		 PassengerDTO dto = service.getPassengerByEmailAndMonileNum(emailId, moileNum);
		if (dto!=null) {
			return ResponseEntity.status(HttpStatus.FOUND).body(dto);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No details found");
		}
	}
	@GetMapping("/getpassenger/{id}")
	public ResponseEntity getPassengerByBooking(@PathVariable("id") int id) {
		 PassengerDTO dto = service.getPassengerByBookingId(id);
		if (dto!=null) {
			return ResponseEntity.status(HttpStatus.FOUND).body(dto);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No details found");
		}
	}
	@GetMapping("/getpassengerByCheck/{id}")
	public ResponseEntity getPassengerByCheckIn(@PathVariable("id") int id) {
		 PassengerDTO dto = service.getPassengerByCheckInId(id);
		if (dto!=null) {
			return ResponseEntity.status(HttpStatus.FOUND).body(dto);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No details found");
		}
	}
	@GetMapping("/getAllpassengers")
	public ResponseEntity getAllPassengers() {
		List<PassengerDTO> dto = service.getAllPassengers();
		if (dto!=null) {
			return ResponseEntity.status(HttpStatus.FOUND).body(dto);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No details found");
		}
	}
	@PutMapping("/updatePassenger/{mail}/{mb}")
	public ResponseEntity updatePassenger(@PathVariable("mail") String emailId,@PathVariable("mb") String moileNum,@RequestBody PassengerDTO dto) {
		Passenger info = service.updatePassengerDTO(emailId, moileNum, dto);
		if (info!=null) {
			return ResponseEntity.status(HttpStatus.FOUND).body("Record updated");
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Records Found");
		}
	}
	@DeleteMapping("/deletepassenger/{id}")
	public ResponseEntity deletePassenger(@PathVariable("id") int id) {
		int i= service.deletePassenger(id);
		System.out.println(i);
		if (i>0) {
			return ResponseEntity.status(HttpStatus.FOUND).body("Record deleted : "+i);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No data found");
		}
	}
	
}
