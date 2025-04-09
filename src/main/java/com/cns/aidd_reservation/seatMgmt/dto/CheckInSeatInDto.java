package com.cns.aidd_reservation.seatMgmt.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CheckInSeatInDto {
	private int reservationId;
	
	@Builder
	public CheckInSeatInDto(int reservationId) {
		super();
		this.reservationId = reservationId;
	}
}
