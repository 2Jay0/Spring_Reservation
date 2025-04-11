package com.cns.aidd_reservation.seatMgmt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cns.aidd_reservation.seatMgmt.dto.CheckInSeatInDto;
import com.cns.aidd_reservation.seatMgmt.dto.CheckInSeatOutDto;
import com.cns.aidd_reservation.seatMgmt.dto.MoveSeatInDto;
import com.cns.aidd_reservation.seatMgmt.dto.MoveSeatOutDto;
import com.cns.aidd_reservation.seatMgmt.dto.RetrieveAvailableSeatInDto;
import com.cns.aidd_reservation.seatMgmt.dto.RetrieveAvailableSeatOutDto;
import com.cns.aidd_reservation.seatMgmt.dto.RetrieveBuildingInDto;
import com.cns.aidd_reservation.seatMgmt.dto.RetrieveBuildingOutDto;
import com.cns.aidd_reservation.seatMgmt.dto.RetrieveFloorInDto;
import com.cns.aidd_reservation.seatMgmt.dto.RetrieveFloorOutDto;
import com.cns.aidd_reservation.seatMgmt.dto.RetrieveTotalAvailableSeatInDto;
import com.cns.aidd_reservation.seatMgmt.dto.RetrieveTotalAvailableSeatOutDto;
import com.cns.aidd_reservation.seatMgmt.dto.RetrieveTotalSeatInDto;
import com.cns.aidd_reservation.seatMgmt.dto.RetrieveTotalSeatOutDto;
import com.cns.aidd_reservation.seatMgmt.dto.ReturnSeatInDto;
import com.cns.aidd_reservation.seatMgmt.dto.ReturnSeatOutDto;
import com.cns.aidd_reservation.seatMgmt.service.SeatMgmtService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/seats")
@RequiredArgsConstructor
public class SeatMgmtController {
	@Autowired
	private final SeatMgmtService seatMgmtService;
	
	@RequestMapping(method = RequestMethod.GET, path = "/available")
	public List<RetrieveAvailableSeatOutDto> retrieveAvailableSeat(@RequestBody RetrieveAvailableSeatInDto retrieveAvailableSeatInDto) throws Exception{
		return seatMgmtService.retrieveAvailableSeat(retrieveAvailableSeatInDto);
	}
	
	@RequestMapping(method = RequestMethod.POST, path = "/return")
	public ReturnSeatOutDto returnSeat(@RequestBody ReturnSeatInDto returnSeatInDto) throws Exception{
		return seatMgmtService.returnSeat(returnSeatInDto);
	}
	
	@RequestMapping(method = RequestMethod.GET, path = "/total_available")
	public RetrieveTotalAvailableSeatOutDto retriveTotalAvailableSeat(@RequestBody RetrieveTotalAvailableSeatInDto retrieveTotalAvailableSeatInDto) throws Exception{
		return seatMgmtService.retriveTotalAvailableSeat(retrieveTotalAvailableSeatInDto);
	}
	
	@RequestMapping(method = RequestMethod.POST, path = "/move")
	public MoveSeatOutDto moveSeat(MoveSeatInDto moveSeatInDto) throws Exception {
		return seatMgmtService.moveSeat(moveSeatInDto);
	}
	
	@RequestMapping(method = RequestMethod.POST, path = "/check_in")
	public CheckInSeatOutDto moveSeat(CheckInSeatInDto checkInSeatInDto) throws Exception {
		return seatMgmtService.checkInSeat(checkInSeatInDto);
	}
	
	@RequestMapping(method = RequestMethod.GET, path = "/building")
	public List<RetrieveBuildingOutDto> retrieveBuilding(RetrieveBuildingInDto retrieveBuildingInDto) throws Exception {
		return seatMgmtService.retrieveBuilding(retrieveBuildingInDto);
	}
	
	@RequestMapping(method = RequestMethod.GET, path = "/floor")
	public List<RetrieveFloorOutDto> retrieveFloor(RetrieveFloorInDto retrieveFloorInDto) throws Exception {
		return seatMgmtService.retrieveFloor(retrieveFloorInDto);
	}
	
	@RequestMapping(method = RequestMethod.GET, path = "/total_seat")
	public List<RetrieveTotalSeatOutDto> retrieveTotalSeat(RetrieveTotalSeatInDto retrieveTotalSeatInDto) throws Exception {
		return seatMgmtService.retrieveTotalSeat(retrieveTotalSeatInDto);
	}
	
}
