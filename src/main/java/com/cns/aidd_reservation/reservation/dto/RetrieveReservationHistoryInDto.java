package com.cns.aidd_reservation.reservation.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RetrieveReservationHistoryInDto {
	private String startDate;
	private String endDate;
	private Integer skip;
	private Integer limit;
	
	@Builder
	public RetrieveReservationHistoryInDto(String startDate, String endDate, Integer skip, Integer limit) {
		super();
		this.startDate = startDate;
		this.endDate = endDate;
		this.skip = skip;
		this.limit = limit;
	}
}
