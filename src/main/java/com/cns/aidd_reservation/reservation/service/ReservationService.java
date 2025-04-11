package com.cns.aidd_reservation.reservation.service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import com.cns.aidd_reservation.common.security.JwtUtil;
import com.cns.aidd_reservation.reservation.dto.*;
import com.cns.aidd_reservation.seatMgmt.dto.RetrieveAvailableSeatOutDto;
import com.cns.aidd_reservation.seatMgmt.repository.SeatMgmtRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cns.aidd_reservation.exception.BusinessException;
import com.cns.aidd_reservation.reservation.repository.ReservationRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReservationService {

	@Autowired
	private ReservationRepository reservationRepository;
    @Autowired
    private SeatMgmtRepository seatMgmtRepository;
    @Autowired
    private JwtUtil jwtUtil;

	public List<RetrieveCurrentReservationOutDto> retrieveCurrentReservation(String token, RetrieveCurrentReservationInDto retrieveCurrentReservationInDto) throws Exception{
		//1. Declare variable
		String employeeId = jwtUtil.extractSubject(token); //JWTToken get

		retrieveCurrentReservationInDto.setEmployeeId(Integer.parseInt(employeeId));
		return reservationRepository.retrieveCurrentReservation(retrieveCurrentReservationInDto);
	}

	public RegisterReservationOutDto registerReservation(String token, RegisterReservationInDto registerReservationInDto) throws Exception {
		//1. Declare variable
		String employeeId = jwtUtil.extractSubject(token); //JWTToken get

		int seatId = registerReservationInDto.getSeatId();
				
		LocalDateTime now = LocalDateTime.now();
		
		String startDate = registerReservationInDto.getDate() + registerReservationInDto.getStartTime();
		String endDate = registerReservationInDto.getDate() + registerReservationInDto.getEndTime();
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmm");
		LocalDateTime startTime = LocalDateTime.parse(startDate, formatter);
		LocalDateTime endTime = LocalDateTime.parse(endDate, formatter);
		
		//2. Validation Input
		if(startTime.isBefore(now)) {
			throw new BusinessException("Current time can't be bigger than Start Time");
		}
		
		if(!startTime.isBefore(endTime)) {
			throw new BusinessException("Start time can't be bigger than End Time");
		}

		//2. 인사 정보 API
		//TODO : Detail Logic

		//3. Retrieve Reservation (Employee)
		RetrieveReservationByEmployeeOutDto retrieveReservationByEmployeeOutDto = reservationRepository.retrieveReservationByEmployee(RetrieveReservationByEmployeeInDto.builder()
						.employeeId(Integer.parseInt(employeeId))
						.build());

		if(retrieveReservationByEmployeeOutDto != null) {
			throw new BusinessException("Already have Seat");
		}

		//4. Retrieve Seat Available
		RetrieveSeatAvailableOutDto retrieveSeatAvailableOutDto = reservationRepository.retrieveSeatAvailable(RetrieveSeatAvailableInDto.builder()
						.seatId(seatId)
						.startTime(startTime)
						.endTime(endTime)
						.build());

		if(retrieveSeatAvailableOutDto != null) {
			throw new BusinessException("Already Reserved");
		}

		//5. Register Reservation
		int insertCnt = reservationRepository.insertReservation(InsertReservationDto.builder()
				.seatId(seatId)
				.startTime(startTime)
				.endTime(endTime)
				.build());
		
		if(insertCnt < 1) {
			throw new BusinessException("Insert Exception");
		}

		return RegisterReservationOutDto.builder().successYn(true).build();
	}
	
	public CancelReservationOutDto cancelReservation(CancelReservationInDto cancelReservationInDto) throws Exception {
		//1. Declare Variable
		int reservationId = cancelReservationInDto.getReservationId();
		boolean result = true;

		//2. Validate Input
		if(0 == reservationId) {
			throw new BusinessException("INPUT ERROR");
		}

		//3. Retrieve Reservation
		RetrieveReservationOutDto retrieveReservationOutDto = reservationRepository.retrieveReservation(RetrieveReservationInDto.builder()
				.reservationId(reservationId)
				.build());

		if(retrieveReservationOutDto == null) {
			throw new BusinessException("No Reservation Information");
		}

		String status = retrieveReservationOutDto.getStatus();

		//3. Check Status / Cancel Seat
		if(status.equals("RESERVED")){
			int updateCnt = reservationRepository.updateReservationStatus(UpdateReservationStatusDto.builder()
					.reservationId(reservationId)
					.status("CANCELED")
					.build());

			if(updateCnt < 1) {
				throw new BusinessException("Update Exception");
			}
		} else {
			result = false;
		}

		return CancelReservationOutDto.builder().successYn(result).build();
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
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");

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
		int employeeId = 0; //JWTToken

		LocalDateTime curEndTime = retrieveReservationOutDto.getEndTime();
		LocalDateTime extendTime = curEndTime.plusHours(1);

		//TODO : Modify
		if (!curEndTime.toLocalDate().equals(extendTime.toLocalDate())) {
			throw new BusinessException("Next Day");
		}

		//4. Validation Extend Available
		RetrieveSeatAvailableOutDto retrieveSeatAvailableOutDto = reservationRepository.retrieveSeatAvailable(RetrieveSeatAvailableInDto.builder()
				.seatId(seatId)
				.startTime(curEndTime)
				.endTime(extendTime)
				.build());
		
		if(retrieveSeatAvailableOutDto != null) {
			throw new BusinessException("Already Reserved");
		}
		
		//5. Retrieve Total Time
		RetrieveTotalTimeOutDto retrieveTotalTimeOutDto = reservationRepository.retrieveTotalTime(RetrieveTotalTimeInDto.builder()
						.employeeId(employeeId)
						.date(curEndTime.format(formatter))
						.build());

		if(retrieveTotalTimeOutDto != null) {
			if(retrieveTotalTimeOutDto.getTotalTime() >= 8)	{
				throw new BusinessException("Exceed 8 Hours");
			}
		}

		//6. Extend
		int updateCnt = reservationRepository.updateReservationTime(UpdateReservationTimeDto.builder()
				.reservationId(reservationId)
				.endTime(extendTime)
				.build());
		
		if(updateCnt < 1) {
			throw new BusinessException("Already Reserved");
		}

		return ExtendReservationTimeOutDto.builder()
				.successYn(true)
				.build();
	}
	
	public List<RetrieveReservationHistoryOutDto> retrieveReservationHistory(String token, RetrieveReservationHistoryInDto retrieveReservationHistoryInDto) throws Exception {
		//1. Declare Variable
		String startDate = retrieveReservationHistoryInDto.getStartDate();
		String endDate = retrieveReservationHistoryInDto.getEndDate();

		String employeeId = jwtUtil.extractSubject(token);

		//paging variable
		int skip = retrieveReservationHistoryInDto.getSkip() != null ? retrieveReservationHistoryInDto.getSkip() : 0;
		int limit = retrieveReservationHistoryInDto.getLimit() != null ? retrieveReservationHistoryInDto.getLimit() : 5;

		List<RetrieveReservationHistoryListOutDto> retrieveReservationHistoryListOutDto = reservationRepository.retrieveReservationHistoryList(RetrieveReservationHistoryListInDto.builder()
				.employeeId(Integer.parseInt(employeeId))
				.startDate(startDate)
				.endDate(endDate)
				.skip(skip)
				.limit(limit)
				.build());
		
		return retrieveReservationHistoryListOutDto.stream()
				.map(dto -> RetrieveReservationHistoryOutDto.builder()
							.date(dto.getDate())
							.seatId(dto.getSeatId())
							.seatName(dto.getSeatName())
							.startTime(dto.getStartTime())
							.endTime(dto.getEndTime())
							.status(dto.getStatus()).build()
				).collect(Collectors.toList());
	}
}
