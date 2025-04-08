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
	private LocalDateTime startTime;
	private LocalDateTime endTime;
	
	@Builder
	public RetrieveReservationOutDto(int reservationId, int seatId, LocalDateTime startTime, LocalDateTime endTime) {
		super();
		this.reservationId = reservationId;
		this.seatId = seatId;
		this.startTime = startTime;
		this.endTime = endTime;
	}
}
