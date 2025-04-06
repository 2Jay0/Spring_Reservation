package com.cns.aidd_reservation.reservation.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RegisterReservationOutDto {
	private int seatId;
	
	public RegisterReservationOutDto(int seatId) {
		this.seatId = seatId;
	}
}
