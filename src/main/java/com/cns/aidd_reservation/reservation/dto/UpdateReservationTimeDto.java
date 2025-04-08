package com.cns.aidd_reservation.reservation.dto;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UpdateReservationTimeDto {
	private int reservationId;
	private LocalDateTime startTime;
	private LocalDateTime endTime;
	
	@Builder
	public UpdateReservationTimeDto(int reservationId, LocalDateTime startTime, LocalDateTime endTime) {
		super();
		this.reservationId = reservationId;
		this.startTime = startTime;
		this.endTime = endTime;
	}
}
