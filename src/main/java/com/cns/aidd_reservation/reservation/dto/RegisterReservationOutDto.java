package com.cns.aidd_reservation.reservation.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RegisterReservationOutDto {
	private boolean successYn;
	
	@Builder
	public RegisterReservationOutDto(boolean successYn) {
		super();
		this.successYn = successYn;
	}
}
