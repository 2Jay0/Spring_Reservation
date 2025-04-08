package com.cns.aidd_reservation.reservation.dto;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class InsertReservationDto {
	private int seatId;
	private LocalDateTime startTime;
	private LocalDateTime endTime;
	
	@Builder
	public InsertReservationDto(int seatId, LocalDateTime startTime, LocalDateTime endTime) {
		super();
		this.seatId = seatId;
		this.startTime = startTime;
		this.endTime = endTime;
	}
}
