package com.jsp.airlines.controller;

import java.time.LocalDate;
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

import com.jsp.airlines.dto.FlightDTO;
import com.jsp.airlines.entity.Flight;
import com.jsp.airlines.service.FlightService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/airline/flight")
@RequiredArgsConstructor
public class FlightController {
	@Autowired
	private final FlightService service;
	@PostMapping("/addFlight")
	public ResponseEntity<String> addingFlight(@RequestBody FlightDTO dto) {
//		AirlinesInfoDTO adto=new AirlinesInfoDTO();
//		adto.setAirlineName(dto.getFlightInfoDTO().getAirlinesInfo().getAirlineName());
		System.out.println(dto);
		return ResponseEntity.status(HttpStatus.CREATED).body("Flight id : "+service.addFlight(dto));
	}
	@GetMapping("/fetchByflightid/{id}")
	public ResponseEntity getFlightDetailsById(@PathVariable("id") int id) {
		FlightDTO dto = service.getFlightById(id);
		if (dto!=null) {
			return ResponseEntity.status(HttpStatus.FOUND).body(dto);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No details found");
		}
	}
	@GetMapping("/fetchflightByFareid/{id}")
	public ResponseEntity getFlightDetailsByFareId(@PathVariable("id") int id) {
		FlightDTO dto = service.getFlightByFareId(id);
		if (dto!=null) {
			return ResponseEntity.status(HttpStatus.FOUND).body(dto);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No details found");
		}
	}
	@GetMapping("/fetchflightByFlightInfoId/{id}")
	public ResponseEntity getFlightDetailsByFlightInfoId(@PathVariable("id") int id) {
		FlightDTO dto = service.getFlightByFlightInfoId(id);
		if (dto!=null) {
			return ResponseEntity.status(HttpStatus.FOUND).body(dto);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No details found");
		}
	}
	@GetMapping("/fetchflightByAirlineName/{name}")
	public ResponseEntity getFlightDetailsByAirlineName(@PathVariable("name") String name) {
		List<FlightDTO> dto = service.getFlightByAirlineName(name);
		if (dto!=null) {
			return ResponseEntity.status(HttpStatus.FOUND).body(dto);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No details found");
		}
	}
	@GetMapping("/fetchflightByFlightNo/{no}")
	public ResponseEntity getFlightByFlightNo(@PathVariable("no") String flightNo) {
		FlightDTO dto = service.searchByflightNo(flightNo);
		if (dto!=null) {
			return ResponseEntity.status(HttpStatus.FOUND).body(dto);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No details found");
		}
	}
	@GetMapping("/fetchallFlights")
	public ResponseEntity getAllFlightDetails() {
		List<FlightDTO> dto = service.getAllFlightDetails();
		if (dto!=null) {
			return ResponseEntity.status(HttpStatus.FOUND).body(dto);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No details found");
		}
	}
//	@GetMapping("/getFlight/{loc}/{des}/{no}")
//	public ResponseEntity getFlightBycurrentLocAnddestinationAndflightDate(@PathVariable("loc") String currentLoc,
//			@PathVariable("des") String destination,@PathVariable("no") LocalDate flightDate) {
//		FlightDTO dto = service.searchFlightBycurrentLocAnddestinationAndflightDate(currentLoc, destination, flightDate);
//		if (dto!=null) {
//			return ResponseEntity.status(HttpStatus.FOUND).body(dto);
//		} else {
//			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No details found");
//		}
//	}
	@PutMapping("/updateFlight/{id}")
	public ResponseEntity updateFlight(@PathVariable("id") int id,@RequestBody FlightDTO dto) {
		Flight info = service.updateFlight(id, dto);
		if (info!=null) {
			return ResponseEntity.status(HttpStatus.FOUND).body("Record updated");
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Records Found");
		}
	}
	@DeleteMapping("/deleteflight/{id}")
	public ResponseEntity deleteFlight(@PathVariable("id") int id) {
		int i= service.deleteFlight(id);
		System.out.println(i);
		if (i>0) {
			return ResponseEntity.status(HttpStatus.FOUND).body("Record deleted : "+i);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No data found");
		}
	}
}
