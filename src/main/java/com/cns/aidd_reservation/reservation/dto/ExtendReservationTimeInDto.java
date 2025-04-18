package com.cns.aidd_reservation.reservation.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ExtendReservationTimeInDto {
	private int reservationId;
	
	@Builder
	public ExtendReservationTimeInDto(int reservationId) {
		super();
		this.reservationId = reservationId;
	}
}
