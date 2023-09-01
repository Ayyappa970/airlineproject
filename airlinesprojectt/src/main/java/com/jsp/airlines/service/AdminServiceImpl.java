package com.jsp.airlines.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
import com.jsp.airlines.entity.User;
import com.jsp.airlines.repository.AirlinesInfoRepository;
import com.jsp.airlines.repository.BookingInfoRepository;
import com.jsp.airlines.repository.CheckInRepository;
import com.jsp.airlines.repository.FareRepository;
import com.jsp.airlines.repository.FlightInfoRepository;
import com.jsp.airlines.repository.FlightRepository;
import com.jsp.airlines.repository.InventoryRepository;
import com.jsp.airlines.repository.PassengerRepository;
import com.jsp.airlines.repository.UserRepository;
@Service
public class AdminServiceImpl implements AdminService{
	@Autowired
	private AirlinesInfoRepository airlineRepository;
	@Override
	public String addAirlineInfo(String name,AirlinesInfoDTO dto) {
		 AirlinesInfo name2 = airlineRepository.findByairlineName(name);
		if (name2!=null) {
			return dto.getAirlineName();
		} else {
			AirlinesInfo save = airlineRepository.save(AirlinesInfo.builder().airlineName(dto.getAirlineName()).build());
			return save.getAirlineName();
		}
	}
	@Override
	public int deleteAirline(int id) {
		try {
			 airlineRepository.deleteById(id);
				return id;
		} catch (Exception e) {
			return 0;
		}
	}
	@Override
	public AirlinesInfo updateAirlineInfo(String name, AirlinesInfoDTO dto) {
		AirlinesInfo info = airlineRepository.findByairlineName(name);
		if (name!=null) {
			try {
				info.setAirlineName(dto.getAirlineName());
				AirlinesInfo save = airlineRepository.save(info);
				return save;
			} catch (Exception e) {
				return null;
			}
		} else {
			return null;
		}
	}
	@Override
	public AirlinesInfoDTO fetchAirlineByName(String name) {
		AirlinesInfo info = airlineRepository.findByairlineName(name);
		if (info!=null) {
			return AirlinesInfoDTO.builder().airlineName(info.getAirlineName()).build();
		} else {
			return null;
		}
	}
	@Override
	public List<AirlinesInfoDTO> getAllAirlineDetails() {
		List<AirlinesInfo> all = airlineRepository.findAll();
		if (all.size()>0) {
			List<AirlinesInfoDTO> collect = all.stream().map(t->AirlinesInfoDTO.builder().airlineName(t.getAirlineName()).build()).collect(Collectors.toList());
			return collect;
		} else {
			return null;
		}
		
	}
	
	@Autowired
	private BookingInfoRepository bookingRepository;
	
