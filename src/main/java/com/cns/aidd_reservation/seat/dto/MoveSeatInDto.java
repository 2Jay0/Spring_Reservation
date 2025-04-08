package com.cns.aidd_reservation.seat.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MoveSeatInDto {
	private int reservationId;
	private int seatId;
	
	@Builder
	public MoveSeatInDto(int reservationId, int seatId) {
		super();
		this.reservationId = reservationId;
		this.seatId = seatId;
	}
}
