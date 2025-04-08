package com.cns.aidd_reservation.reservation.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RegisterReservationOutDto {
	private int seatId;
	private boolean successYn;
	
	@Builder
	public RegisterReservationOutDto(int seatId, boolean successYn) {
		super();
		this.seatId = seatId;
		this.successYn = successYn;
	}
}
