package com.cns.aidd_reservation.admin.contorller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cns.aidd_reservation.admin.dto.ForceCancelSeatInDto;
import com.cns.aidd_reservation.admin.dto.ForceCancelSeatOutDto;
import com.cns.aidd_reservation.admin.dto.ForceMoveSeatInDto;
import com.cns.aidd_reservation.admin.dto.ForceMoveSeatOutDto;
import com.cns.aidd_reservation.admin.service.AdminService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

	@Autowired
	private final AdminService adminService;
	
	@RequestMapping(method = RequestMethod.POST, path = "/return")
	public ForceCancelSeatOutDto forceCancelSeat(@RequestBody ForceCancelSeatInDto forceCancelSeatInDto) throws Exception{
		return adminService.forceCancelSeat(forceCancelSeatInDto);
	}
	
	@RequestMapping(method = RequestMethod.POST, path = "/move")
	public ForceMoveSeatOutDto forceMoveSeat(@RequestBody ForceMoveSeatInDto forceMoveSeatInDto) throws Exception{
		return adminService.forceMoveSeat(forceMoveSeatInDto);
	}
}
