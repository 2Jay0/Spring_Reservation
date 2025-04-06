package com.cns.aidd_reservation.reservation.dto;

import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RegisterReservationInDto {
	private int employeeId;
	private int seatId;
	private Date date;
	private String startTime;
	private String endTime;
	
	public RegisterReservationInDto(int employeeId, int seatId, Date date, String startTime, String endTime) {
		this.employeeId = employeeId;
		this.seatId = seatId;
		this.date = date;
		this.startTime = startTime;
		this.endTime = endTime;
	}

}
