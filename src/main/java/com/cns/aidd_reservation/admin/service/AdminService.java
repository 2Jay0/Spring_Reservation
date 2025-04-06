package com.cns.aidd_reservation.admin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cns.aidd_reservation.admin.dto.ForceCancelSeatInDto;
import com.cns.aidd_reservation.admin.dto.ForceCancelSeatOutDto;
import com.cns.aidd_reservation.admin.dto.ForceMoveSeatInDto;
import com.cns.aidd_reservation.admin.dto.ForceMoveSeatOutDto;
import com.cns.aidd_reservation.penalty.dto.RegisterPenaltyLogDto;
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
		ForceCancelSeatOutDto result = new ForceCancelSeatOutDto(); 
		result.setSuccessYn(false);
		
		//1. 입력값 검증
		int reservationId = forceCancelSeatInDto.getReservationId();
		if(0 == reservationId) {
			throw new RuntimeException();
		}
		
		//2. 예약 정보 조회
		RetrieveReservationInDto retrieveReservationInDto = new RetrieveReservationInDto();
		retrieveReservationInDto.setReservationId(reservationId);
		
		RetrieveReservationOutDto retrieveReservationOutDto = reservationRepository.retrieveReservation(retrieveReservationInDto);
		if(retrieveReservationOutDto == null) {
			throw new RuntimeException();
		}
		
		//3. 예약 상태 변경
		UpdateReservationStatusDto updateReservationStatusDto = new UpdateReservationStatusDto();
		updateReservationStatusDto.setReservationId(reservationId);
		updateReservationStatusDto.setStatus("FORCED_CANCEL");
		
		int updateCnt = reservationRepository.updateReservationStatus(updateReservationStatusDto);
		if(updateCnt < 1) {
			throw new RuntimeException();
		}
		
		//4. 예약 성공
		result.setSuccessYn(true);
		result.setStatus("FORCED_CANCEL");
		return result;
	}
	
	public ForceMoveSeatOutDto forceMoveSeat(ForceMoveSeatInDto moveSeatForcedInDto) throws Exception{
		ForceMoveSeatOutDto result = new ForceMoveSeatOutDto();
		
		//1. 입력값 검증
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
		
		//2. 가능 예약 정보 조회
		//private 함수로 종료예정시간 get하는 함수
		String newEndTime = "";
		RetrieveSeatAvailableInDto retrieveSeatAvailableInDto = new RetrieveSeatAvailableInDto();
		retrieveSeatAvailableInDto.setSeatId(seatId);
		retrieveSeatAvailableInDto.setDate(null);
		retrieveSeatAvailableInDto.setStartTime(newEndTime);
		retrieveSeatAvailableInDto.setEndTime(newEndTime);
		
		RetrieveSeatAvailableOutDto retrieveSeatAvailableOutDto = reservationRepository.retrieveSeatAvailable(retrieveSeatAvailableInDto);
		if(retrieveSeatAvailableOutDto != null) {
			throw new RuntimeException();
		}
		
		//3. 예약 정보 수정
		UpdateReservationSeatDto updateReservationSeatDto = new UpdateReservationSeatDto();
		updateReservationSeatDto.setReservationId(reservationId);
		updateReservationSeatDto.setReservationId(seatId);
		updateReservationSeatDto.setEndTime(newEndTime);
		
		int updateCnt = reservationRepository.updateReservationSeat(updateReservationSeatDto);
		if(updateCnt < 1) {
			throw new RuntimeException();
		}
		
		//4. 패널티 로그 등록
		RegisterPenaltyLogDto registerPenaltyLogDto = new RegisterPenaltyLogDto();
		registerPenaltyLogDto.setReservationId(reservationId);
		registerPenaltyLogDto.setEmployeeId(reservationId);
		registerPenaltyLogDto.setGivenAt(null);
		registerPenaltyLogDto.setReason(reason);
		
		int insertCnt = penaltyRepository.registerPenaltyLog(registerPenaltyLogDto);
		if(insertCnt < 1) {
			throw new RuntimeException();
		}
		
		return result;
	}
}
