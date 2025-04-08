package com.cns.aidd_reservation.seat.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MoveSeatOutDto {
	private int seatID;
	private boolean successYn;
	
	@Builder
	public MoveSeatOutDto(int seatId, boolean successYn) {
		super();
		this.seatID = seatId;
		this.successYn = successYn;
	}
}
