package com.cns.aidd_reservation.seat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	
	public RetrieveAvailableSeatsByDateOutDto retrieveAvailableSeatsByDate(RetrieveAvailableSeatsByDateInDto retrieveAvailableSeatsByDateInDto) throws Exception{
		RetrieveAvailableSeatsByDateOutDto result = new RetrieveAvailableSeatsByDateOutDto();
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
}
