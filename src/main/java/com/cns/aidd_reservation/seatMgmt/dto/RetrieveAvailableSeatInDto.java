package com.cns.aidd_reservation.seatMgmt.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RetrieveAvailableSeatInDto {
	private String date;
	private String startTime;
	private String endTime;
	private Integer skip;
	private Integer limit;
	
	@Builder
	public RetrieveAvailableSeatInDto(String date, String startTime, String endTime, Integer skip, Integer limit) {
		super();
		this.date = date;
		this.startTime = startTime;
		this.endTime = endTime;
		this.skip = skip;
		this.limit = limit;
	}
}
