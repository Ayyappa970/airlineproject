package com.jsp.airlines.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jsp.airlines.dto.AirlinesInfoDTO;
import com.jsp.airlines.dto.FareDTO;
import com.jsp.airlines.dto.FlightDTO;
import com.jsp.airlines.dto.FlightInfoDTO;
import com.jsp.airlines.dto.InventoryDTO;
import com.jsp.airlines.entity.AirlinesInfo;
import com.jsp.airlines.entity.Fare;
import com.jsp.airlines.entity.Flight;
import com.jsp.airlines.entity.FlightInfo;
import com.jsp.airlines.entity.Inventory;
import com.jsp.airlines.repository.AirlinesInfoRepository;
import com.jsp.airlines.repository.FareRepository;
import com.jsp.airlines.repository.FlightInfoRepository;
import com.jsp.airlines.repository.FlightRepository;
import com.jsp.airlines.repository.InventoryRepository;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class FlightServiceImpl implements FlightService{
	
	@Autowired
	private final FlightRepository flightRepository;
	private final AirlinesInfoRepository airlineRepository;
	private final FlightInfoRepository flightInfoRepository;
	private final InventoryRepository inventoryRepository;
	private final FareRepository fareRepository; 
	@Override
	public int addFlight(FlightDTO dto) {
		AirlinesInfo info = airlineRepository.findByairlineName(dto.getFlightInfoDTO().getAirlinesInfo().getAirlineName());
		FlightInfo info2 = flightInfoRepository.findByflightNo(dto.getFlightInfoDTO().getFlightNo());
		if (info!=null) {
			try {
				Flight flight = flightRepository.save(Flight.builder().currentLoc(dto.getCurrentLoc()).destination(dto.getDestination())
						.flightDate(dto.getFlightDate()).flightNo(dto.getFlightNo()).flightTime(dto.getFlightTime())
						.fare(Fare.builder().currency(dto.getFareDTO().getCurrency()).fareAmount(dto.getFareDTO().getFareAmount()).build())
						.flightInfo(FlightInfo.builder().flightNo(dto.getFlightNo()).flightTime(dto.getFlightTime())
						.flightType(dto.getFlightInfoDTO().getFlightType()).airlinesInfo(info).build())
						.inventories(Inventory.builder().count(dto.getInventoryDTO().getCount()).build()).build());
				return flight.getFlightId();
			} catch (Exception e) {
				return 0;
			}
		} else {
			try {
				Flight flight = flightRepository.save(Flight.builder().currentLoc(dto.getCurrentLoc()).destination(dto.getDestination()).flightDate(dto.getFlightDate())
						.flightNo(dto.getFlightNo()).flightTime(dto.getFlightTime())
						.fare(Fare.builder().currency(dto.getFareDTO().getCurrency()).fareAmount(dto.getFareDTO().getFareAmount()).build())
						.flightInfo(FlightInfo.builder().flightNo(dto.getFlightInfoDTO().getFlightNo()).flightTime(dto.getFlightInfoDTO().getFlightTime())
						.flightType(dto.getFlightInfoDTO().getFlightType()).airlinesInfo(AirlinesInfo.builder().airlineName(dto.getFlightInfoDTO().getAirlinesInfo().getAirlineName()).build()).build())
						.inventories(Inventory.builder().count(dto.getInventoryDTO().getCount()).build()).build());
				return flight.getFlightId();
			} catch (Exception e) {
				return 0;
			}
		}
		
	}
	@Override
	public FlightDTO getFlightById(int id) {
		Optional<Flight> optional = flightRepository.findById(id);
		if (optional!=null) {
			try {
				Flight flight = optional.get();
				FlightDTO dto = FlightDTO.builder().currentLoc(flight.getCurrentLoc()).destination(flight.getDestination()).flightDate(flight.getFlightDate())
						.flightNo(flight.getFlightNo()).flightTime(flight.getFlightTime()).fareDTO(FareDTO.builder().currency(flight.getFare().getCurrency())
						.fareAmount(flight.getFare().getFareAmount()).build()).flightInfoDTO(FlightInfoDTO.builder().flightNo(flight.getFlightNo())
						.flightTime(flight.getFlightTime()).flightType(flight.getFlightInfo().getFlightType())
						.airlinesInfo(AirlinesInfoDTO.builder().airlineName(flight.getFlightInfo().getAirlinesInfo().getAirlineName()).build()).build())
						.inventoryDTO(InventoryDTO.builder().count(flight.getInventories().getCount()).build()).build();
				return dto;
			} catch (Exception e) {
				return null;
			}
		} else {
			return null;
		}
	}
	@Override
	public FlightDTO getFlightByFareId(int id) {
		Flight flight = flightRepository.findByfareId(id);
		if (flight!=null) {
			try {
				FlightDTO dto = FlightDTO.builder().currentLoc(flight.getCurrentLoc()).destination(flight.getDestination()).flightDate(flight.getFlightDate())
						.flightNo(flight.getFlightNo()).flightTime(flight.getFlightTime()).fareDTO(FareDTO.builder().currency(flight.getFare().getCurrency())
						.fareAmount(flight.getFare().getFareAmount()).build()).flightInfoDTO(FlightInfoDTO.builder().flightNo(flight.getFlightNo())
						.flightTime(flight.getFlightTime()).flightType(flight.getFlightInfo().getFlightType())
						.airlinesInfo(AirlinesInfoDTO.builder().airlineName(flight.getFlightInfo().getAirlinesInfo().getAirlineName()).build()).build())
						.inventoryDTO(InventoryDTO.builder().count(flight.getInventories().getCount()).build()).build();
				return dto;
			} catch (Exception e) {
				return null;
			}
		} else {
			return null;
		}
	}
	@Override
	public FlightDTO getFlightByFlightInfoId(int id) {
		Flight flight = flightRepository.findByflightInfoId(id);
		if (flight!=null) {
			try {
				FlightDTO dto = FlightDTO.builder().currentLoc(flight.getCurrentLoc()).destination(flight.getDestination()).flightDate(flight.getFlightDate())
						.flightNo(flight.getFlightNo()).flightTime(flight.getFlightTime()).fareDTO(FareDTO.builder().currency(flight.getFare().getCurrency())
						.fareAmount(flight.getFare().getFareAmount()).build()).flightInfoDTO(FlightInfoDTO.builder().flightNo(flight.getFlightNo())
						.flightTime(flight.getFlightTime()).flightType(flight.getFlightInfo().getFlightType())
						.airlinesInfo(AirlinesInfoDTO.builder().airlineName(flight.getFlightInfo().getAirlinesInfo().getAirlineName()).build()).build())
						.inventoryDTO(InventoryDTO.builder().count(flight.getInventories().getCount()).build()).build();
				return dto;
			} catch (Exception e) {
				return null;
			}
		} else {
			return null;
		}
	}
	@Override
	public List<FlightDTO> getFlightByAirlineName(String name) {
		 List<FlightDTO> flight = flightRepository.findByflightairlineName(name);
		if (flight.size()>0) {
			try {
				List<FlightDTO> list = flight.stream().map(t->FlightDTO.builder().currentLoc(t.getCurrentLoc()).destination(t.getDestination())
				.flightDate(t.getFlightDate()).flightNo(t.getFlightNo()).flightTime(t.getFlightTime())
				.fareDTO(FareDTO.builder().currency(t.getFareDTO().getCurrency()).fareAmount(t.getFareDTO().getFareAmount()).build())
				.flightInfoDTO(FlightInfoDTO.builder().flightNo(t.getFlightNo()).flightTime(t.getFlightTime()).flightType(t.getFlightInfoDTO().getFlightType())
				.airlinesInfo(AirlinesInfoDTO.builder().airlineName(t.getFlightInfoDTO().getAirlinesInfo().getAirlineName()).build()).build())
				.inventoryDTO(InventoryDTO.builder().count(t.getInventoryDTO().getCount()).build()).build()).collect(Collectors.toList());
				return list;
			} catch (Exception e) {
				return null;
			}
		} else {
			return null;
		}
	}
	@Override
	public FlightDTO searchByflightNo(String flightNo) {
		Flight flight = flightRepository.findByflightNo(flightNo);
		if (flight!=null) {
			FlightDTO dto = FlightDTO.builder().currentLoc(flight.getCurrentLoc()).destination(flight.getDestination())
			.flightDate(flight.getFlightDate()).flightNo(flight.getFlightNo()).flightTime(flight.getFlightTime())
			.fareDTO(FareDTO.builder().currency(flight.getFare().getCurrency()).fareAmount(flight.getFare().getFareAmount()).build())
			.flightInfoDTO(FlightInfoDTO.builder().flightNo(flight.getFlightInfo().getFlightNo()).flightTime(flight.getFlightInfo().getFlightTime())
			.flightType(flight.getFlightInfo().getFlightType())
			.airlinesInfo(AirlinesInfoDTO.builder().airlineName(flight.getFlightInfo().getAirlinesInfo().getAirlineName()).build()).build())
			.inventoryDTO(InventoryDTO.builder().count(flight.getInventories().getCount()).build()).build();
			return dto;
		} else {
			return null;
		}
	}
	@Override
	public List<FlightDTO> getAllFlightDetails() {
		List<Flight> findAll = flightRepository.findAll();
		if (findAll!=null) {
			List<FlightDTO> list = findAll.stream().map(t->FlightDTO.builder().currentLoc(t.getCurrentLoc()).destination(t.getDestination())
			.flightDate(t.getFlightDate()).flightNo(t.getFlightNo()).flightTime(t.getFlightTime())
			.fareDTO(FareDTO.builder().currency(t.getFare().getCurrency()).fareAmount(t.getFare().getFareAmount()).build())
			.flightInfoDTO(FlightInfoDTO.builder().flightNo(t.getFlightInfo().getFlightNo()).flightTime(t.getFlightInfo().getFlightTime())
			.flightType(t.getFlightInfo().getFlightType()).airlinesInfo(AirlinesInfoDTO.builder().airlineName(t.getFlightInfo().getAirlinesInfo().getAirlineName())
			.build()).build()).inventoryDTO(InventoryDTO.builder().count(t.getInventories().getCount()).build()).build()).collect(Collectors.toList());
			return list;
		} else {
			return null;
		}
	}
//	@Override
//	public FlightDTO searchFlightBycurrentLocAnddestinationAndflightDate(String currentLoc, String destination,
//			LocalDate flightDate) {
//		Flight flight = flightRepository.findFlight(currentLoc, destination, flightDate);
//		if (flight!=null) {
//			FlightDTO dto = FlightDTO.builder().currentLoc(flight.getCurrentLoc()).destination(flight.getDestination())
//					.flightDate(flight.getFlightDate()).flightNo(flight.getFlightNo()).flightTime(flight.getFlightTime())
//					.fareDTO(FareDTO.builder().currency(flight.getFare().getCurrency()).fareAmount(flight.getFare().getFareAmount()).build())
//					.flightInfoDTO(FlightInfoDTO.builder().flightNo(flight.getFlightInfo().getFlightNo()).flightTime(flight.getFlightInfo().getFlightTime())
//					.flightType(flight.getFlightInfo().getFlightType())
//					.airlinesInfo(AirlinesInfoDTO.builder().airlineName(flight.getFlightInfo().getAirlinesInfo().getAirlineName()).build()).build())
//					.inventoryDTO(InventoryDTO.builder().count(flight.getInventories().getCount()).build()).build();
//					return dto;
//		} else {
//			return null;
//		}
//		
//	}
	@Override
	public Flight updateFlight(int id, FlightDTO dto) {
		Optional<Flight> optional = flightRepository.findById(id);
		if (optional!=null) {
			try {
				Flight flight = optional.get();
				flight.setCurrentLoc(dto.getCurrentLoc());
				flight.setDestination(dto.getCurrentLoc());
				flight.setFlightDate(dto.getFlightDate());
				flight.setFlightNo(dto.getFlightNo());
				flight.setFlightTime(dto.getFlightTime());
				FlightInfo no = flightInfoRepository.findByflightNo(dto.getFlightInfoDTO().getFlightNo());
				flight.setFlightInfo(no);
				Inventory bycount = inventoryRepository.findBycount(dto.getInventoryDTO().getCount());
				flight.setInventories(bycount);
				Fare fare = fareRepository.findBycurrencyAndfareAmount(dto.getFareDTO().getCurrency(), dto.getFareDTO().getFareAmount());
				flight.setFare(fare);
				Flight save = flightRepository.save(flight);
				return save;
			} catch (Exception e) {
				return null;
			}
			
		} else {
			return null;
		}
	}
	@Override
	public int deleteFlight(int id) {
		try {
			flightRepository.deleteById(id);
			return id;
		} catch (Exception e) {
			return 0;
		}
	}
	

}
