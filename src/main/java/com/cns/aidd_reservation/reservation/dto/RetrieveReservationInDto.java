package com.cns.aidd_reservation.reservation.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RetrieveReservationInDto {
	private int reservationId;
	
	public RetrieveReservationInDto(int reservationId) {
		this.reservationId = reservationId;
	}
}
