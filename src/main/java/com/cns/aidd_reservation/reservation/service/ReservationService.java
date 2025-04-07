package com.cns.aidd_reservation.reservation.service;

import java.time.Duration;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cns.aidd_reservation.reservation.dto.CancelReservationInDto;
import com.cns.aidd_reservation.reservation.dto.CancelReservationOutDto;
import com.cns.aidd_reservation.reservation.dto.ExtendReservationTimeInDto;
import com.cns.aidd_reservation.reservation.dto.ExtendReservationTimeOutDto;
import com.cns.aidd_reservation.reservation.dto.RegisterReservationInDto;
import com.cns.aidd_reservation.reservation.dto.RegisterReservationOutDto;
import com.cns.aidd_reservation.reservation.dto.RetrieveRemainSeatTimeInDto;
import com.cns.aidd_reservation.reservation.dto.RetrieveRemainSeatTimeOutDto;
import com.cns.aidd_reservation.reservation.dto.RetrieveReservationByEmployeeInDto;
import com.cns.aidd_reservation.reservation.dto.RetrieveReservationByEmployeeOutDto;
import com.cns.aidd_reservation.reservation.dto.RetrieveReservationBySeatTimeInDto;
import com.cns.aidd_reservation.reservation.dto.RetrieveReservationBySeatTimeOutDto;
import com.cns.aidd_reservation.reservation.dto.RetrieveReservationInDto;
import com.cns.aidd_reservation.reservation.dto.RetrieveReservationOutDto;
import com.cns.aidd_reservation.reservation.dto.UpdateReservationStatusDto;
import com.cns.aidd_reservation.reservation.repository.ReservationRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReservationService {

	@Autowired
	private ReservationRepository reservationRepository;
	
	public RegisterReservationOutDto registerReservation(RegisterReservationInDto cancelReservationInDto) throws Exception {
		RegisterReservationOutDto result = new RegisterReservationOutDto();
		
		//1. 입력값 검증
		// 지난 날짜 및 시간 불가, 시작 및 종료 시간 역전 불가
		
		//2. 인사관리 API 연결
		
		//3. 예약정보 조회(직원ID)
		return result;
	}
	
	public CancelReservationOutDto cancelReservation(CancelReservationInDto cancelReservationInDto) throws Exception {
		CancelReservationOutDto result = new CancelReservationOutDto();
		
		//1. 입력값 검증
		int reservationId = cancelReservationInDto.getReservationId();
		
		if(0 == reservationId) {
			throw new RuntimeException();
		}
		//2. 좌석 예약 취소
		int updateCnt = reservationRepository.updateReservationStatus(UpdateReservationStatusDto.builder()
				.reservationId(reservationId)
				.status("CANCELED")
				.build());
		
		if(updateCnt < 1) {
			throw new RuntimeException();
		}
		
		return result;
	}
	
	public RetrieveRemainSeatTimeOutDto retrieveRemainSeatTime(RetrieveRemainSeatTimeInDto retrieveRemainSeatTimeInDto) throws Exception {
		//1. Declare variable
		long remainTime = 0L;
		
		//2. Validation Input
		int seatId = retrieveRemainSeatTimeInDto.getSeatId();
		
		if(0 == seatId) {
			throw new RuntimeException();
		}
		
		//3. Retrieve current seat reservation
		RetrieveReservationBySeatTimeOutDto retrieveReservationBySeatTimeOutDto = reservationRepository.retrieveReservationBySeatTime(
				RetrieveReservationBySeatTimeInDto.builder()
				.seatId(seatId)
				.build());
		
		// 4. Calculate remainTime
		if (retrieveReservationBySeatTimeOutDto == null) {
		    remainTime = 0L;
		} else {
		    LocalDateTime endTime = retrieveReservationBySeatTimeOutDto.getEndTime();
		    LocalDateTime now = LocalDateTime.now();
		    
		    if (endTime.isAfter(now)) {
		        remainTime = Duration.between(now, endTime).toMillis();
		    } else {
		        remainTime = 0L;
		    }
		}
		
		RetrieveRemainSeatTimeOutDto retrieveRemainSeatTimeOutDto = RetrieveRemainSeatTimeOutDto.builder()
				.remainTime(remainTime)
				.build();
		return retrieveRemainSeatTimeOutDto;
	}
	
	public ExtendReservationTimeOutDto extendReservationTime(ExtendReservationTimeInDto extendReservationTimeInDto) throws Exception {
		//1. Declare variable
		int reservationId = extendReservationTimeInDto.getReservationId();
		int seatId = 0;
		String date = "";
		
		//2. Validation Input
		if (reservationId == 0) {
		    throw new RuntimeException("Invalid reservationId");
		}
		
		//3. Retrieve reservation
		RetrieveReservationOutDto retrieveReservationOutDto = reservationRepository.retrieveReservation(RetrieveReservationInDto.builder()
				.reservationId(reservationId)
				.build());
		
		if(retrieveReservationOutDto == null) {
			throw new RuntimeException("No Reservation Information");
		}
		
		//예약정보 변수 셋팅
		
		//4. Validation Time
		
		//5. Validation Extend Available
		
		//6. Extend
		
		ExtendReservationTimeOutDto result = new ExtendReservationTimeOutDto();
		return result;
	}
	
	public RetrieveReservationByEmployeeOutDto retrieveReservationByEmployee(RetrieveReservationByEmployeeInDto retrieveReservationByEmployeeInDto) throws Exception {
		RetrieveReservationByEmployeeOutDto result = new RetrieveReservationByEmployeeOutDto();
		return result;
	}
}
