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

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService{
	@Autowired
	private final AirlinesInfoRepository airlineRepository;
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
	public int deleteAirline(String name) {
		try {
			 AirlinesInfo info = airlineRepository.findByairlineName(name);
			 airlineRepository.delete(info);
				return 1;
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
	private final BookingInfoRepository bookingRepository;
	
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
	private final CheckInRepository checkRepository;
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
	private final FareRepository fareRepository;
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
	private final InventoryRepository inventoryRepository;
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
	public int deleteInventoryByCount(int id) {
		try {
			Inventory bycount = inventoryRepository.findBycount(id);
			inventoryRepository.delete(bycount);
			return 1;
		} catch (Exception e) {
			return 0;
		}
	}
	@Autowired
	private final FlightInfoRepository flightInfoRepository;
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
				return info.getFlightInfoId()+" And Airline Id :"+info.getAirlinesInfo().getAirlineId();
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
	public List<FlightInfoDTO> getFlightInfoBasedOnAirlineName(String airlineName) {
		return getAllFlightInfoDetails().stream()
				.filter(t -> t.getAirlinesInfo().getAirlineName().equalsIgnoreCase(airlineName))
				.collect(Collectors.toList());
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
	public int deleteFlightInfoByFlightNo(String fNo) {
		FlightInfo flightInfo = flightInfoRepository.findByflightNo(fNo);
		if (flightInfo!=null) {
			//	flightInfoRepository.deleteById(id);
				flightInfoRepository.delete(flightInfo);
				return 1;
			
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
	
	
	
}