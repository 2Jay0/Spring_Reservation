package com.cns.aidd_reservation.reservation.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RetrieveRemainSeatTimeInDto {
	private int seatId;
	
	@Builder
	public RetrieveRemainSeatTimeInDto(int seatId) {
		super();
		this.seatId = seatId;
	}
}
