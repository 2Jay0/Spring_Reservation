package com.cns.aidd_reservation.reservation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cns.aidd_reservation.reservation.dto.CancelReservationInDto;
import com.cns.aidd_reservation.reservation.dto.CancelReservationOutDto;
import com.cns.aidd_reservation.reservation.dto.ExtendReservationTimeInDto;
import com.cns.aidd_reservation.reservation.dto.ExtendReservationTimeOutDto;
import com.cns.aidd_reservation.reservation.dto.RegisterReservationInDto;
import com.cns.aidd_reservation.reservation.dto.RegisterReservationOutDto;
import com.cns.aidd_reservation.reservation.dto.RetrieveRemainSeatTimeInDto;
import com.cns.aidd_reservation.reservation.dto.RetrieveRemainSeatTimeOutDto;
import com.cns.aidd_reservation.reservation.dto.RetrieveReservationHistoryInDto;
import com.cns.aidd_reservation.reservation.dto.RetrieveReservationHistoryOutDto;
import com.cns.aidd_reservation.reservation.service.ReservationService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/reservations")
@RequiredArgsConstructor
public class ReservationController {
	@Autowired
	private final ReservationService reservationService;
	
	@RequestMapping(method = RequestMethod.POST, path = "/register")
	public RegisterReservationOutDto RegisterReservation(@RequestBody RegisterReservationInDto registerReservationInDto) throws Exception{
		return reservationService.registerReservation(registerReservationInDto);
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
	public RetrieveReservationHistoryOutDto retrieveReservationHistory(@RequestBody RetrieveReservationHistoryInDto retrieveReservationHistoryInDto) throws Exception {
		return reservationService.retrieveReservationHistory(retrieveReservationHistoryInDto);
	}
}
