package com.cns.aidd_reservation.reservation.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RetrieveReservationOutDto {
	private int reservationId;
	
	public RetrieveReservationOutDto(int reservationId) {
		this.reservationId = reservationId;
	}
}
