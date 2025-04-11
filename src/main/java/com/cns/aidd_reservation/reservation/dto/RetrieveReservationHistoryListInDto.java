package com.cns.aidd_reservation.reservation.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RetrieveReservationHistoryListInDto {
	private int employeeId;
	private String startDate;
	private String endDate;
	private int skip;
	private int limit;
	
	@Builder
	public RetrieveReservationHistoryListInDto(int employeeId, String startDate, String endDate, int skip, int limit) {
		super();
		this.employeeId = employeeId;
		this.startDate = startDate;
		this.endDate = endDate;
		this.skip = skip;
		this.limit = limit;
	}
}
