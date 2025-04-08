package com.cns.aidd_reservation.reservation.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RegisterReservationInDto {
	private int employeeId;
	private int seatId;
	private String startTime;
	private String endTime;
	
	@Builder
	public RegisterReservationInDto(int employeeId, int seatId, String startTime, String endTime) {
		super();
		this.employeeId = employeeId;
		this.seatId = seatId;
		this.startTime = startTime;
		this.endTime = endTime;
	}

}
