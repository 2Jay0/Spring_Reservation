package com.cns.aidd_reservation.reservation.service;

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
		return result;
	}
	
	public RetrieveRemainSeatTimeOutDto retrieveRemainSeatTime(RetrieveRemainSeatTimeInDto retrieveRemainSeatTimeInDto) throws Exception {
		RetrieveRemainSeatTimeOutDto result = new RetrieveRemainSeatTimeOutDto();
		return result;
	}
	
	public ExtendReservationTimeOutDto extendReservationTime(ExtendReservationTimeInDto extendReservationTimeInDto) throws Exception {
		ExtendReservationTimeOutDto result = new ExtendReservationTimeOutDto();
		return result;
	}
	
	public RetrieveReservationByEmployeeOutDto retrieveReservationByEmployee(RetrieveReservationByEmployeeInDto retrieveReservationByEmployeeInDto) throws Exception {
		RetrieveReservationByEmployeeOutDto result = new RetrieveReservationByEmployeeOutDto();
		return result;
	}
}
