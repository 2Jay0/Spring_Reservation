package com.cns.aidd_reservation.admin.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ForceMoveSeatInDto {
	private int reservationId;
	private int seatId;
	private String reason;
	
	@Builder
	public ForceMoveSeatInDto(int reservationId, int seatId, String reason) {
		super();
		this.reservationId = reservationId;
		this.seatId = seatId;
		this.reason = reason;
	}
}