	@Override
	public int addBookingInfo(BookingInfoDTO dto) {
		BookingInfo info = bookingRepository.save(BookingInfo.builder().bookingDate(dto.getBookingDate()).currentCity(dto.getCurrentCity()).destination(dto.getDestination())
				.fare(dto.getFare()).flightDate(dto.getFlightDate()).flightNo(dto.getFlightNo()).flightTime(dto.getFlightTime()).status(dto.getStatus()).build());
		return info.getBookingId();
	}
	@Override
	public BookingInfoDTO fetchByBookingInfoDTOId(int id) {
		Optional<BookingInfo> optional = bookingRepository.findById(id);
		if (optional.isPresent()) {
			BookingInfo info = optional.get();
			BookingInfoDTO dto = BookingInfoDTO.builder().bookingDate(info.getBookingDate()).currentCity(info.getCurrentCity()).destination(info.getDestination()).fare(info.getFare()).flightDate(info.getFlightDate())
					.flightTime(info.getFlightTime()).flightNo(info.getFlightNo()).status(info.getStatus()).build();
			return dto;
		}else {
			return null;
		}
	}
	@Override
	public int deleteBookingInfoDTOById(int id) {
		try {
			bookingRepository.deleteById(id);
			return id;
		} catch (Exception e) {
			return 0;
		}
	}
	@Override
	public List<BookingInfoDTO> getAllBookings() {
		List<BookingInfo> all = bookingRepository.findAll();
		if (all.size()>0) {
			List<BookingInfoDTO> list = all.stream().map(t->BookingInfoDTO.builder().bookingDate(t.getBookingDate()).currentCity(t.getCurrentCity())
					.destination(t.getDestination()).fare(t.getFare()).flightDate(t.getFlightDate()).flightNo(t.getFlightNo())
					.flightTime(t.getFlightTime()).status(t.getStatus()).build()).collect(Collectors.toList());
			return list;
		} else {
			return null;
		}
		
	}
	@Override
	public BookingInfo updateBookingInfo(int id, BookingInfoDTO dto) {
		Optional<BookingInfo> byId = bookingRepository.findById(id);
		if (byId!=null) {
			try {
				BookingInfo info = byId.get();
				info.setBookingDate(dto.getBookingDate());
				info.setCurrentCity(dto.getCurrentCity());
				info.setDestination(dto.getDestination());
				info.setFare(dto.getFare());
				info.setFlightDate(dto.getFlightDate());
				info.setFlightNo(dto.getFlightNo());
				info.setFlightTime(dto.getFlightTime());
				info.setStatus(dto.getStatus());
				BookingInfo save = bookingRepository.save(info);
				return save;
			} catch (Exception e) {
				return null;
			}
		} else {
			return null;
		}
		
	}
	@Autowired
	private CheckInRepository checkRepository;
	@Override
	public int addCheckIn(CheckInDTO dto) {
			CheckIn in = checkRepository.save(CheckIn.builder().gateNO(dto.getGateNO()).seatNo(dto.getSeatNo()).build());
			return in.getCheckInId();
	}
	@Override
	public CheckIn updateCheckIn(int id,CheckInDTO dto) {
		Optional<CheckIn> optional = checkRepository.findById(id);
		if (optional.isPresent()) {
			try {
				CheckIn in = optional.get();
				in.setGateNO(dto.getGateNO());
				in.setSeatNo(dto.getSeatNo());
				CheckIn save = checkRepository.save(in);
				return save;
			} catch (Exception e) {
				return null;
			}
		} else {
			return null;
		}
		
	}
	@Override
	public CheckInDTO getBySeatNo(int gNo) {
		CheckIn in = checkRepository.findByseatNo(gNo);
		if (in!=null) {
			CheckInDTO dto = CheckInDTO.builder().gateNO(in.getGateNO()).seatNo(in.getSeatNo()).build();
			return dto;
		} else {
			return null;
		}
	}

