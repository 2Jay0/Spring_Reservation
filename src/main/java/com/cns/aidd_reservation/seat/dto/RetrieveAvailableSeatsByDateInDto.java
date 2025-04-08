package com.cns.aidd_reservation.seat.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RetrieveAvailableSeatsByDateInDto {
	private String startTime;
	private String endTime;
	
	@Builder
	public RetrieveAvailableSeatsByDateInDto(String startTime, String endTime) {
		super();
		this.startTime = startTime;
		this.endTime = endTime;
	}
}