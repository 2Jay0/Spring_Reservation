package com.cns.aidd_reservation.reservation.dto;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RetrieveReservationOutDto {
	private int reservationId;
	private int seatId;
	private int employeeId;
	private LocalDateTime startTime;
	private LocalDateTime endTime;
	private String status;
	
	@Builder
	public RetrieveReservationOutDto(int reservationId, int seatId, int employeeId
			, LocalDateTime startTime, LocalDateTime endTime, String status) {
		super();
		this.reservationId = reservationId;
		this.seatId = seatId;
		this.employeeId = employeeId;
		this.startTime = startTime;
		this.endTime = endTime;
		this.status = status;
	}
}