	@Override
	public List<CheckInDTO> getAllCheckInDetails() {
		List<CheckIn> list = checkRepository.findAll();
		if (list.size()>0) {
			List<CheckInDTO> list2 = list.stream().map(t->CheckInDTO.builder().gateNO(t.getGateNO()).seatNo(t.getSeatNo()).build()).collect(Collectors.toList());
			return list2;
		} else {
			return null;
		}
	}
	@Override
	public int deleteCheckIn(int id) {
		try {
			checkRepository.deleteById(id);
			return id;
		} catch (Exception e) {
			return 0;
		}
	}
	@Autowired
	private FareRepository fareRepository;
	@Override
	public int addFare(FareDTO dto) {
		Fare amt = fareRepository.save(Fare.builder().currency(dto.getCurrency()).fareAmount(dto.getFareAmount()).build());
		return amt.getFareId();
	}
	@Override
	public List<FareDTO> getDetailsByCurrency(String currency) {
		List<Fare> list = fareRepository.findBycurrency(currency);
		if (list!=null) {
//			FareDTO dto = FareDTO.builder().currency(fare.getCurrency()).fareAmount(fare.getFareAmount()).build();
//			return list;
			List<FareDTO> list2 = list.stream().map(t->FareDTO.builder().currency(t.getCurrency()).fareAmount(t.getFareAmount()).build()).collect(Collectors.toList());
			return list2;
		} else {
			return null;
		}
	}
	@Override
	public List<FareDTO> getAllFareDetails() {
		List<Fare> all = fareRepository.findAll();
		if (all!=null) {
			List<FareDTO> list = all.stream().map(t->FareDTO.builder().currency(t.getCurrency()).fareAmount(t.getFareAmount()).build()).collect(Collectors.toList());
			return list;
		} else {
			return null;
		}
	}
	@Override
	public Fare updateFareInfo(int id, FareDTO dto) {
		Optional<Fare> optional = fareRepository.findById(id);
		if (optional.isPresent()) {
			try {
				Fare fare = optional.get();
				fare.setCurrency(dto.getCurrency());
				fare.setFareAmount(dto.getFareAmount());
				Fare save = fareRepository.save(fare);
				return save;
			} catch (Exception e) {
				return null;
			}
		} else {
			return null;
		}
	}
	@Override
	public int deleteFare(int id) {
		try {
			fareRepository.deleteById(id);
			return id;
		} catch (Exception e) {
			return 0;
		}
	}
	@Autowired
	private InventoryRepository inventoryRepository;
	@Override
	public int addInventory(InventoryDTO dto) {
		Inventory bycount = inventoryRepository.findBycount(dto.getCount());
		if (bycount!=null) {
			return bycount.getCount();
		} else {
			Inventory in = inventoryRepository.save(Inventory.builder().count(dto.getCount()).build());
			return in.getInventoryId();
		}
	}
	@Override
	public InventoryDTO fetchInventoryById(int id) {
		 Optional<Inventory> optional = inventoryRepository.findById(id);
		if (optional!=null) {
			try {
				Inventory in = optional.get();
				return InventoryDTO.builder().count(in.getCount()).build();
			} catch (Exception e) {
				return null;
			}
		} else {
			return null;
		}
		
	}
	@Override
	public List<InventoryDTO> getAllInventory() {
		List<Inventory> in = inventoryRepository.findAll();
		if (in!=null) {
			List<InventoryDTO> list = in.stream().map(t->InventoryDTO.builder().count(t.getCount()).build()).collect(Collectors.toList());
			return list;
		} else {
			return null;
		}
	}
	@Override
	public Inventory updateInventory(int id, InventoryDTO dto) {
		Optional<Inventory> optional = inventoryRepository.findById(id);
		if (optional.isPresent()) {
			Inventory inven = optional.get();
			inven.setCount(dto.getCount());
			Inventory save = inventoryRepository.save(inven);
			return save;
		} else {
			return null;
		}
	}
	@Override
	public int deleteInventory(int id) {
		try {
			inventoryRepository.deleteById(id);
			return id;
		} catch (Exception e) {
			return 0;
		}
	}
	@Autowired
	private FlightInfoRepository flightInfoRepository;
	@Override
	public String addFlightInfo(AirlinesInfoDTO adto,FlightInfoDTO dto) {
		AirlinesInfo name = airlineRepository.findByairlineName(adto.getAirlineName());
		if (name!=null) {
			try {
				FlightInfo info2 = flightInfoRepository.save(FlightInfo.builder()
						.flightNo(dto.getFlightNo())
						.flightTime(dto.getFlightTime())
						.flightType(dto.getFlightType())
						.airlinesInfo(name)
						.build());
				return info2.getFlightInfoId()+" And Airline Id :"+name.getAirlineId();
			} catch (Exception e) {
				return null;
			}
		} else {
			try {
				FlightInfo info = flightInfoRepository.save(FlightInfo.builder()
						.flightNo(dto.getFlightNo())
						.flightTime(dto.getFlightTime())
						.flightType(dto.getFlightType())
						.airlinesInfo(AirlinesInfo.builder().airlineName(dto.getAirlinesInfo().getAirlineName()).build())
						.build());
				return "FlightInfo id : "+info.getFlightInfoId()+" And Airline Id :"+info.getAirlinesInfo().getAirlineId();
			} catch (Exception e) {
				return null;
			}
		}
	}
	@Override
	public FlightInfoDTO fetchFlightInfoById(int id) {
		Optional<FlightInfo> optional = flightInfoRepository.findById(id);
		if (optional.isPresent()) {
			FlightInfo info = optional.get();
			FlightInfoDTO dto = FlightInfoDTO.builder().flightNo(info.getFlightNo()).flightTime(info.getFlightTime()).flightType(info.getFlightType())
			.airlinesInfo(AirlinesInfoDTO.builder().airlineName(info.getAirlinesInfo().getAirlineName()).build()).build();
			return dto;
		} else {
			return null;
		}
	}
	@Override
	public List<FlightInfoDTO> getDetailsByflightType(String flightType) {
		List<FlightInfo> info = flightInfoRepository.findByflightType(flightType);
		if (info!=null) {
			List<FlightInfoDTO> list = info.stream().map(t->FlightInfoDTO.builder().flightNo(t.getFlightNo())
					.flightTime(t.getFlightTime()).flightType(t.getFlightType()).airlinesInfo(AirlinesInfoDTO.builder()
							.airlineName(t.getAirlinesInfo().getAirlineName()).build()).build()).collect(Collectors.toList());
			return list;
		} else {
			return null;
		}
		
	}

