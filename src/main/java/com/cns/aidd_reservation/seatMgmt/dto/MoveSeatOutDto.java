package com.cns.aidd_reservation.seatMgmt.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MoveSeatOutDto {
	private boolean successYn;
	
	@Builder
	public MoveSeatOutDto(boolean successYn) {
		super();
		this.successYn = successYn;
	}
}
