package com.cns.aidd_reservation.admin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cns.aidd_reservation.admin.dto.ForceCancelSeatInDto;
import com.cns.aidd_reservation.admin.dto.ForceCancelSeatOutDto;
import com.cns.aidd_reservation.admin.dto.ForceMoveSeatInDto;
import com.cns.aidd_reservation.admin.dto.ForceMoveSeatOutDto;
import com.cns.aidd_reservation.penalty.dto.InsertPenaltyLogDto;
import com.cns.aidd_reservation.penalty.repository.PenaltyRepository;
import com.cns.aidd_reservation.reservation.dto.RetrieveReservationInDto;
import com.cns.aidd_reservation.reservation.dto.RetrieveReservationOutDto;
import com.cns.aidd_reservation.reservation.dto.RetrieveSeatAvailableInDto;
import com.cns.aidd_reservation.reservation.dto.RetrieveSeatAvailableOutDto;
import com.cns.aidd_reservation.reservation.dto.UpdateReservationSeatDto;
import com.cns.aidd_reservation.reservation.dto.UpdateReservationStatusDto;
import com.cns.aidd_reservation.reservation.repository.ReservationRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminService {
	
	@Autowired
	private ReservationRepository reservationRepository;
	
	@Autowired
	private PenaltyRepository penaltyRepository;
	
	public ForceCancelSeatOutDto forceCancelSeat(ForceCancelSeatInDto forceCancelSeatInDto) throws Exception{
		//1. Declare Variable
		String status = "FORCED_CANCELED";
		int reservationId = forceCancelSeatInDto.getReservationId();
		
		//2. Validation Input
		if(reservationId == 0) {
			throw new RuntimeException();
		}
		
		//3. Retrieve Reservation
		RetrieveReservationOutDto retrieveReservationOutDto = reservationRepository.retrieveReservation(
				RetrieveReservationInDto.builder()
				.reservationId(reservationId)
				.build());
		
		if(retrieveReservationOutDto == null) {
			throw new RuntimeException();
		}
		
		//4. Update Reservation Status
		int updateCnt = reservationRepository.updateReservationStatus(UpdateReservationStatusDto.builder()
				.reservationId(reservationId)
				.status(status)
				.build());
		
		if(updateCnt < 1) {
			throw new RuntimeException();
		}
		
		ForceCancelSeatOutDto forceCancelSeatOutDto = ForceCancelSeatOutDto.builder()
				.status(status)
				.successYn(true)
				.build(); 
		return forceCancelSeatOutDto;
	}
	
	public ForceMoveSeatOutDto forceMoveSeat(ForceMoveSeatInDto moveSeatForcedInDto) throws Exception{
		//1. Validation Input
		int reservationId = moveSeatForcedInDto.getReservationId();
		int seatId = moveSeatForcedInDto.getSeatId();
		String reason = moveSeatForcedInDto.getReason();
		
		if(0 == reservationId) {
			throw new RuntimeException();
		}
		
		if(0 == seatId) {
			throw new RuntimeException();
		}
		
		if("".equals(reason)) {
			throw new RuntimeException();
		}
		
		//TODO
		//2. Validation Seat Available
		//private 함수로 종료예정시간 get하는 함수
		
		RetrieveSeatAvailableOutDto retrieveSeatAvailableOutDto = reservationRepository.retrieveSeatAvailable(RetrieveSeatAvailableInDto.builder()
				.seatId(seatId)
				.startTime(null)
				.endTime(null)
				.build());
		
		if(retrieveSeatAvailableOutDto != null) {
			throw new RuntimeException("Already Reserved Seat");
		}
		
		//3. Update Reservation
		int updateCnt = reservationRepository.updateReservationSeat(UpdateReservationSeatDto.builder()
				.reservationId(reservationId)
				.seatId(seatId)
				.endTime(null)
				.build());
		
		if(updateCnt < 1) {
			throw new RuntimeException();
		}
		
		//TODO
		//4. Insert Penalty Log
		int insertCnt = penaltyRepository.insertPenaltyLog(InsertPenaltyLogDto.builder()
				.reservationId(reservationId)
				.employeeId(updateCnt)
				.givenAt(null)
				.reason(reason)
				.build());
		
		if(insertCnt < 1) {
			throw new RuntimeException();
		}
		
		//TODO
		ForceMoveSeatOutDto forceMoveSeatOutDto = ForceMoveSeatOutDto.builder()
				.status(null)
				.build();
		return forceMoveSeatOutDto;
	}
}