	@Override
	public List<FlightInfoDTO> getAllFlightInfoDetails() {
		List<FlightInfo> info = flightInfoRepository.findAll();
		if (info!=null) {
			List<FlightInfoDTO> list = info.stream().map(t->FlightInfoDTO.builder().flightNo(t.getFlightNo())
					.flightTime(t.getFlightTime()).flightType(t.getFlightType()).airlinesInfo(AirlinesInfoDTO.builder()
							.airlineName(t.getAirlinesInfo().getAirlineName()).build()).build()).collect(Collectors.toList());
			return list;
		} else {
			return null;
		}
	}
	@Override
	public FlightInfo updateFlightInfo(int id, FlightInfoDTO dto) {
		Optional<FlightInfo> optional = flightInfoRepository.findById(id);
		AirlinesInfo name = airlineRepository.findByairlineName(dto.getAirlinesInfo().getAirlineName());
		if (optional.isPresent() && name!=null) {
			FlightInfo info = optional.get();
			info.setFlightNo(dto.getFlightNo());
			info.setFlightTime(dto.getFlightTime());
			info.setFlightType(dto.getFlightType());
			name.setAirlineName(dto.getAirlinesInfo().getAirlineName());
//			AirlinesInfo air=new AirlinesInfo();
//			air.setAirlineName(dto.getAirlinesInfo().getAirlineName());
			info.setAirlinesInfo(name);
			FlightInfo save = flightInfoRepository.save(info);
			return save;
		} else {
			return null;
		}
	}
	@Override
	public int deleteFlightInfo(int id) {
		Optional<FlightInfo> optional = flightInfoRepository.findById(id);
		if (optional.isPresent()) {
				FlightInfo info = optional.get();
			//	flightInfoRepository.deleteById(id);
				flightInfoRepository.deleteById(info.getFlightInfoId());
				return id;
			
		} else {
			return 0;
		}
	}
	@Autowired
	private PassengerRepository passengrtRepository;
	
