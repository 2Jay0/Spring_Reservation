package com.cns.aidd_reservation.admin.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ForceCancelSeatInDto {
	private int reservationId;
	
	@Builder
	public ForceCancelSeatInDto(int reservationId) {
		super();
		this.reservationId = reservationId;
	}
}
