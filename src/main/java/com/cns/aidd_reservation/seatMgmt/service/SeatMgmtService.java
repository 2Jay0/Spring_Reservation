package com.cns.aidd_reservation.seatMgmt.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cns.aidd_reservation.exception.BusinessException;
import com.cns.aidd_reservation.reservation.dto.InsertReservationDto;
import com.cns.aidd_reservation.reservation.dto.RetrieveReservationInDto;
import com.cns.aidd_reservation.reservation.dto.RetrieveReservationOutDto;
import com.cns.aidd_reservation.reservation.dto.RetrieveSeatAvailableInDto;
import com.cns.aidd_reservation.reservation.dto.RetrieveSeatAvailableOutDto;
import com.cns.aidd_reservation.reservation.dto.UpdateReservationSeatDto;
import com.cns.aidd_reservation.reservation.dto.UpdateReservationStatusDto;
import com.cns.aidd_reservation.reservation.repository.ReservationRepository;
import com.cns.aidd_reservation.seatMgmt.dto.CheckInSeatInDto;
import com.cns.aidd_reservation.seatMgmt.dto.CheckInSeatOutDto;
import com.cns.aidd_reservation.seatMgmt.dto.MoveSeatInDto;
import com.cns.aidd_reservation.seatMgmt.dto.MoveSeatOutDto;
import com.cns.aidd_reservation.seatMgmt.dto.RetrieveAvailableSeatInDto;
import com.cns.aidd_reservation.seatMgmt.dto.RetrieveAvailableSeatOutDto;
import com.cns.aidd_reservation.seatMgmt.dto.RetrieveAvailableSeatsByDateInDto;
import com.cns.aidd_reservation.seatMgmt.dto.RetrieveAvailableSeatsByDateOutDto;
import com.cns.aidd_reservation.seatMgmt.dto.RetrieveBuildingInDto;
import com.cns.aidd_reservation.seatMgmt.dto.RetrieveBuildingOutDto;
import com.cns.aidd_reservation.seatMgmt.dto.RetrieveFloorInDto;
import com.cns.aidd_reservation.seatMgmt.dto.RetrieveFloorOutDto;
import com.cns.aidd_reservation.seatMgmt.dto.RetrieveTotalAvailableSeatInDto;
import com.cns.aidd_reservation.seatMgmt.dto.RetrieveTotalAvailableSeatOutDto;
import com.cns.aidd_reservation.seatMgmt.dto.RetrieveTotalSeatInDto;
import com.cns.aidd_reservation.seatMgmt.dto.RetrieveTotalSeatOutDto;
import com.cns.aidd_reservation.seatMgmt.dto.ReturnSeatInDto;
import com.cns.aidd_reservation.seatMgmt.dto.ReturnSeatOutDto;
import com.cns.aidd_reservation.seatMgmt.repository.SeatMgmtRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SeatMgmtService {
	
	@Autowired
	private SeatMgmtRepository seatMgmtRepository;
	
	@Autowired
	private ReservationRepository reservationRepository;
	
	public List<RetrieveAvailableSeatOutDto> retrieveAvailableSeat(RetrieveAvailableSeatInDto retrieveAvailableSeatInDto) throws Exception{
		//1. Declare Variable
		
		//paging variable
		int skip = retrieveAvailableSeatInDto.getSkip() != null ? retrieveAvailableSeatInDto.getSkip() : 0;
		int limit = retrieveAvailableSeatInDto.getLimit() != null ? retrieveAvailableSeatInDto.getLimit() : 5;
		
		LocalDateTime now = LocalDateTime.now();
		
		String startDate = retrieveAvailableSeatInDto.getDate() + retrieveAvailableSeatInDto.getStartTime();
		String endDate = retrieveAvailableSeatInDto.getDate() + retrieveAvailableSeatInDto.getEndTime();
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHMM");
		LocalDateTime startTime = LocalDateTime.parse(startDate, formatter);
		LocalDateTime endTime = LocalDateTime.parse(endDate, formatter);	
		
		//2. Validation Input
		if(startTime.isBefore(now)) {
			throw new RuntimeException("Current time can't be bigger than Start Time");
		}
		
		if(!startTime.isBefore(endTime)) {
			throw new RuntimeException("Start time can't be bigger than End Time");
		}
		
		//3. Retrieve Available Seat
		List<RetrieveAvailableSeatsByDateOutDto> retrieveAvailableSeatsByDateOutDtoList = seatMgmtRepository.retrieveAvailableSeatsByDate(
				RetrieveAvailableSeatsByDateInDto.builder()
				.startTime(startTime)
				.endTime(endTime)
				.skip(skip)
				.limit(limit)
				.build());
		
		return retrieveAvailableSeatsByDateOutDtoList.stream()
			    .map(dto -> {
			    	RetrieveAvailableSeatOutDto retrieveAvailableSeatOutDto = new RetrieveAvailableSeatOutDto();
			    	retrieveAvailableSeatOutDto.setBuildingName(dto.getBuildingName());
			    	retrieveAvailableSeatOutDto.setFloor(dto.getFloor());
			    	retrieveAvailableSeatOutDto.setSeatName(dto.getSeatName());
			        return retrieveAvailableSeatOutDto;
			    }).collect(Collectors.toList());
	}
	
	public ReturnSeatOutDto returnSeat(ReturnSeatInDto returnSeatInDto) throws Exception{
		//1. Declare variable
		int reservationId = returnSeatInDto.getReservationId();
		boolean result = true;
		
		//2. Validate Input
		if(reservationId == 0) {
			throw new BusinessException("Input Error");
		}
		
		//3. Retrieve Reservation
		RetrieveReservationOutDto retrieveReservationOutDto = reservationRepository.retrieveReservation(RetrieveReservationInDto.builder()
						.reservationId(reservationId)
						.build());
				
		if(retrieveReservationOutDto == null) {
			throw new BusinessException("No Reservation Information");
		}
		
		String status = retrieveReservationOutDto.getStatus();
		
		//4. Check In
		if(status == "IN_USE") {
			int updateCnt = reservationRepository.updateReservationStatus(UpdateReservationStatusDto.builder()
							.reservationId(reservationId)
							.status("COMPLETED")
							.build());
					
			if(updateCnt < 1) {
				throw new BusinessException("Update Error");
			}
		} else {
			result = false;
		}
				
		ReturnSeatOutDto returnSeatOutDto = ReturnSeatOutDto.builder()
				.successYn(result)
				.build();
		return returnSeatOutDto;
	}
	
	public RetrieveTotalAvailableSeatOutDto retriveTotalAvailableSeat(RetrieveTotalAvailableSeatInDto retrieveTotalAvailableSeatInDto) throws Exception{
		RetrieveTotalAvailableSeatOutDto result = new RetrieveTotalAvailableSeatOutDto();
		return result;
	}
	
	public MoveSeatOutDto moveSeat(MoveSeatInDto moveSeatInDto) throws Exception {
		//1. Declare variable
		int reservationId = moveSeatInDto.getReservationId();
		int moveSeatId = moveSeatInDto.getSeatId();
		
		//2. Validate Input
		if(reservationId == 0) {
			throw new BusinessException("Input Error");
		}
		
		if(moveSeatId == 0) {
			throw new BusinessException("Input Error");
		}
		
		//3. Retrieve Reservation
		RetrieveReservationOutDto retrieveReservationOutDto = reservationRepository.retrieveReservation(
				RetrieveReservationInDto.builder()
				.reservationId(reservationId)
				.build());
		
		if(retrieveReservationOutDto == null) {
			throw new BusinessException("No Reservation Information");
		}
		
		LocalDateTime startTime = retrieveReservationOutDto.getStartTime();
		LocalDateTime endTime = retrieveReservationOutDto.getEndTime();
		
		//4. Validate seat available
		RetrieveSeatAvailableOutDto retrieveSeatAvailableOutDto = reservationRepository.retrieveSeatAvailable(
				RetrieveSeatAvailableInDto.builder()
				.seatId(moveSeatId)
				.startTime(startTime)
				.endTime(endTime)
				.build());
		
		if(retrieveSeatAvailableOutDto != null) {
			throw new BusinessException("Already Reserved");
		}
		
		//5. Move Seat
		int updateCnt = reservationRepository.updateReservationSeat(UpdateReservationSeatDto.builder()
				.reservationId(reservationId)
				.seatId(moveSeatId)
				.build());
		
		if(updateCnt < 1) {
			throw new BusinessException("Exception Update");
		}
		
		int insertCnt = reservationRepository.insertReservation(InsertReservationDto.builder()
				.seatId(moveSeatId)
				.startTime(startTime)
				.endTime(endTime)
				.build());
		
		if(insertCnt < 1) {
			throw new BusinessException("Exception Update");
		}
		
		MoveSeatOutDto moveSeatOutDto = MoveSeatOutDto.builder()
				.successYn(true)
				.build();
		
		return moveSeatOutDto;
	}
	
	public CheckInSeatOutDto checkInSeat(CheckInSeatInDto checkInSeatInDto) throws Exception {
		//1. Declare variable
		int reservationId = checkInSeatInDto.getReservationId();
		boolean result = false;
		
		//2. Validate Input
		if(reservationId == 0) {
			throw new BusinessException("Input Error");
		}
		
		//3. Retrieve Reservation
		RetrieveReservationOutDto retrieveReservationOutDto = reservationRepository.retrieveReservation(RetrieveReservationInDto.builder()
				.reservationId(reservationId)
				.build());
		
		if(retrieveReservationOutDto == null) {
			throw new BusinessException("No Reservation Information");
		}
		
		String status = retrieveReservationOutDto.getStatus();
		
		//4. Check In
		if(status == "RESERVED") {
			int updateCnt = reservationRepository.updateReservationStatus(UpdateReservationStatusDto.builder()
					.reservationId(reservationId)
					.status("CANCELED")
					.build());
			
			if(updateCnt < 1) {
				throw new BusinessException("Update Error");
			}
		} else {
			result = false;
		}
		
		CheckInSeatOutDto checkInSeatOutDto = CheckInSeatOutDto.builder()
				.successYn(result)
				.build();
		
		return checkInSeatOutDto;
	}

	public List<RetrieveBuildingOutDto> retrieveBuilding(RetrieveBuildingInDto retrieveBuildingInDto) throws Exception {
		return seatMgmtRepository.retrieveBuilding(retrieveBuildingInDto);
	}
	
	public List<RetrieveFloorOutDto> retrieveFloor(RetrieveFloorInDto retrieveFloorInDto) throws Exception {
		return seatMgmtRepository.retrieveFloor(retrieveFloorInDto);
	}
	
	public List<RetrieveTotalSeatOutDto> retrieveTotalSeat(RetrieveTotalSeatInDto retrieveTotalSeatInDto) throws Exception {
		return seatMgmtRepository.retrieveTotalSeat(retrieveTotalSeatInDto);
	}
}
