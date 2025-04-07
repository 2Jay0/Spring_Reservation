package com.cns.aidd_reservation.reservation.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RetrieveReservationBySeatTimeInDto {
	private int seatId;
	
	@Builder
	public RetrieveReservationBySeatTimeInDto(int seatId) {
		super();
		this.seatId = seatId;
	}
}
