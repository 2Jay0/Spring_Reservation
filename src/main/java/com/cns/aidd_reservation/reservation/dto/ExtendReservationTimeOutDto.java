package com.cns.aidd_reservation.reservation.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ExtendReservationTimeOutDto {
	private boolean successYn;
	
	@Builder
	public ExtendReservationTimeOutDto(boolean successYn) {
		super();
		this.successYn = successYn;
	}
}
