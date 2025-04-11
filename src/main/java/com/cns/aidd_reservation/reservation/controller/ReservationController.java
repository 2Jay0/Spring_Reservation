package com.cns.aidd_reservation.reservation.controller;

import java.util.List;

import com.cns.aidd_reservation.reservation.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.cns.aidd_reservation.reservation.service.ReservationService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/reservations")
@RequiredArgsConstructor
public class ReservationController {
	@Autowired
	private final ReservationService reservationService;

	@RequestMapping(method = RequestMethod.GET, path = "/register")
	public List<RetrieveCurrentReservationOutDto> retrieveCurrentReservation(@RequestHeader("Authorization") String token
			, @RequestBody RetrieveCurrentReservationInDto retrieveCurrentReservationInDto) throws Exception{
		return reservationService.retrieveCurrentReservation(token, retrieveCurrentReservationInDto);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/register")
	public RegisterReservationOutDto RegisterReservation(@RequestHeader("Authorization") String token
														 , @RequestBody RegisterReservationInDto registerReservationInDto) throws Exception{
		return reservationService.registerReservation(token, registerReservationInDto);
	}
	
	@RequestMapping(method = RequestMethod.POST, path = "/cancel")
	public CancelReservationOutDto cancelReservation(@RequestBody CancelReservationInDto cancelReservationInDto) throws Exception{
		return reservationService.cancelReservation(cancelReservationInDto);
	}
	
	@RequestMapping(method = RequestMethod.GET, path = "/remaining_time")
	public RetrieveRemainSeatTimeOutDto cancelReservation(@RequestBody RetrieveRemainSeatTimeInDto retrieveRemainSeatTimeInDto) throws Exception{
		return reservationService.retrieveRemainSeatTime(retrieveRemainSeatTimeInDto);
	}
	
	@RequestMapping(method = RequestMethod.POST, path = "/extend")
	public ExtendReservationTimeOutDto extendReservationTime(@RequestBody ExtendReservationTimeInDto extendReservationTimeInDto) throws Exception {
		return reservationService.extendReservationTime(extendReservationTimeInDto);
	}
	
	@RequestMapping(method = RequestMethod.GET, path = "/history")
	public List<RetrieveReservationHistoryOutDto> retrieveReservationHistory(@RequestHeader("Authorization") String token
																			 , @RequestBody RetrieveReservationHistoryInDto retrieveReservationHistoryInDto) throws Exception {
		return reservationService.retrieveReservationHistory(token, retrieveReservationHistoryInDto);
	}
}
