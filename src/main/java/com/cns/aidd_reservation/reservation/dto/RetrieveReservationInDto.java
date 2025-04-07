package com.cns.aidd_reservation.reservation.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RetrieveReservationInDto {
	private int reservationId;
	
	@Builder
	public RetrieveReservationInDto(int reservationId) {
		super();
		this.reservationId = reservationId;
	}
}
