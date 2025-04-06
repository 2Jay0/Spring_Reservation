package com.cns.aidd_reservation.seat.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	
	public List<RetrieveAvailableSeatsByDateOutDto> retrieveAvailableSeatsByDate(RetrieveAvailableSeatsByDateInDto retrieveAvailableSeatsByDateInDto) throws Exception{
		List<RetrieveAvailableSeatsByDateOutDto> result = new ArrayList();
		
		//1. 입력값 검증
		// 지난 날짜 및 시간 불가, 시작 및 종료 시각 역전 불가
		
		//2. 가능 좌석 조회(날짜)
		
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
		MoveSeatOutDto result = new MoveSeatOutDto();
		return result;
	}
}
