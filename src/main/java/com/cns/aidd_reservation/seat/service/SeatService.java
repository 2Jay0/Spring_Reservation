package com.cns.aidd_reservation.seat.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cns.aidd_reservation.reservation.dto.RetrieveReservationInDto;
import com.cns.aidd_reservation.reservation.dto.RetrieveReservationOutDto;
import com.cns.aidd_reservation.reservation.dto.RetrieveSeatAvailableInDto;
import com.cns.aidd_reservation.reservation.dto.RetrieveSeatAvailableOutDto;
import com.cns.aidd_reservation.reservation.dto.UpdateReservationSeatDto;
import com.cns.aidd_reservation.reservation.repository.ReservationRepository;
import com.cns.aidd_reservation.seat.dto.MoveSeatInDto;
import com.cns.aidd_reservation.seat.dto.MoveSeatOutDto;
import com.cns.aidd_reservation.seat.dto.RetrieveAvailableSeatsByDateInDto;
import com.cns.aidd_reservation.seat.dto.RetrieveAvailableSeatsByDateOutDto;
import com.cns.aidd_reservation.seat.dto.RetrieveTotalAvailableSeatInDto;
import com.cns.aidd_reservation.seat.dto.RetrieveTotalAvailableSeatOutDto;
import com.cns.aidd_reservation.seat.dto.ReturnSeatInDto;
import com.cns.aidd_reservation.seat.dto.ReturnSeatOutDto;
import com.cns.aidd_reservation.seat.repository.SeatRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SeatService {
	
	@Autowired
	private SeatRepository seatRepository;
	
	@Autowired
	private ReservationRepository reservationRepository;
	
	public List<RetrieveAvailableSeatsByDateOutDto> retrieveAvailableSeatsByDate(RetrieveAvailableSeatsByDateInDto retrieveAvailableSeatsByDateInDto) throws Exception{
		//1. Declare Variable
		LocalDateTime now = LocalDateTime.now();
				
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHMM");
		LocalDateTime startTime = LocalDateTime.parse(retrieveAvailableSeatsByDateInDto.getStartTime(), formatter);
		LocalDateTime endTime = LocalDateTime.parse(retrieveAvailableSeatsByDateInDto.getStartTime(), formatter);	
		
		//2. Validation Input
		if(startTime.isBefore(now)) {
			throw new RuntimeException("Current time can't be bigger than Start Time");
		}
		
		if(!startTime.isBefore(endTime)) {
			throw new RuntimeException("Start time can't be bigger than End Time");
		}
		
		//3. Retrieve Available Seat
		
		List<RetrieveAvailableSeatsByDateOutDto> result = new ArrayList();
		return result;
	}
	
	public ReturnSeatOutDto returnSeat(ReturnSeatInDto seatInDto) throws Exception{
		ReturnSeatOutDto result = new ReturnSeatOutDto();
		return result;
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
			throw new RuntimeException("Input Error");
		}
		
		if(moveSeatId == 0) {
			throw new RuntimeException("Input Error");
		}
		
		//3. Retrieve Reservation
		RetrieveReservationOutDto retrieveReservationOutDto = reservationRepository.retrieveReservation(
				RetrieveReservationInDto.builder()
				.reservationId(reservationId)
				.build());
		
		if(retrieveReservationOutDto == null) {
			throw new RuntimeException("No Reservation Information");
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
			throw new RuntimeException("Already Reserved");
		}
		
		//5. Move Seat
		int updateCnt = reservationRepository.updateReservationSeat(UpdateReservationSeatDto.builder()
				.reservationId(reservationId)
				.seatId(moveSeatId)
				.build());
		
		if(updateCnt < 1) {
			throw new RuntimeException("Exception Update");
		}
		
		MoveSeatOutDto moveSeatOutDto = MoveSeatOutDto.builder()
				.seatId(moveSeatId)
				.successYn(true)
				.build();
		
		return moveSeatOutDto;
	}
}
