package com.cns.aidd_reservation.reservation.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class RetrieveReservationByEmployeeOutDto {
	private int reservationId;
	private LocalDateTime startTime;
	private LocalDateTime endTime;

	@Builder
	public RetrieveReservationByEmployeeOutDto(int reservationId, LocalDateTime startTime, LocalDateTime endTime) {
		super();
		this.reservationId = reservationId;
		this.startTime = startTime;
		this.endTime = endTime;
	}
}
