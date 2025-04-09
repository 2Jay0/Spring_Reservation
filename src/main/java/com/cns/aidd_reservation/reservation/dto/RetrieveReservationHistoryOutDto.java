package com.cns.aidd_reservation.reservation.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RetrieveReservationHistoryOutDto {
	private int reservationId;
	
	@Builder
	public RetrieveReservationHistoryOutDto(int reservationId) {
		super();
		this.reservationId = reservationId;
	}
}
