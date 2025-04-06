package com.cns.aidd_reservation.admin.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ForceCancelSeatInDto {
	private int reservationId;
	
	public ForceCancelSeatInDto(int reservationId) {
		this.reservationId = reservationId;
	}
}
