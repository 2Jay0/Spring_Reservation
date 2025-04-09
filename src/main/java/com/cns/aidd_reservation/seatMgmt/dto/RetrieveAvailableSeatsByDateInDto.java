package com.cns.aidd_reservation.seatMgmt.dto;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RetrieveAvailableSeatsByDateInDto {
	private LocalDateTime startTime;
	private LocalDateTime endTime;
	private int skip;
	private int limit;
	
	@Builder
	public RetrieveAvailableSeatsByDateInDto(LocalDateTime startTime, LocalDateTime endTime, int skip, int limit) {
		super();
		this.startTime = startTime;
		this.endTime = endTime;
		this.skip = skip;
		this.limit = limit;
	}
}