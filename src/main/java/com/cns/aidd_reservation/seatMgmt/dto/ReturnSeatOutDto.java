package com.cns.aidd_reservation.seatMgmt.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ReturnSeatOutDto {
	private boolean successYn;
	
	@Builder
	public ReturnSeatOutDto(boolean successYn) {
		super();
		this.successYn = successYn;
	}
}
