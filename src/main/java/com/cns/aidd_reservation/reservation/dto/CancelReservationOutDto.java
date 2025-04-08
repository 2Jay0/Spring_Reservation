package com.cns.aidd_reservation.reservation.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CancelReservationOutDto {
	private boolean successYn;
	
	@Builder
	public CancelReservationOutDto(boolean successYn) {
		super();
		this.successYn = successYn;
	}

}
