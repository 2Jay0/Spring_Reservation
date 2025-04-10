package com.cns.aidd_reservation.reservation.service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cns.aidd_reservation.exception.BusinessException;
import com.cns.aidd_reservation.reservation.dto.CancelReservationInDto;
import com.cns.aidd_reservation.reservation.dto.CancelReservationOutDto;
import com.cns.aidd_reservation.reservation.dto.ExtendReservationTimeInDto;
import com.cns.aidd_reservation.reservation.dto.ExtendReservationTimeOutDto;
import com.cns.aidd_reservation.reservation.dto.InsertReservationDto;
import com.cns.aidd_reservation.reservation.dto.RegisterReservationInDto;
import com.cns.aidd_reservation.reservation.dto.RegisterReservationOutDto;
import com.cns.aidd_reservation.reservation.dto.RetrieveRemainSeatTimeInDto;
import com.cns.aidd_reservation.reservation.dto.RetrieveRemainSeatTimeOutDto;
import com.cns.aidd_reservation.reservation.dto.RetrieveReservationBySeatTimeInDto;
import com.cns.aidd_reservation.reservation.dto.RetrieveReservationBySeatTimeOutDto;
import com.cns.aidd_reservation.reservation.dto.RetrieveReservationHistoryInDto;
import com.cns.aidd_reservation.reservation.dto.RetrieveReservationHistoryOutDto;
import com.cns.aidd_reservation.reservation.dto.RetrieveReservationInDto;
import com.cns.aidd_reservation.reservation.dto.RetrieveReservationOutDto;
import com.cns.aidd_reservation.reservation.dto.RetrieveSeatAvailableInDto;
import com.cns.aidd_reservation.reservation.dto.RetrieveSeatAvailableOutDto;
import com.cns.aidd_reservation.reservation.dto.UpdateReservationStatusDto;
import com.cns.aidd_reservation.reservation.dto.UpdateReservationTimeDto;
import com.cns.aidd_reservation.reservation.repository.ReservationRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReservationService {

	@Autowired
	private ReservationRepository reservationRepository;
	
	public RegisterReservationOutDto registerReservation(RegisterReservationInDto registerReservationInDto) throws Exception {	
		//1. Declare variable
		int seatId = registerReservationInDto.getSeatId();
				
		LocalDateTime now = LocalDateTime.now();
		
		String startDate = registerReservationInDto.getDate() + registerReservationInDto.getStartTime();
		String endDate = registerReservationInDto.getDate() + registerReservationInDto.getEndTime();
		
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
		//2. 인사관리 API 연결
		
		//3. 예약정보 조회(직원ID)
		
		//4. Register Reservation
		int insertCnt = reservationRepository.insertReservation(InsertReservationDto.builder()
				.seatId(seatId)
				.startTime(startTime)
				.endTime(endTime)
				.build());
		
		if(insertCnt < 1) {
			throw new RuntimeException("Insert Exception");
		}
		RegisterReservationOutDto registerReservationOutDto = RegisterReservationOutDto.builder()
				.successYn(true)
				.build();
		return registerReservationOutDto;
	}
	
	public CancelReservationOutDto cancelReservation(CancelReservationInDto cancelReservationInDto) throws Exception {
		//1. Validate Input
		int reservationId = cancelReservationInDto.getReservationId();
		
		if(0 == reservationId) {
			throw new RuntimeException();
		}
		//2. Cancel Seat
		int updateCnt = reservationRepository.updateReservationStatus(UpdateReservationStatusDto.builder()
				.reservationId(reservationId)
				.status("CANCELED")
				.build());
		
		if(updateCnt < 1) {
			throw new RuntimeException();
		}
		
		CancelReservationOutDto result = new CancelReservationOutDto();
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
		
		//2. Validation Input
		if (reservationId == 0) {
		    throw new BusinessException("Invalid reservationId");
		}
		
		//3. Retrieve reservation
		RetrieveReservationOutDto retrieveReservationOutDto = reservationRepository.retrieveReservation(RetrieveReservationInDto.builder()
				.reservationId(reservationId)
				.build());
		
		if(retrieveReservationOutDto == null) {
			throw new BusinessException("No Reservation Information");
		}
		
		int seatId = retrieveReservationOutDto.getSeatId();
		int employeeId = retrieveReservationOutDto.getEmployeeId();
		LocalDateTime curEndTime = retrieveReservationOutDto.getEndTime();
		LocalDateTime extendTime = curEndTime.plusHours(1);
		
		//4. Validation Extend Available
		RetrieveSeatAvailableOutDto retrieveSeatAvailableOutDto = reservationRepository.retrieveSeatAvailable(RetrieveSeatAvailableInDto.builder()
				.seatId(seatId)
				.startTime(curEndTime)
				.endTime(extendTime)
				.build());
		
		if(retrieveSeatAvailableOutDto != null) {
			throw new BusinessException("Already Reserved");
		}
		
		//5. Validate 
		//오늘일자, 사원 => sum값
		
		//6. Extend
		int updateCnt = reservationRepository.updateReservationTime(UpdateReservationTimeDto.builder()
				.reservationId(reservationId)
				.endTime(extendTime)
				.build());
		
		if(updateCnt < 1) {
			throw new RuntimeException("Already Reserved");
		}
		
		ExtendReservationTimeOutDto extendReservationTimeOutDto = ExtendReservationTimeOutDto.builder()
				.successYn(true)
				.build();
		return extendReservationTimeOutDto;
	}
	
	public List<RetrieveReservationHistoryOutDto> retrieveReservationHistory(RetrieveReservationHistoryInDto retrieveReservationHistoryInDto) throws Exception {
		//1. Declare Variable
		String startDate = retrieveReservationHistoryInDto.getStartDate();
		String endDate = retrieveReservationHistoryInDto.getEndDate();
	
		//paging variable
		int skip = retrieveReservationHistoryInDto.getSkip() != null ? retrieveReservationHistoryInDto.getSkip() : 0;
		int limit = retrieveReservationHistoryInDto.getLimit() != null ? retrieveReservationHistoryInDto.getLimit() : 5;
		
//		List<RetrieveReservationHistoryListOutDto> retrieveReservationHistoryOutDto = reservationRepository.retrieveReservationHistoryList(RetrieveReservationHistoryListInDto.builder()
//				.startDate(startDate)
//				.endDate(endDate)
//				.skip(skip)
//				.limit(limit)
//				.build());
		
		return null;
	}
}
