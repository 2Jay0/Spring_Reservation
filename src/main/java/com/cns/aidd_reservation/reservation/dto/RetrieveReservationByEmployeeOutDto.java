package com.cns.aidd_reservation.reservation.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RetrieveReservationByEmployeeOutDto {
	private int reservationId;
	
	@Builder
	public RetrieveReservationByEmployeeOutDto(int reservationId) {
		super();
		this.reservationId = reservationId;
	}
}