	@Override
	public int addPassenger(PassengerDTO dto) {
		BookingInfo info = bookingRepository.findByflightNo(dto.getBookingInfoDTO().getFlightNo());
		CheckIn no = checkRepository.findByseatNo(dto.getCheckInDTO().getSeatNo());
		if (info!=null) {
			try {
				Passenger ps = passengrtRepository.save(Passenger.builder().emailId(dto.getEmailId()).firstName(dto.getFirstName())
						.gender(dto.getGender()).lastName(dto.getLastName()).moileNum(dto.getMoileNum())
						.bookingInfo(info)
						.checkIn(CheckIn.builder().gateNO(dto.getCheckInDTO().getGateNO()).seatNo(dto.getCheckInDTO().getSeatNo()).build()).build());
				return ps.getPassengerId();
			} catch (Exception e) {
				return 0;
			}
		} else {
			try {
				Passenger ps = passengrtRepository.save(Passenger.builder().emailId(dto.getEmailId()).firstName(dto.getFirstName())
						.gender(dto.getGender()).lastName(dto.getLastName()).moileNum(dto.getMoileNum())
						.bookingInfo(BookingInfo.builder().bookingDate(dto.getBookingInfoDTO().getBookingDate()).currentCity(dto.getBookingInfoDTO().getCurrentCity())
						.destination(dto.getBookingInfoDTO().getDestination()).fare(dto.getBookingInfoDTO().getFare()).flightDate(dto.getBookingInfoDTO().getFlightDate())
						.flightNo(dto.getBookingInfoDTO().getFlightNo()).flightTime(dto.getBookingInfoDTO().getFlightTime()).status(dto.getBookingInfoDTO().getStatus()).build())
						.checkIn(CheckIn.builder().gateNO(dto.getCheckInDTO().getGateNO()).seatNo(dto.getCheckInDTO().getSeatNo()).build()).build());
				return ps.getPassengerId();
			} catch (Exception e) {
				return 0;
			}
		}
	}
	@Override
	public PassengerDTO getPassengerByEmailAndMonileNum(String emailId, String moileNum) {
		Passenger pass = passengrtRepository.findByemailIdAndmoileNum(emailId, moileNum);
		if (pass!=null) {
			PassengerDTO dto = PassengerDTO.builder().emailId(pass.getEmailId()).firstName(pass.getFirstName())
			.lastName(pass.getLastName()).gender(pass.getGender()).moileNum(pass.getMoileNum())
			.bookingInfoDTO(BookingInfoDTO.builder().bookingDate(pass.getBookingInfo().getBookingDate()).currentCity(pass.getBookingInfo().getCurrentCity())
			.destination(pass.getBookingInfo().getDestination()).fare(pass.getBookingInfo().getFare())
			.flightDate(pass.getBookingInfo().getFlightDate()).flightNo(pass.getBookingInfo().getFlightNo())
			.flightTime(pass.getBookingInfo().getFlightTime()).status(pass.getBookingInfo().getStatus()).build())
			.checkInDTO(CheckInDTO.builder().gateNO(pass.getCheckIn().getGateNO()).seatNo(pass.getCheckIn().getSeatNo()).build()).build();
			return dto;
		} else {
			return null;
		}
		
	}

