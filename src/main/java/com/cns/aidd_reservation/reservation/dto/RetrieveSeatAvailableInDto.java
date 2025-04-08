package com.cns.aidd_reservation.reservation.dto;

import java.time.LocalDateTime;
import java.util.Date;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RetrieveSeatAvailableInDto {
	private int seatId;
	private LocalDateTime startTime;
	private LocalDateTime endTime;
	
	@Builder
	public RetrieveSeatAvailableInDto(int seatId, Date date, LocalDateTime startTime, LocalDateTime endTime) {
		super();
		this.seatId = seatId;
		this.startTime = startTime;
		this.endTime = endTime;
	}

}
