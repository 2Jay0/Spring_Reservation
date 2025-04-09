package com.cns.aidd_reservation.seatMgmt.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ReturnSeatInDto {
	private int reservationId;
	
	@Builder
	public ReturnSeatInDto(int reservationId) {
		super();
		this.reservationId = reservationId;
	}
}
