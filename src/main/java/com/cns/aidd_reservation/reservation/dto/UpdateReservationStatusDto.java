package com.cns.aidd_reservation.reservation.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UpdateReservationStatusDto {
	private int reservationId;
	private String status;
	
	public UpdateReservationStatusDto(int reservationId, String status) {
		this.reservationId = reservationId;
		this.status = status;
	}

}
