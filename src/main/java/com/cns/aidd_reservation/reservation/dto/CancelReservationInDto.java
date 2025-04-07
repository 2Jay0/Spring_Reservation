package com.cns.aidd_reservation.reservation.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CancelReservationInDto {
	private int reservationId;
	
	@Builder
	public CancelReservationInDto(int reservationId) {
		super();
		this.reservationId = reservationId;
	}
}