	@Override
	public List<PassengerDTO> getAllPassengers() {
		List<Passenger> all = passengrtRepository.findAll();
		if (all.size()>0) {
			List<PassengerDTO> list = all.stream().map(t->PassengerDTO.builder().emailId(t.getEmailId()).firstName(t.getFirstName())
					.lastName(t.getLastName()).gender(t.getGender()).moileNum(t.getMoileNum())
			.bookingInfoDTO(BookingInfoDTO.builder().bookingDate(t.getBookingInfo().getBookingDate()).currentCity(t.getBookingInfo().getCurrentCity())
			.destination(t.getBookingInfo().getDestination()).fare(t.getBookingInfo().getFare()).flightDate(t.getBookingInfo().getFlightDate())
			.flightNo(t.getBookingInfo().getFlightNo()).flightTime(t.getBookingInfo().getFlightTime()).status(t.getBookingInfo().getStatus()).build())
			.checkInDTO(CheckInDTO.builder().gateNO(t.getCheckIn().getGateNO()).seatNo(t.getCheckIn().getSeatNo()).build()).build()).collect(Collectors.toList());
			return list;
		} else {
			return null;
		}
	}
	@Override
	public PassengerDTO getPassengerByBookingId(int id) {
		Passenger pass = passengrtRepository.findBybookingInfo(id);
		if (pass!=null) {
			PassengerDTO dto = PassengerDTO.builder().emailId(pass.getEmailId()).firstName(pass.getFirstName())
					.lastName(pass.getLastName()).gender(pass.getGender()).moileNum(pass.getMoileNum())
					.bookingInfoDTO(BookingInfoDTO.builder().bookingDate(pass.getBookingInfo().getBookingDate()).currentCity(pass.getBookingInfo().getCurrentCity())
					.destination(pass.getBookingInfo().getDestination()).fare(pass.getBookingInfo().getFare())
					.flightDate(pass.getBookingInfo().getFlightDate()).flightNo(pass.getBookingInfo().getFlightNo())
					.flightTime(pass.getBookingInfo().getFlightTime()).status(pass.getBookingInfo().getStatus()).build())
					.checkInDTO(CheckInDTO.builder().gateNO(pass.getCheckIn().getGateNO()).seatNo(pass.getCheckIn().getSeatNo()).build()).build();
					return dto;
		} else {
			return null;
		}
	}
	@Override
	public PassengerDTO getPassengerByCheckInId(int id) {
		Passenger pass = passengrtRepository.findBycheckIn(id);
		if (pass!=null) {
			PassengerDTO dto = PassengerDTO.builder().emailId(pass.getEmailId()).firstName(pass.getFirstName())
					.lastName(pass.getLastName()).gender(pass.getGender()).moileNum(pass.getMoileNum())
					.bookingInfoDTO(BookingInfoDTO.builder().bookingDate(pass.getBookingInfo().getBookingDate()).currentCity(pass.getBookingInfo().getCurrentCity())
					.destination(pass.getBookingInfo().getDestination()).fare(pass.getBookingInfo().getFare())
					.flightDate(pass.getBookingInfo().getFlightDate()).flightNo(pass.getBookingInfo().getFlightNo())
					.flightTime(pass.getBookingInfo().getFlightTime()).status(pass.getBookingInfo().getStatus()).build())
					.checkInDTO(CheckInDTO.builder().gateNO(pass.getCheckIn().getGateNO()).seatNo(pass.getCheckIn().getSeatNo()).build()).build();
					return dto;
		} else {
			return null;
		}
	}
	@Override
	public Passenger updatePassengerDTO(String emailId,String moileNum, PassengerDTO dto) {
		Passenger pass = passengrtRepository.findByemailIdAndmoileNum(emailId, moileNum);
		if (pass!=null) {
			pass.setEmailId(dto.getEmailId());
			pass.setFirstName(dto.getFirstName());
			pass.setLastName(dto.getLastName());
			pass.setGender(dto.getGender());
			pass.setMoileNum(dto.getMoileNum());
			BookingInfo info = bookingRepository.findByflightNo(dto.getBookingInfoDTO().getFlightNo());
			pass.setBookingInfo(info);
			CheckIn in = checkRepository.findByseatNo(dto.getCheckInDTO().getSeatNo());
			pass.setCheckIn(in);
			Passenger save = passengrtRepository.save(pass);
			return save;
		} else {
			return null;
		}
	}
	@Override
	public int deletePassenger(int id) {
		Optional<Passenger> optional = passengrtRepository.findById(id);
		if (optional!=null) {
			passengrtRepository.deleteById(id);
			return id;
		} else {
			 return 0;
		}
	}
	@Autowired
	private FlightRepository flightRepository;
	@Override
	public int addFlight(FlightDTO dto) {
		AirlinesInfo info = airlineRepository.findByairlineName(dto.getFlightInfoDTO().getAirlinesInfo().getAirlineName());
		if (info!=null) {
			try {
				Flight flight = flightRepository.save(Flight.builder().currentLoc(dto.getCurrentLoc()).destination(dto.getDestination()).flightDate(dto.getFlightDate())
						.flightNo(dto.getFlightNo()).flightTime(dto.getFlightTime())
						.fare(Fare.builder().currency(dto.getFareDTO().getCurrency()).fareAmount(dto.getFareDTO().getFareAmount()).build())
						.flightInfo(FlightInfo.builder().flightNo(dto.getFlightInfoDTO().getFlightNo()).flightTime(dto.getFlightInfoDTO().getFlightTime())
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