package com.cns.aidd_reservation.seat.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cns.aidd_reservation.seat.dto.MoveSeatInDto;
import com.cns.aidd_reservation.seat.dto.MoveSeatOutDto;
import com.cns.aidd_reservation.seat.dto.RetrieveAvailableSeatsByDateInDto;
import com.cns.aidd_reservation.seat.dto.RetrieveAvailableSeatsByDateOutDto;
import com.cns.aidd_reservation.seat.dto.RetrieveTotalAvailableSeatInDto;
import com.cns.aidd_reservation.seat.dto.RetrieveTotalAvailableSeatOutDto;
import com.cns.aidd_reservation.seat.dto.ReturnSeatInDto;
import com.cns.aidd_reservation.seat.dto.ReturnSeatOutDto;
import com.cns.aidd_reservation.seat.service.SeatService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/seats")
@RequiredArgsConstructor
public class SeatController {
	@Autowired
	private final SeatService seatService;
	
	@RequestMapping(method = RequestMethod.GET, path = "/available")
	public List<RetrieveAvailableSeatsByDateOutDto> retrieveAvailableSeatsByDate(@RequestBody RetrieveAvailableSeatsByDateInDto retrieveAvailableSeatsByDateInDto) throws Exception{
		return seatService.retrieveAvailableSeatsByDate(retrieveAvailableSeatsByDateInDto);
	}
	
	@RequestMapping(method = RequestMethod.POST, path = "/return")
	public ReturnSeatOutDto returnSeat(@RequestBody ReturnSeatInDto returnSeatInDto) throws Exception{
		return seatService.returnSeat(returnSeatInDto);
	}
	
	@RequestMapping(method = RequestMethod.GET, path = "/total_available")
	public RetrieveTotalAvailableSeatOutDto retriveTotalAvailableSeat(@RequestBody RetrieveTotalAvailableSeatInDto retrieveTotalAvailableSeatInDto) throws Exception{
		return seatService.retriveTotalAvailableSeat(retrieveTotalAvailableSeatInDto);
	}
	
	@RequestMapping(method = RequestMethod.POST, path = "/move")
	public MoveSeatOutDto moveSeat(MoveSeatInDto moveSeatInDto) throws Exception {
		return seatService.moveSeat(moveSeatInDto);
	}
}
